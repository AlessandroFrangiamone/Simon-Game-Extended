package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {

    private JButton playRandomButton;
    private JButton playCustomButton;
    private JButton creditsButton;
    private JButton exitButton;

    private JLabel img_title;



    public MenuView(){
        super("Simon Game Extended - Menù");
        this.setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);

        setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );


        add(Box.createRigidArea(new Dimension(5,60)));

        img_title= new JLabel(new ImageIcon("src/main/resources/TitoloMenu_Simon_Game.jpg"));
        img_title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(img_title);

        add(Box.createRigidArea(new Dimension(5,100)));

        playRandomButton = new JButton("Partita Rapida");
        playRandomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playRandomButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(playRandomButton);

        add(Box.createRigidArea(new Dimension(5,30)));

        playCustomButton = new JButton("Partita Personalizzata");
        playCustomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playCustomButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(playCustomButton);
        playCustomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Entro nella View che mi permette di settare i parametri per la prossima partita
                new CustomizeSettings();
                dispose();
            }
        });

        add(Box.createRigidArea(new Dimension(5,30)));


        creditsButton = new JButton("Crediti");
        creditsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(creditsButton);
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Entro nella View con i crediti
                new CreditsView();
                dispose();
            }
        });

        add(Box.createRigidArea(new Dimension(5,30)));

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
}
