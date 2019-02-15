import java.io.IOException;
import java.lang.Process;
import java.lang.Runtime;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Console;

public class MailSender {

    public static void main(String[] args) {
            String subject = args[0];
            int items = args.length;

        try {
            List<String> recipientsList = new ArrayList<String>();
            List<String> contentList  = new ArrayList<String>();
            contentList.add("File1");
            contentList.add("File2");
            contentList.add("File3");
            contentList.add("File4");
            contentList.add("File5");
            contentList.add("File5");
            contentList.add("File6");
            contentList.add("File7");
            for(int i = 1; i < items ; ++i) {
                recipientsList.add(args[i]);
            }
            String recipients = recipientsList.toString();
            System.out.println(recipients);
            recipients = recipients.substring(1, recipients.length() - 1);
            System.out.println(recipients);
            System.out.println(subject);
            String content = contentList.toString();
            //System.out.println(content);
            Process mailer = Runtime.getRuntime().exec("mail -s " + subject + " " + recipients);
            //Process mailer = Runtime.getRuntime().exec("echo " + content);
            //Process mailer = Runtime.getRuntime().exec("cat testData");
            /*BufferedReader br = new BufferedReader(new InputStreamReader(mailer.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            */
            BufferedWriter wr  = new BufferedWriter(new OutputStreamWriter(mailer.getOutputStream()));
            wr.write(content, 0, content.length());
            //Makes the main thread wait till the process finishes
            wr.flush();
            wr.close();
            mailer.waitFor();
            mailer.destroy();
        } catch (InterruptedException | IOException ie) {
       // } catch (IOException ie) {
            ie.printStackTrace();
        }


    }
}
