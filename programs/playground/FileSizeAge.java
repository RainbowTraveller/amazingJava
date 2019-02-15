import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileSizeAge {

    public static void main(String [] args) throws Exception {
        File file = new File("./data/HalfGBInput");
        BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        FileTime fileAge = attrs.creationTime();
        long creationTimeMillis = fileAge.toMillis();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("AGE : " + (currentTimeMillis - creationTimeMillis) / 1000);
        long fileSize = (file.length() / (1024 * 1024));
        System.out.println("SIZE :" + fileSize);
    }
}
