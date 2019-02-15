import java.lang.StringBuilder;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        System.out.println("HOUR DEMO ---------------------------: ");
        String logId = "w10.aws.sg.saavn.com:840:1477294527.1449";
        String ts = logId.substring(logId.lastIndexOf(':') + 1);
        System.out.println(" TS : " + ts);
        String mili = ts.substring(0, ts.indexOf('.'));
        System.out.println(" MIL TS : " + mili);
        long num = Long.parseLong(mili);
        DateFormat f = new SimpleDateFormat("HH");
        DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date d = new Date(num * 1000);
        System.out.println(" HOUR : " + f.format(d));
        System.out.println(" DATE : " + format.format(d));

        String filename = "staging.saavn.com:17408:1478124005.3469-error_log-20161102-22.gz";
        //String filename = "as-i-f0de52a6.aws.sg.saavn.com:18163:1479125724.3411-error_log-20161114-04.gz";
        String withoutExt = filename.substring(0, filename.lastIndexOf('.'));
        System.out.println("NO extension " + withoutExt);
        index = withoutExt.indexOf("error_log");
        index = withoutExt.indexOf('-', index);
        String dateHr = withoutExt.substring(index + 1);
        String date = dateHr.substring(0, dateHr.indexOf('-'));
        String hr = dateHr.substring(dateHr.indexOf('-') + 1);
        System.out.println("Date : " + date + " HR:" + hr);
    }
}
