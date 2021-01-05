package main.java.view;

import main.java.controller.SimonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomizeSettings extends JFrame{

    private JComboBox DifficultySelector;
    private JComboBox instrumentSelector;
    private JComboBox noteSelector;
    private JComboBox scaleSelector;
    private JComboBox speedSelector;
    private JComboBox lightSelector;
    private JButton playButton;
    private JButton backButton;




    public CustomizeSettings(){
        super("Simon Game Extended - Settings");
        this.setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);


        setLayout(
                new BorderLayout()
        );

        //Setto il background del Menù
        setContentPane(new JLabel(new ImageIcon("src/main/resources/img/background_settings.jpg")));

        setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
        );

        DifficultySelector = new JComboBox(new String[]{"Facile", "Medio", "Difficile"});
        instrumentSelector = new JComboBox(new String[]{"Piano", "Organo", "Batteria", "Sassofono", "Arpa", "Chitarra"});
        noteSelector = new JComboBox(new String[]{"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"});
        scaleSelector = new JComboBox(new String[]{"Cromatica", "Maggiore", "Minore", "Pentatonica Maggiore", "Pentatonica Minore"});
        speedSelector = new JComboBox(new String[]{"Normale", "90BPM", "120BPM"});
        lightSelector = new JComboBox(new String[]{"Si", "No"});


        add(Box.createRigidArea(new Dimension(5,150)));


        /*

            JPANEL Difficoltà

         */
        //Più difficile = Più Tasti
        JPanel difficultySelection = new JPanel();
        difficultySelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        difficultySelection.setBorder(BorderFactory.createMatteBorder(7, 7, 0, 7, Color.GRAY));
        add(difficultySelection);
        difficultySelection.add(Box.createRigidArea(new Dimension(7,65)));

        difficultySelection.setLayout(new BoxLayout(difficultySelection, BoxLayout.X_AXIS));
        JLabel label_diff = new JLabel("Difficoltà: ");
        label_diff.setFont(new Font("SanSerif", Font.BOLD, 18));
        label_diff.setAlignmentX(Component.CENTER_ALIGNMENT);

        label_diff.setMaximumSize(new Dimension(250, 50));
        DifficultySelector.setMaximumSize(new Dimension(250, 50));
        DifficultySelector.setFont(new Font("SanSerif", Font.PLAIN, 18));

        difficultySelection.add(label_diff);
        difficultySelection.add(DifficultySelector);


        difficultySelection.add(Box.createRigidArea(new Dimension(7,65)));



        /*

            JPANEL Strumento

         */
        JPanel instrumentSelection = new JPanel();
        instrumentSelection.setLayout(new BoxLayout(instrumentSelection, BoxLayout.X_AXIS));
        instrumentSelection.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 7, Color.GRAY));
        add(instrumentSelection);
        instrumentSelection.add(Box.createRigidArea(new Dimension(7,65)));

        JLabel label_strum = new JLabel("Seleziona Strumento: ");
        label_strum.setFont(new Font("SanSerif", Font.BOLD, 18));

        label_strum.setMaximumSize(new Dimension(250, 50));
        instrumentSelector.setMaximumSize(new Dimension(250, 50));
        instrumentSelector.setFont(new Font("SanSerif", Font.PLAIN, 18));

        instrumentSelection.add(label_strum);
        instrumentSelection.add(instrumentSelector);

        instrumentSelection.add(Box.createRigidArea(new Dimension(7,65)));



        /*

            JPANEL Note

         */
        JPanel noteSelection = new JPanel();
        noteSelection.setLayout(new BoxLayout(noteSelection, BoxLayout.X_AXIS));
        noteSelection.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 7, Color.GRAY));
        add(noteSelection);
        noteSelection.add(Box.createRigidArea(new Dimension(7,65)));


        JLabel label_note = new JLabel("Seleziona Nota: ");
        label_note.setFont(new Font("SanSerif", Font.BOLD, 18));

        label_note.setMaximumSize(new Dimension(250, 50));
        noteSelector.setMaximumSize(new Dimension(250, 50));
        noteSelector.setFont(new Font("SanSerif", Font.PLAIN, 18));

        noteSelection.add(label_note);
        noteSelection.add(noteSelector);

        noteSelection.add(Box.createRigidArea(new Dimension(7,65)));



        /*

            JPANEL SCALA

         */
        JPanel scaleSelection = new JPanel();
        scaleSelection.setLayout(new BoxLayout(scaleSelection, BoxLayout.X_AXIS));
        scaleSelection.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 7, Color.GRAY));
        add(scaleSelection);
        scaleSelection.add(Box.createRigidArea(new Dimension(7,65)));

        JLabel label_scale = new JLabel("Seleziona Scala: ");
        label_scale.setFont(new Font("SanSerif", Font.BOLD, 18));

        label_scale.setMaximumSize(new Dimension(250, 50));
        scaleSelector.setMaximumSize(new Dimension(250, 50));
        scaleSelector.setFont(new Font("SanSerif", Font.PLAIN, 18));

        scaleSelection.add(label_scale);
        scaleSelection.add(scaleSelector);

        scaleSelection.add(Box.createRigidArea(new Dimension(7,65)));

        /*

            JPANEL VELOCITà

         */
        JPanel speedSelection = new JPanel();
        speedSelection.setLayout(new BoxLayout(speedSelection, BoxLayout.X_AXIS));
        speedSelection.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 7, Color.GRAY));
        add(speedSelection);
        speedSelection.add(Box.createRigidArea(new Dimension(7,65)));

        JLabel label_speed = new JLabel("Seleziona Velocità: ");
        label_speed.setFont(new Font("SanSerif", Font.BOLD, 18));

        label_speed.setMaximumSize(new Dimension(250, 50));
        speedSelector.setMaximumSize(new Dimension(250, 50));
        speedSelector.setFont(new Font("SanSerif", Font.PLAIN, 18));

        speedSelection.add(label_speed);
        speedSelection.add(speedSelector);

        speedSelection.add(Box.createRigidArea(new Dimension(7,65)));

        /*

            JPANEL Illuminazione

         */
        JPanel lightSelection = new JPanel();
        lightSelection.setLayout(new BoxLayout(lightSelection, BoxLayout.X_AXIS));
        lightSelection.setBorder(BorderFactory.createMatteBorder(0, 7, 7, 7, Color.GRAY));
        add(lightSelection);
        lightSelection.add(Box.createRigidArea(new Dimension(7,65)));

        JLabel label_light = new JLabel("Illuminazione tasti: ");
        label_light.setFont(new Font("SanSerif", Font.BOLD, 18));

        label_light.setMaximumSize(new Dimension(250, 50));
        lightSelector.setMaximumSize(new Dimension(250, 50));
        lightSelector.setFont(new Font("SanSerif", Font.PLAIN, 18));

        lightSelection.add(label_light);
        lightSelection.add(lightSelector);

        lightSelection.add(Box.createRigidArea(new Dimension(7,65)));

        //Fine di tutti i JPANEL

        add(Box.createRigidArea(new Dimension(5,30)));


        /*
            Button inizia, invia i dati al SimonController per iniziare la partita customizzata
         */
        playButton = new JButton("Inizia");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(playButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                dispose();
                new SimonController(difficultyToKeys(DifficultySelector.getSelectedItem().toString()), noteSelector.getSelectedIndex()+36,
                        scaleSelector.getSelectedIndex(), instrumentSelector.getSelectedIndex(), speedSelector.getSelectedIndex(), (lightSelector.getSelectedItem()=="Si")?true:false);
            }
        });

        add(Box.createRigidArea(new Dimension(5,30)));

        backButton = new JButton("Indietro");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Ritorno al Menù
                new MenuView();
                dispose();
            }
        });

        add(Box.createRigidArea(new Dimension(5,60)));

        this.setVisible(true);
    }

    private int difficultyToKeys(String diff){
        System.out.println(diff);
        switch(diff){
            case("Facile"):
                return 4;
            case("Medio"):
                return 6;
            case("Difficile"):
                return 8;
        }

        return 4;
    }

}
