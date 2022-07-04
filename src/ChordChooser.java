import java.util.Random;

public class ChordChooser {

    String[] chordsForOne={};
    String[] progressions = {"I V iii vi", "vi IV iii V", "I V vi IV", "I vi IV V"};
    Random r = new Random();
    public String chooseChords(){
        int randomIndex = r.nextInt(progressions.length);
        System.out.println(progressions[randomIndex]);
        return progressions[randomIndex];
    }
}
