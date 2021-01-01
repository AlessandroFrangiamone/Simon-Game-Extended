package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditsView extends JFrame{

    private JLabel credits_text;
    private JButton backButton;

    public CreditsView(){
        super("Simon Game Extended - Credits");
        //Default Settings
        this.setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);



        setLayout(
                new BorderLayout()
        );

        //Setto il background del Menù
        setContentPane(new JLabel(new ImageIcon("src/main/resources/img/background_credits.jpg")));

        setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );

        //spazio
        add(Box.createRigidArea(new Dimension(5,150)));



        /*
            Label contenente tutti i crediti, ho usato html per formattare dato che è comodo
         */
        credits_text = new JLabel("", SwingConstants.CENTER);
        credits_text.setAlignmentX(Component.CENTER_ALIGNMENT);
        credits_text.setText("<html>Questo progetto è stato realizzato da <b>Alessandro Frangiamone</b> per il corso<br>" +
                "Programmazione per la Musica svolto dal professor Adriano Baratè presso <br>" +
                "l'Università degli Studi di Milano nell'anno accademico 2020/2021.</html>");
        credits_text.setFont(new Font("SansSerif", Font.PLAIN, 20));
        credits_text.setForeground(Color.WHITE);
        add(credits_text);

        //spazio
        add(Box.createRigidArea(new Dimension(5,50)));



        /*
            Pulsante Indietro, torna nel menù
         */
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
