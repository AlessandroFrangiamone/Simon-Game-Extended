package main.java.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.java.controller.SimonController;
import main.java.model.CurrentData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static main.java.controller.SimonController.counting;

public class GameView extends JFrame implements AWTEventListener {


    private JButton playButton;
    private JButton exitButton;
    private JButton repeatButton;

    private JLabel answerType;
    private JLabel score;
    private JLabel highScore;

    private Board[] boards;
    private CurrentData data;


    //Istanzio javafx per riprodurre i sound effects (per ora è presente solo risposta sbagliata)
    final JFXPanel fxPanel = new JFXPanel();
    final Media media = new Media(new File("src/main/resources/music/bad-beep-incorrect.mp3").toURI().toString());
    final MediaPlayer mediaPlayer = new MediaPlayer(media);

    //variabile che determina quando è il turno dell'utente nel gioco
    private boolean playing = true;

    //indica se si è premuto il bottone "ripeti"
    private boolean ripetuto = false;


    public GameView(CurrentData data){
        //Settings di Default
        super("Simon Game Extended");
        this.setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //Inizializzo i dati correnti
        this.data = data;

        setLayout(
                new BorderLayout()
        );

        //Setto il background del Menù
        setContentPane(new JLabel(new ImageIcon("src/main/resources/img/background_game.jpg")));

        setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );

        add(Box.createRigidArea(new Dimension(5,265)));

        setLayout(
                new FlowLayout()
        );

