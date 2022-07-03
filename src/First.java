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
        String vocalsStaccato=new MelodyMaker().vocals();
        vocals.add(vocalsStaccato);
        vocals.setInstrument("HARMONICA");

        String BassDrumRythm="";
        String SnareRythm="";
        String HiHatRythm="";
        String ClapRythm="";
        Rhythm rhythm = new Rhythm()
                .addLayer(BassDrumRythm)
                .addLayer(SnareRythm)
                .addLayer(HiHatRythm)
                .addLayer(ClapRythm);

        String chordProg=new ChordChooser().chooseChords();
        String key=new KeyChooser().chooseKey();
        String chordStrumPattern=new ChordStrumPattern().makeStrumPattern();
        ChordProgression cp = new ChordProgression(chordProg)
                .setKey(key)
                .distribute("9")
                .allChordsAs(chordStrumPattern);


        Pattern p=new Pattern(vocals.setVoice(1).repeat(2),cp.getPattern().setVoice(0).repeat(2),rhythm.getPattern().repeat(4).setVoice(3));

        String time=new TimeGrabber().grabTime();
        System.out.println(time);
        try {
            File filePath = new File(time+".mid");
            filePath.createNewFile();
            MidiFileManager.savePatternToMidi(p, filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        player.play(p);
        Pattern loadedFile = new Pattern();
        try {
            File filePath2 = new File("ddd.mid");
            loadedFile = MidiFileManager.loadPatternFromMidi(filePath2);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(loadedFile);
    }
}
