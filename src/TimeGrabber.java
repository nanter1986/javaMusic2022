import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TimeGrabber {
    public String grabTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
