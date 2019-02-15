import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Dating {
    public static void main(String [] args) throws Exception {

        SimpleDateFormat apacheDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
        apacheDateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));

        SimpleDateFormat ratchetDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        ratchetDateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));

        Date date = new Date();
        System.out.println("Current Date : " + date);
        String serverTs =  "Mon Nov 07 17:57:23 2016";
        date = apacheDateFormat.parse(serverTs);

        System.out.println("Parsed  Date : " + date);

        String seconds = String.valueOf(date.getTime()/1000L);
        long numSeconds = Long.parseLong(seconds);
        System.out.println("String seconds : " + seconds + " Num seconds: " + numSeconds);
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println("RESULT DATE : " + dateFormat.format(d));
        DateFormat geoDateFormat = new SimpleDateFormat("Y-M-d HH:mm:ss");
        System.out.println("RESULT DATE : " + geoDateFormat.format(d));
    }
}
