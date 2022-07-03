import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

public class First {
    public static void main(String[] args) {
        Player player = new Player();

        Pattern vocals = new Pattern();
        vocals.add("C5h Rq D5i E5i | G5h Rq F5i E5i | E5h Rq F5i G5i | A5h Rq B5i B5i");
        vocals.setInstrument("HARMONICA");

        Rhythm rhythm = new Rhythm()
                .addLayer("O...O...O...O...")
                .addLayer("..S...S...S...S.")
                .addLayer("````````````````")
                .addLayer("X.......X.......");

        ChordProgression cp = new ChordProgression("I V iii vi")
                .setKey("C")
                .distribute("9")
                .allChordsAs("$0i $0i ri $0i ri $0i ri $0i | $1i $1i ri $1i ri $1i ri $1i |  $2i $2i ri $2i ri $2i ri $2i |  $3i $3i ri $3i ri $3i ri $3i |");


        Pattern p=new Pattern(vocals.setVoice(1).repeat(2),cp.getPattern().setVoice(0).repeat(2),rhythm.getPattern().repeat(4).setVoice(3));

        player.play(p);

        try {
            File filePath = new File("aaa.mid");
            filePath.createNewFile();
            MidiFileManager.savePatternToMidi(p, filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Pattern loadedFile = new Pattern();
        try {
            File filePath2 = new File("aaa.mid");
            loadedFile = MidiFileManager.loadPatternFromMidi(filePath2);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(loadedFile);
    }
}
