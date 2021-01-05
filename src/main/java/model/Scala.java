package main.java.model;

public class Scala {

    private String[] notes;
    public Scala(){notes = new String[]{"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};}

    public int getNote(int offset, int scale){
        int[] notes;
        int note = 0;
        switch(scale){
            //Scala Cromatica
            case(0):
                return offset;
            //Scala Maggiore
            case(1):
                notes = new int[]{0, 2, 4, 5, 7, 9 ,11 };
                while(offset >= 7){
                    offset-=7;
                    note+=12;
                }
                note += notes[offset];
                return note;
            //Scala Minore
            case(2):
                notes = new int[]{0, 2, 3, 5, 7, 8, 10};
                while(offset >= 7){
                    offset-=7;
                    note+=12;
                }
                note += notes[offset];
                return note;
            //Scala Pentatonica Maggiore
            case(3):
                notes = new int[]{0, 2, 4, 7, 9};
                while(offset >= 5){
                    offset-=5;
                    note+=12;
                }
                note += notes[offset];
                return note;
            //Scala Pentatonica Minore
            case(4):
                notes = new int[]{0, 3, 5, 7, 10};
                while(offset >= 5){
                    offset-=5;
                    note+=12;
                }
                note += notes[offset];
                return note;
        }
        return offset;
    }
}