        //Inizializzazione delle boards
        JPanel g = new JPanel();
        g.setAlignmentX(Component.CENTER_ALIGNMENT);
        g.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.GRAY));
        boards=new Board[data.getBoardsNumber()];
        for(int i=0;i<data.getBoardsNumber();i++){
            boards[i] = new Board("src/main/resources/img/button_not_pressed"+i+".png", data);
            g.add(boards[i]);
        }
        add(g);
        g.setMaximumSize(new Dimension(100+(100*data.getBoardsNumber()), 125));


        setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );

        add(Box.createRigidArea(new Dimension(5,80)));
        score = new JLabel("-");
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setFont(new Font("SansSerif", Font.BOLD, 22));
        score.setText("Punteggio: "+0);
        score.setForeground(Color.WHITE);
        add(score);

        add(Box.createRigidArea(new Dimension(5,10)));
        highScore = new JLabel("-");
        highScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScore.setFont(new Font("SansSerif", Font.BOLD, 22));
        highScore.setText("Punteggio Migliore: "+data.getHighScore());
        highScore.setForeground(Color.WHITE);
        add(highScore);

        add(Box.createRigidArea(new Dimension(5,40)));
        answerType = new JLabel("-");
        answerType.setAlignmentX(Component.CENTER_ALIGNMENT);
        answerType.setFont(new Font("SansSerif", Font.BOLD, 22));
        answerType.setText("Premi inizia (o invio) per giocare");
        answerType.setForeground(Color.ORANGE);
        add(answerType);
        add(Box.createRigidArea(new Dimension(5,20)));





        repeatButton = new JButton("Ripeti");
        repeatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        repeatButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(repeatButton);
        repeatButton.setEnabled(false);
        repeatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SimonController.playPattern(data.getLevel());
                repeatButton.setEnabled(false);
                playing=false;
                ripetuto=true;
            }
        });

        add(Box.createRigidArea(new Dimension(5,20)));






        playButton = new JButton("Inizia");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(playButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SimonController.playPattern(data.getLevel());
                playButton.setEnabled(false);
                repeatButton.setEnabled(true);
                answerType.setText("   ");
                playing=false;
            }
        });

        add(Box.createRigidArea(new Dimension(5,50)));

        exitButton = new JButton("Indietro");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Rimuovo il keylistener
                GameView.this.getToolkit().removeAWTEventListener(GameView.this);
                dispose();
                //Ritorno al Menù
                new MenuView();

            }
        });

        add(Box.createRigidArea(new Dimension(5,60)));

        //KeyListener
        this.getToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);


        this.setVisible(true);
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        if(event instanceof KeyEvent && playing){

            KeyEvent key = (KeyEvent)event;
            if(key.getID()==KeyEvent.KEY_PRESSED){ //Gestisce la pressione del tasto

                //Premuto Invio
                if(key.getKeyCode() == KeyEvent.VK_ENTER){
                    if(playButton.isEnabled()) {
                        SimonController.playPattern(data.getLevel());
                        playButton.setEnabled(false);
                        answerType.setText("   ");
                        repeatButton.setEnabled(true);
                        playing = false;
                    }
                }else {
                    boards[keyToIndex(key.getKeyChar())].setImage("src/main/resources/img/button_pressed.png");
                    //Suona la Nota corrispondente in base alla key e scala fornita
                    SimonController.playNote(keyToIndex(key.getKeyChar()));
                }

            }

            if(key.getID()==KeyEvent.KEY_RELEASED){ //Gestisce il rilascio del tasto
                boards[keyToIndex(key.getKeyChar())].setImage("src/main/resources/img/button_not_pressed"+keyToIndex(key.getKeyChar())+".png");
                if(data.getLevel() > 0 && !data.isPlaying() && counting)
                    SimonController.checkNote(keyToIndex(key.getKeyChar()));
            }

        }
    }

    public void correctAnswer(){
        data.incCurrentScore();
        answerType.setText("Corretto");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    answerType.setText("Corretto");
                    answerType.setForeground(Color.GREEN);
                    playButton.setText("Continua");
                    Thread.sleep(1000);
                    answerType.setText("Premi continua (o invio) per passare al livello successivo");
                    answerType.setForeground(Color.ORANGE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();

        //punteggio scalato in base alla difficoltà, al progresso dei livelli e se l'utente ha premuto a meno ripeti
        if(!ripetuto) {
            data.setCurrentScorePoint((data.getBoardsNumber() * data.getCurrentScore()) / 2);
            score.setText("Punteggio: " + data.getCurrentScorePoint());
        }else {
            data.setCurrentScorePoint(((data.getBoardsNumber() * data.getCurrentScore()) / 2)/2);
            score.setText("Punteggio: " + data.getCurrentScorePoint());
        }
        ripetuto=false;

        System.out.println(data.getCurrentScore()+"   -   "+data.getHighScore());

        if(data.getCurrentScorePoint() >= data.getHighScore()) {
            //Aggiorno il file di testo high_score.txt
            updateScore();

            data.setHighScore(data.getCurrentScorePoint());
            highScore.setText("Punteggio Migliore: "+data.getHighScore());
        }
        counting = false;
        //abilito il pulsante continua alla risposta corretta
        playButton.setEnabled(true);
        //Disabilito il bottone ripeti alla risposta corretta
        repeatButton.setEnabled(false);
    }


    public void wrongAnswer(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer.setVolume(0.7);
                    mediaPlayer.play();

                    counting = false;
                    score.setText("Punteggio: "+0);
                    answerType.setText("Sbagliato");
                    answerType.setForeground(Color.RED);
                    playButton.setText("Riprova");
                    playButton.setEnabled(true);

                    Thread.sleep(1000);
                    mediaPlayer.stop();

                    answerType.setText("Premi riprova (o invio) per rincominciare");
                    answerType.setForeground(Color.ORANGE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();

        //Disabilito il bottone ripeti alla risposta errata
        repeatButton.setEnabled(false);

        //azzero il punteggio
        data.setCurrentScorePoint(-data.getCurrentScorePoint());
    }

    //Aggiorna lo score migliore nel file di tasto high_score.txt
    private void updateScore() {
        try {
            File file = new File("src/main/resources/other/high_score.txt");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(data.getCurrentScorePoint());
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void playPattern(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                data.setPlaying(true);
                System.out.println("PLAYING");
                //statusLabel.setText("Wait");
                for(int i = 0; i < data.getLevel(); i++){
                    try {
                        boards[data.getPattern()[i]].setPlaying(true);
                        if(data.isLight()) {
                            boards[data.getPattern()[i]].setImage("src/main/resources/img/button_pressed.png");
                        }
                        if(i==data.getLevel())
                            playing=true;

                        if(data.getVelocità()==0) {
                            Thread.sleep(1000);
                        }else{
                            if(data.getVelocità()==1)
                                Thread.sleep(750);
                            else
                                Thread.sleep(500);
                        }

                        boards[data.getPattern()[i]].setPlaying(false);
                        boards[data.getPattern()[i]].setImage("src/main/resources/img/button_not_pressed"+data.getPattern()[i]+".png");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                playing=true;
                data.setPlaying(false);
                //statusLabel.setText("Recording...");
            }
        });
        thread.start();
    }


    //Si occupa di convertire in indice il char del tasto premuto sulla tastiera
    private int keyToIndex(char key){
        switch(key){
            case('a'):
                return 0;
            case('s'):
                return 1;
            case('d'):
                return 2;
            case('f'):
                return 3;
            case('g'):
                return 4;
            case('h'):
                return 5;
            case('j'):
                return 6;
            case('k'):
                return 7;

            case('A'):
                return 0;
            case('S'):
                return 1;
            case('D'):
                return 2;
            case('F'):
                return 3;
            case('G'):
                return 4;
            case('H'):
                return 5;
            case('J'):
                return 6;
            case('K'):
                return 7;
        }
        return -1;
    }
}
