package main.java.model;

public class CurrentData {

    private int boardsNumber;
    private int currentScore;
    private int currentScorePoint;
    private int instrument;
    private int []pattern;
    private int key;
    private int counter;
    private int highScore;
    private boolean light;
    private int velocità;
    //private boolean noFail;
    //private ScalaCromatica scale;
    private int scaleType;
    private Scala scale;
    private boolean customPattern = false;
    public boolean playing = false;

    public CurrentData(int keysNumber, int key, int scaleType, int instrument, boolean customPattern,int velocità, boolean light){
        this.boardsNumber = keysNumber;
        this.key = key;
        this.instrument = instrument;
        this.customPattern = customPattern;
        //Devo fare un metodo che seleziona la scala da tutte le possibili
        this.scaleType=scaleType;
        this.scale= new Scala();
        this.velocità = velocità;
        this.light = light;
        this.currentScorePoint=0;
        this.highScore=0;
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
    public int getInstrument(){return instrument;}
    public int []getPattern(){
        return pattern;
    }
    public int getCounter(){return counter;}
    public int getVelocità() {return velocità;}
    public int getKey(){return key;}
    public int getScaleType(){return scaleType;}
    public Scala getScale(){return scale;}
    public int getLevel(){return this.currentScore + 1;}
    public int getCurrentScorePoint(){ return currentScorePoint; }


    public void setInstrument(int instrument){
        this.instrument = instrument;
    }
    public void setPattern(int []pattern){
        this.pattern = pattern;
    }
    public void setPlaying(boolean playing){this.playing = playing;}
    public void setHighScore(int highScore){this.highScore = highScore;}
    public void setCurrentScorePoint(int score){currentScorePoint+=score;}

    public boolean isCustomPattern(){return customPattern;}
    public boolean isPlaying(){return this.playing;}
    public boolean isLight() {
        return light;
    }

    public void resetCounter(){counter = 0;}
    public void resetScore() {this.currentScore = 0;}

    public void incCounter(){counter++;}
    public void incCurrentScore(){ currentScore++; }

}
