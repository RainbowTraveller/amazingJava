import java.lang.StringBuilder;

public class SubStrings {

    public static void main( String[] args ) {
        String path = "s3n://saavninterns/milindv/dt=20160614/qa-error_log-20160614-18-00.gz";
        String prefix  =  path.substring(0, path.lastIndexOf('/'));
        String fileName = path.substring(path.lastIndexOf('/') + 1);
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        String fileNameNoExt =  fileName.substring(0, fileName.lastIndexOf('.'));
        System.out.println("PREFIX : " + prefix + " FILENAME: " + fileName + " EXTENSION: " + extension + " ONLY NAME : " + fileNameNoExt);
        int index = 7;
        StringBuilder pathBuilder = new StringBuilder(prefix);
        pathBuilder.append("/");
        pathBuilder.append(fileNameNoExt);
        pathBuilder.append("_");
        pathBuilder.append(Integer.toString(index));
        pathBuilder.append(".");
        pathBuilder.append(extension);
        path = pathBuilder.toString();
        System.out.println("PATH: " + path);
    }
}
