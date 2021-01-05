package main.java.view;

import main.java.model.CurrentData;

import javax.swing.*;
import java.awt.*;

public class Board extends JLabel {

    private Image img;
    private Boolean playing = false;
    private CurrentData data;

    public Board(String img, CurrentData data) {
        this.data = data;
        Image icon= new ImageIcon(img).getImage();
        this.img=icon;
        Dimension size = new Dimension(100, 100);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public Board(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    void setImage(String img){
        Image icon= new ImageIcon(img).getImage();
        this.img=icon;
        repaint();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 6, 6, 90, 90, null);
    }

    boolean isPlaying(){return playing;}
    void setPlaying(boolean playing){this.playing = playing;}

}
