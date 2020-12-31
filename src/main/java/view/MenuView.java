package main.java.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.embed.swing.JFXPanel;
import main.java.controller.SimonController;


public class MenuView extends JFrame {

    private JButton playRandomButton;
    private JButton playCustomButton;
    private JButton creditsButton;
    private JButton exitButton;

    private JLabel img_title;




    //Costruttore
    public MenuView() {


        super("Simon Game Extended - Menù");

        //Istanzio javafx per poi riprodurre l'audio del menù
        final JFXPanel fxPanel = new JFXPanel();
        final Media media = new Media(new File("src/main/resources/music/home_resonance.mp3").toURI().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        playSound(mediaPlayer);


        //Settings di Default
        this.setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);


        setLayout(
                new BorderLayout()
        );

        //Setto il background del Menù
        setContentPane(new JLabel(new ImageIcon("src/main/resources/img/background.jpg")));

        setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );


        //spazio
        add(Box.createRigidArea(new Dimension(5,170)));

        //Title image del menù
        img_title= new JLabel(new ImageIcon("src/main/resources/img/TitoloMenu_Simon_Game.png"));
        img_title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(img_title);

        //spazio
        add(Box.createRigidArea(new Dimension(5,100)));



        /*
            Pulsante Partita Rapida, invoca SimonController e inizia una nuova partita con settaggi random
         */
        playRandomButton = new JButton("Partita Rapida");
        playRandomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playRandomButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(playRandomButton);
        playRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stopSound(mediaPlayer);
                setVisible(false);
                dispose();
                new SimonController(4, 0+36,
                        0, 0, false);
            }
        });

        //spazio
        add(Box.createRigidArea(new Dimension(5,30)));



        /*
            Pulsante Partita Personalizzata, invoca CustomizeSettings, una View che permette di scegliere ai parametri
            fondamentali da settare, quali: Difficoltà, Strumenti, Nota, Scala.
            In modo che la partita sia personalizzata in base alle scelta dell'utente.
         */
        playCustomButton = new JButton("Partita Personalizzata");
        playCustomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playCustomButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(playCustomButton);
        playCustomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stopSound(mediaPlayer);
                //Entro nella View che mi permette di settare i parametri per la prossima partita
                new CustomizeSettings();
                dispose();
            }
        });

        //spazio
        add(Box.createRigidArea(new Dimension(5,30)));



        /*
            Pulsante Crediti, invoca la dei crediti (CreditsView)
         */
        creditsButton = new JButton("Crediti");
        creditsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(creditsButton);
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Entro nella View con i crediti
                stopSound(mediaPlayer);
                new CreditsView();
                dispose();
            }
        });

        //spazio
        add(Box.createRigidArea(new Dimension(5,30)));



        /*
            Pulsante Esci, esce dall'applicazione
         */
        exitButton = new JButton("Esci");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Esco dall'applicazione
                System.exit(0);
            }
        });

        this.setVisible(true);

    }

    public void playSound(MediaPlayer mediaPlayer){
        System.out.println("Menu music start");
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
    }

    public void stopSound(MediaPlayer mediaPlayer){
        System.out.println("Menu music stop");
        mediaPlayer.stop();
    }

}
