/**
 * Zipping already existing file
 * Makes use of linux command
 */
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Runtime;
import java.lang.Process;

public class ZippingAfter {

    public static void main(String[] args) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String inputFile = args[0];
        try {

            date = new Date();
            System.out.println(format.format(date));
            // -k to keep original file intact
            Process zipper = Runtime.getRuntime().exec("gzip -k " + inputFile);
            //Makes the main thread wait till the process finishes
            zipper.waitFor();
            zipper.destroy();
            date = new Date();
            System.out.println(format.format(date));
        } catch (InterruptedException | IOException ie) {
            ie.printStackTrace();
        }

    }
}
