/**
 * Reads a file and writes to a .gz compressed file
 */
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.Reader;

import java.nio.charset.Charset;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.io.IOException;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPOutputStream;

public class ZippingRuntime {

    public static void main(String[] args) {
        //File Path
         final String COMMON_FILE = "common.gz";
        //Check if file exists, if not create
        File sharedFile = null;
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String inputFile = args[0];
        try{
            //Creates a file
            sharedFile = new File(COMMON_FILE);
            sharedFile.createNewFile();
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try (
            //For writing to the file
            FileOutputStream out = new FileOutputStream(sharedFile, true);
            //Special Output stream for zipping
            //GZIPOutputStream out = new GZIPOutputStream(new
            //               BufferedOutputStream(outputStream));
            //Reader to read the file, per line
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            //Gives convinient println function, wraps zipping outputstream
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            out, Charset.forName("UTF-8").newEncoder())));
            ) {
                String line;
                date = new Date();
                System.out.println(format.format(date));
                while((line = reader.readLine()) != null) {
                    writer.println("ONE : " + line);
                    //Flushing is important or some data is not written
                    writer.flush();
                }
                date = new Date();
                System.out.println(format.format(date));
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
    }
}
