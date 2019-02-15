import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.io.IOException;

public class CopyFile {
    public static void main (String [] args) throws IOException {
        String srcFilePath = "/Users/milind/mytemp/a.txt";
        String srcDirPath =  srcFilePath.substring(0, srcFilePath.lastIndexOf('/'));
        Path srcPath = Paths.get(srcDirPath);
        DirectoryStream<Path> stream = Files.newDirectoryStream(srcPath);
        String dstDirpath = "/Users/milind/temp";
        for(Path file : stream) {
            String dstFilePath = dstDirpath + "/" + file.getFileName();
            Path dstPath = Paths.get(dstFilePath);
            Files.copy(file, dstPath);
        }
    }
}
