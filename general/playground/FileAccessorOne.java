import java.io.File;
//Readers - Writers
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileAccessorOne {

    public static void main(String[] args) {
        //File Path
         final String COMMON_FILE = "common.txt";
        //Check if file exists, if not create
        File sharedFile = null;
        FileLock lock = null;
        long start = 0;
        long end = 0;
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try{
            sharedFile = new File(COMMON_FILE);
            sharedFile.createNewFile();
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try (
            FileOutputStream outputStream = new FileOutputStream(sharedFile, true);
            BufferedReader reader = new BufferedReader(new FileReader("logdata"));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            outputStream, Charset.forName("UTF-8").newEncoder())));
            FileChannel channel = outputStream.getChannel();
            ) {
                String line;
                int count = 0;
                int lockCount = 0;
                int totalCount = 0;
                while((line = reader.readLine()) != null) {
                    if(lock == null) {
                        lockCount++;
                        System.out.println("ONE lock requested");
                        start = System.currentTimeMillis();
                        //lock = channel.lock();
                        while(lock == null) {
                            lock = channel.tryLock();
                        }
                        end = System.currentTimeMillis();
                        System.out.println("Time To get lock " + (end - start));
                        System.out.println("ONE lock acquired : " + lockCount);
                    }
                    writer.println("ONE : " + line);
                    writer.flush();
                    count++;
                    totalCount++;
                    if(count == 20) {
                        if(lock != null) {
                             Thread.sleep(4000);
                             lock.release();
                             lock = null;
                             System.out.println("Released lock : ");
                        }
                        count = 0;
                        System.out.println("Lines processed : " + totalCount);
                        System.out.println("ONE : Sleeping......");
                        Date date = new Date();
                        System.out.println(format.format(date));
                        Thread.sleep(5000);
                        date = new Date();
                        System.out.println(format.format(date));
                        System.out.println("ONE : Up and running......");
                    }
                }
                System.out.println("File Size : " + sharedFile.length());
                Path from = sharedFile.toPath();
                Path to = Paths.get("./data");
                Files.move(from, to.resolve(from.getFileName()), StandardCopyOption.ATOMIC_MOVE);
            } catch(IOException ioe) {
                ioe.printStackTrace();
            } catch (InterruptedException ie) {
                 ie.printStackTrace();
            } finally {
                if(lock != null) {
                    try {
                        lock.release();
                        System.out.println("Finally releasing lock");
                    } catch(IOException ioe) {

                    }
                }
            }
        //Lock and Write some stuff
        //Wait for 5 seconds
        //Lock and Read from file and print on console
        //Wait for 5 seconds
    }
}
