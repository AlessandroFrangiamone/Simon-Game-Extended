package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditsView extends JFrame{

    private JButton backButton;
    private JLabel credits_text;

    public CreditsView(){
        super("Simon Game Extended - Credits");
        this.setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);

        setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );


        add(Box.createRigidArea(new Dimension(5,150)));

        credits_text = new JLabel("", SwingConstants.CENTER);
        credits_text.setAlignmentX(Component.CENTER_ALIGNMENT);
        credits_text.setText("<html>Questo progetto è stato realizzato da <b>Alessandro Frangiamone</b> per il corso<br>" +
                "Programmazione per la Musica svolto dal professor Adriano Baratè presso <br>" +
                "l'Università degli Studi di Milano nell'anno accademico 2020/2021.</html>");
        credits_text.setFont(new Font("SansSerif", Font.PLAIN, 18));
        credits_text.setForeground(Color.white);
        add(credits_text);

        add(Box.createRigidArea(new Dimension(5,50)));

        backButton = new JButton("Indietro");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Torno al Menù
                new MenuView();
                dispose();
            }
        });

        this.setVisible(true);
    }

}
