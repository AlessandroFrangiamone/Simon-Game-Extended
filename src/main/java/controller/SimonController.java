package main.java.controller;

import jm.constants.ProgramChanges;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.util.Play;
import main.java.model.CurrentData;
import main.java.view.GameView;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class SimonController {

    private int []pattern;
    private static Phrase[]notes;
    public static boolean counting = false;
    public static CurrentData data;
    public static Part part;                     //random part
    public static Part temp;                     //Suonata dall'utente
    public static GameView gameView;

    public SimonController(int keysNumber, int key, int scale, int instrument, int velocità, boolean light){
        setCurrentData(keysNumber, key, scale, instrument, velocità, light);
    }

    private void setCurrentData(int keysNumber, int key, int scale, int instrument, int velocità, boolean light){
        data = new CurrentData(keysNumber, key, scale, instrument, velocità, light);
        data.resetScore();
        initializeScore();
        data.setInstrument(instrument);
        loadNotes();
        setInstrument();
        generatePattern();
        launchGame();
    }

    public void launchGame(){
        gameView = new GameView(data);
    }

    private void setInstrument(){
        setPart(data.getInstrument());
        setTemp(data.getInstrument());
        System.out.println(data.getInstrument());
        Play.midi(part);
    }

    private void setPart(int choice){
        switch(choice){
            case(0):
                part = new Part("Piano", ProgramChanges.PIANO);
                break;
            case(1):
                part = new Part("Organ", ProgramChanges.ORGAN);
                break;
            case(2):
                part = new Part("Drums", ProgramChanges.DRUM);
                break;
            case(3):
                part = new Part("Flute", ProgramChanges.SAXOPHONE);
                break;
            case(4):
                part = new Part("Harp", ProgramChanges.HARP);
                break;
            case(5):
                part = new Part("Nylon guitar", ProgramChanges.NYLON_GUITAR);
                break;
        }
    }

    public void setTemp(int choice){
        switch(choice){
            case(0):
                temp = new Part("Piano", ProgramChanges.PIANO);
                break;
            case(1):
                temp = new Part("Organ", ProgramChanges.ORGAN);
                break;
            case(2):
                temp = new Part("Drums", ProgramChanges.DRUM);
                break;
            case(3):
                temp = new Part("Flute", ProgramChanges.SAXOPHONE);
                break;
            case(4):
                temp = new Part("Harp", ProgramChanges.HARP);
                break;
            case(5):
                temp = new Part("Nylon guitar", ProgramChanges.NYLON_GUITAR);
                break;
        }
    }

    //Inizializza l'highscore
    public void initializeScore(){
        try{
            File file = new File("src/main/resources/other/high_score.txt");
            Scanner scanner = new Scanner(file);
            data.setHighScore(Integer.valueOf(scanner.nextLine()));
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    //Riproduce un pattern
    public static void playPattern(int n){
        Phrase phrase = new Phrase();
        //imposta i bpm
        if(data.getVelocità()==0) {
            part.setTempo(60);
        }else{
            if(data.getVelocità()==1)
                part.setTempo(90);
            else
                part.setTempo(120);
        }

        if(part.length() != 0)
            part.removeAllPhrases();
        for(int i = 0; i < n; i++){
            phrase.add(new Note(data.getKey()+data.getScale().getNote(data.getPattern()[i],data.getScaleType()), 1));
        }
        part.add(phrase);
        gameView.playPattern();
        Play.midi(part);
        counting = true;
    }


    //Suona una nota
    public static void playNote(int i){
        if(temp.length() != 0)
            temp.removeAllPhrases();
        temp.add(notes[i]);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Play.midi(temp);
            }
        });
        thread.start();
    }


    //Controlla le note premute dal giocatore, se sono corrette restituisce true, altrimenti false e finisce il gioco
    public static Boolean checkNote(int note){
        data.incCounter();
        if(data.getPattern()[data.getCounter()-1] == note) {
            if(data.getCounter() == data.getLevel()){
                gameView.correctAnswer();
                data.resetCounter();
            }
            return true;
        }
        else {
            data.resetCounter();
            gameView.wrongAnswer();
            data.resetScore();
            return false;
        }
    }


    //Carica le note possibili (36) in un vettore di Phrases, ogni Phrases contiene una nota
    private void loadNotes(){
        notes = new Phrase[36];
        for(int i = 0; i < 36; i++){
            notes[i] = new Phrase(new Note(data.getKey() + data.getScale().getNote(i,data.getScaleType()), 1));
        }
    }

    //Genera un pattern di dimensione 100, ogni pattern-i
    private void generatePattern() {
        pattern = new int[100];
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            pattern[i] = rand.nextInt(data.getBoardsNumber());
        }
        data.setPattern(pattern);
    }


}
