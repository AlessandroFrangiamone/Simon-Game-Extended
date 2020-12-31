package main.java.model;

public class CurrentData {

    private int boardsNumber;
    private int currentScore;
    private int instrument;
    private int []pattern;
    private int key;
    private int counter;
    private int highScore;
    //private boolean noFail;
    //private ScalaCromatica scale;
    private int scaleType;
    private Scala scale;
    private boolean customPattern = false;
    public boolean playing = false;

    public CurrentData(int keysNumber, int key, int scale, int instrument, boolean customPattern){
        this.boardsNumber = keysNumber;
        this.key = key;
        this.instrument = instrument;
        this.customPattern = customPattern;
        //Devo fare un metodo che seleziona la scala da tutte le possibili
        this.scaleType=scale;
        this.scale= new Scala();
        //scaleFactory(scale);
    }

    public int getBoardsNumber(){
        return boardsNumber;
    }
    public int getHighScore(){
        return highScore;
    }
    public int getCurrentScore(){
        return currentScore;
    }
    public void incCurrentScore(){
        currentScore++;
    }
    public void setInstrument(int instrument){
        this.instrument = instrument;
    }
    public int getInstrument(){return instrument;}
    public void setPattern(int []pattern){
        this.pattern = pattern;
    }
    public int []getPattern(){
        return pattern;
    }
    public int getCounter(){return counter;}
    public void resetCounter(){counter = 0;}
    public void incCounter(){counter++;}
    public void setHighScore(int highScore){this.highScore = highScore;}
    public int getKey(){return key;}
    public int getScaleType(){return scaleType;}
    public Scala getScale(){return scale;}
    public void resetScore() {this.currentScore = 0;}
    public int getLevel(){return this.currentScore + 1;}
    public boolean isCustomPattern(){return customPattern;}
    public boolean isPlaying(){return this.playing;}
    public void setPlaying(boolean playing){this.playing = playing;}



}
