import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.ArrayList;
import java.lang.String;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;

public class MyFiles {

    public MyFiles() {
        String strpath = this.getClass().getClassLoader().getResource(".").getPath();
        System.out.println(strpath);
    }

    public static void main(String [] args) throws Exception {
        List<String> data = new ArrayList<String>();
        data.add("one");
        data.add("two");
        data.add("Three");
        data.add("Four");
        data.add("Five");
        
        Path path = FileSystems.getDefault().getPath("../general","testData");
        System.out.println(path);
        Files.write(path, data, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        /*
        FileOutputStream fos = new FileOutputStream("testData");
        */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(data);
        oos.flush();
        byte[] binary = baos.toByteArray();
        //String text = Base64.encodeBase64String(binary);
        String text = new String(binary);
        System.out.println(text);
        MyFiles mf = new MyFiles(); 
    }
}

