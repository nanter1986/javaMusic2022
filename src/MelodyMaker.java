import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;
import org.jfugue.theory.Scale;

import java.util.Random;

public class MelodyMaker {
    public String vocals(Chord[] chords) {

        String melody="";
        for (Chord ch:chords){
            for(int i=0;i<4;i++) {
                int l = chords.length;
                Random r = new Random();
                int isRest= r.nextInt(3);
                int randomIndex = r.nextInt(l-1);
                int noteValue = ch.getNotes()[randomIndex].getValue();
                noteValue = noteValue + 12;
                Note n = new Note(noteValue);
                n.setDuration(0.25);
                System.out.println("note:::" + n);
                if (isRest==0 || i==0){
                    melody += n + " ";
                }else if(isRest==1){
                    for (int x=0;x<3;x++){
                        Random rtriplet = new Random();
                        int rIndex = rtriplet.nextInt(l-1);
                        int nv = ch.getNotes()[rIndex].getValue();
                        nv = nv + 12;
                        Note ntriplet = new Note(nv);
                        melody += ntriplet + "*3:1" + " ";
                    }

                }else{
                    melody += "ri" + " ";
                }

            }
            ;

        }
        System.out.println(melody);
        return melody;
    }
}