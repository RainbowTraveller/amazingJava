import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class DownloadImageFromURL {

    public static void main(String[] args) {
        String urlStr = "url";
        try {
            URL url = new URL(urlStr);
            String filename = url.getFile();
            if(filename.indexOf("/") != -1) {
                int lastIndex = filename.lastIndexOf('/');
                filename = filename.substring(lastIndex + 1);
            }
            System.out.println("FILE NAME :" + filename);
            try(InputStream in = url.openStream()){
                Files.copy(in, Paths.get("/Users/xyz/pqr/u.jpg"), REPLACE_EXISTING);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch(IOException ie) {
                ie.printStackTrace();
        }

    }
}
