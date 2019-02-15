import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class CheckIfPathExists {
    public static void main(String[] args) throws IOException {
        String strPath = "/Users/xyz/ipl/loan/random";
        Path path = Paths.get(strPath);
        if(Files.exists(path)) {
            System.out.println("PATH exists");
        } else {
            Files.createDirectory(path);
        }
    }
}
