package main.java.view;

import main.java.controller.SimonController;
import main.java.model.CurrentData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static main.java.controller.SimonController.counting;

public class GameView extends JFrame implements AWTEventListener {


    private JButton playButton;
    private JButton exitButton;

    private JLabel answerType;
    private JLabel score;

    private Board[] boards;
    private CurrentData data;

    //variabile che determina quando è il turno dell'utente nel gioco
    private boolean playing = true;
    private boolean start = false;


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



        add(Box.createRigidArea(new Dimension(5,300)));


        setLayout(
                new FlowLayout()
        );

        //Inizializzazione delle boards
        JPanel g = new JPanel();
        g.setAlignmentX(Component.CENTER_ALIGNMENT);
        g.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.GRAY));
        boards=new Board[data.getBoardsNumber()];
        for(int i=0;i<data.getBoardsNumber();i++){
            boards[i] = new Board("src/main/resources/img/button_not_pressed.png", data);
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

        add(Box.createRigidArea(new Dimension(5,40)));
        answerType = new JLabel("-");
        answerType.setAlignmentX(Component.CENTER_ALIGNMENT);
        answerType.setFont(new Font("SansSerif", Font.BOLD, 22));
        answerType.setText("Premi inizia (o invio) per giocare");
        answerType.setForeground(Color.ORANGE);
        add(answerType);
        add(Box.createRigidArea(new Dimension(5,50)));

        playButton = new JButton("Inizia");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(playButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SimonController.playPattern(data.getLevel());
                playButton.setEnabled(false);
                answerType.setText("   ");
                playing=false;
            }
        });

        add(Box.createRigidArea(new Dimension(5,30)));

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
        //getRootPane().setDefaultButton(playButton);
        this.getToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);


        this.setVisible(true);
    }

    @Override
    public void eventDispatched(AWTEvent event) {
        if(event instanceof KeyEvent && playing){

            KeyEvent key = (KeyEvent)event;
            if(key.getID()==KeyEvent.KEY_PRESSED){ //Handle key presses
                if(key.getKeyCode() == KeyEvent.VK_ENTER){
                    if(playButton.isEnabled()) {
                        SimonController.playPattern(data.getLevel());
                        playButton.setEnabled(false);
                        answerType.setText("   ");
                        playing = false;
                    }
                }else {
                    boards[keyToIndex(key.getKeyChar())].setImage("src/main/resources/img/button_pressed.png");
                    //Suona la Nota corrispondente in base alla key e scala fornita
                    SimonController.playNote(keyToIndex(key.getKeyChar()));
                }

            }

            if(key.getID()==KeyEvent.KEY_RELEASED){ //Handle key presses
                boards[keyToIndex(key.getKeyChar())].setImage("src/main/resources/img/button_not_pressed.png");
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

        score.setText("Punteggio: "+data.getCurrentScore());
        if(data.getCurrentScore() >= data.getHighScore()) {
            //highScore.setText("High score :" + data.getCurrentScore());
            //updateScore();
        }
        //currentScore.setText("Current score: " + data.getCurrentScore());
        counting = false;
        playButton.setEnabled(true);
    }


    public void wrongAnswer(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    counting = false;
                    score.setText("Punteggio: "+0);
                    answerType.setText("Sbagliato");
                    answerType.setForeground(Color.RED);
                    playButton.setText("Riprova");
                    playButton.setEnabled(true);
                    Thread.sleep(1000);
                    answerType.setText("Premi riprova (o invio) per rincominciare");
                    answerType.setForeground(Color.ORANGE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
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
                        boards[data.getPattern()[i]].setImage("src/main/resources/img/button_pressed.png");
                        if(i==data.getLevel())
                            playing=true;
                        Thread.sleep(1000);
                        boards[data.getPattern()[i]].setPlaying(false);
                        boards[data.getPattern()[i]].setImage("src/main/resources/img/button_not_pressed.png");
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
        }
        return -1;
    }

    /*public void initialiseBoards(){
        boards=new Board[data.getBoardsNumber()];
        for(int i=0;i<data.getBoardsNumber();i++){
            boards[i] = new Board("src/main/resources/img/button_not_pressed.png");
            add(boards[i]);
        }
    }*/
}
