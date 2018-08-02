import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatch {

    public static void main(String[] args) {
       // String msg = "{\"ts\":\"1463440475\", \"asd\":\"dkalsdasl\"}";
        //String msg = "[Thu Jun 09 01:18:10 2016] [error] [client 69.181.141.46] DBSTATUS MYSQL = New Connection Should Be Initialized [HOST:qa]";
        //String msg = "[Tue May 31 21:36:29 2016] [error] [client 69.181.141.46] DATA:{}|Stats:abc [HOST:qa]";
        String msg = "[Fri Jun 10 20:42:37 2016] [error] [client 69.181.141.46] DATA:{\"log_id\":\"w2.aws.sg.saavn.com:4421:1480438452.5844\", \"icookie\":\"G6aAloAvdueW8NVf3e6uWG7nXGO+7UQ9Y5gioZ6h8EAiGxtz4HzvNkh7s0XL2KeO8BtR0HoM+n91GvmOBrQ\\/fplwzQPhJIlgJJCQc21X\\/9YfBRh+N4chzlZGafoFpQSpaupi9pnq56CFfplgnsEyxNlz5CUYDWG4Cl4"
        + "LamgjC8mg2KdSXwUqYCSQkHNtV\\/\\/WNnQHlceYUuBfraEj+FvuZ\\/a0gmeWC79wcT73JPRz3cdncqoxqlsh8iCasO0HwhgKAdQBOFAl8krcL8B7A6Ohww==\",\"user\":\"milindv@saavn.com\",\"saavn_uid\":\"milindv797\",\"ev\":\"site:inbox:empty_no_data:ui:view\",\"_t\":\"1465591357695\","
        + "\"B\":\"777396adc2bda96d5b1357c8f98793a9\",\"__gads\":\"ID=57d410d05357fab9:T=1455662830:S=ALNI_Ma7AXdOChiPgMaDmMU2vslftso_8Q\",\"__qca\":\"P0-1394393251-1456986179352\",\"_ga\":\"GA1.2.523413368.1454116465\",\"P\":\"trial:1477182436\",\"geo\":"
        + "\"69.181.141.46,US,California,Palo Alto,94301\",\"L\":\"marathi\",\"__utma\":\"106028931.934921013.1454636990.1465516326.1465587877.18\",\"__utmb\":\"106028931.11.10.1465587971\",\"__utmcig\":\"106028931\",\"__utmz\":\"106028931.1454636990.1.1.utmcsr"
        + "=(direct)|utmccn=(direct)|utmcmd=(none)\",\"referer\":\"http:\\/\\/qa.saavn.com\\/new-releases\",\"ip\":\"69.181.141.46\"}|Stats:, referer: http://qa.saavn.com/new-releases [HOST:qa]";
        /*String msg = "[24-Jul-2017 14:39:46 America/Los_Angeles] [error] [client 69.181.141.46] DATA:{\"log_id\":\"w2.aws.sg.saavn.com:4421:1480438452.5844\", \"icookie\":\"G6aAloAvdueW8NVf3e6uWG7nXGO+7UQ9Y5gioZ6h8EAiGxtz4HzvNkh7s0XL2KeO8BtR0HoM+n91GvmOBrQ\\/fplwzQPhJIlgJJCQc21X\\/9YfBRh+N4chzlZGafoFpQSpaupi9pnq56CFfplgnsEyxNlz5CUYDWG4Cl4"
        + "LamgjC8mg2KdSXwUqYCSQkHNtV\\/\\/WNnQHlceYUuBfraEj+FvuZ\\/a0gmeWC79wcT73JPRz3cdncqoxqlsh8iCasO0HwhgKAdQBOFAl8krcL8B7A6Ohww==\",\"user\":\"milindv@saavn.com\",\"saavn_uid\":\"milindv797\",\"ev\":\"site:inbox:empty_no_data:ui:view\",\"_t\":\"1465591357695\","
        + "\"B\":\"777396adc2bda96d5b1357c8f98793a9\",\"__gads\":\"ID=57d410d05357fab9:T=1455662830:S=ALNI_Ma7AXdOChiPgMaDmMU2vslftso_8Q\",\"__qca\":\"P0-1394393251-1456986179352\",\"_ga\":\"GA1.2.523413368.1454116465\",\"P\":\"trial:1477182436\",\"geo\":"
        + "\"69.181.141.46,US,California,Palo Alto,94301\",\"L\":\"marathi\",\"__utma\":\"106028931.934921013.1454636990.1465516326.1465587877.18\",\"__utmb\":\"106028931.11.10.1465587971\",\"__utmcig\":\"106028931\",\"__utmz\":\"106028931.1454636990.1.1.utmcsr"
        + "=(direct)|utmccn=(direct)|utmcmd=(none)\",\"referer\":\"http:\\/\\/qa.saavn.com\\/new-releases\",\"ip\":\"69.181.141.46\"}|Stats:, referer: http://qa.saavn.com/new-releases [HOST:qa]";
        */
        //String msg = "[Fri Jun 10 22:57:46 2016] [error] [client 69.181.141.46] PHP Notice:  Undefined index: uid in /var/www/html/profile_view.inc on line 267, referer: http://qa.saavn.com/s/playlist/milindv797/TamilMelodies/JthCfNkq6NM_ [HOST:qa]";
        String ptnStr = "\"ts\":\"(\\d*)\"";
        String hostPtrn = "\\[HOST:(\\w*)\\]";
        String tsPattern = "\\[(\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} \\d{4})+\\]";
        //String TIME_STAMP_REGEX = "^\\[([a-zA-Z0-9:\\s\\-]*)\\]";
        String TIME_STAMP_REGEX = "^\\[([a-zA-Z0-9:\\s-/_]*)\\]";
        //tsPattern = "\\[([^\\]]*)\\]";
        //tsPattern = "(?i)\\[(\\w{3} \\w{3})+.*\\]";
        System.out.println("Message : " + msg);
        System.out.println("Pattern : " + ptnStr);
        System.out.println("Pattern Host : " + hostPtrn);
        System.out.println("Pattern TS : " + tsPattern);

        Pattern ptrn = Pattern.compile(ptnStr, Pattern.UNIX_LINES);
        Matcher m = ptrn.matcher(msg);

        Pattern ptrnH = Pattern.compile(hostPtrn, Pattern.UNIX_LINES);
        Matcher mh = ptrnH.matcher(msg);

        Pattern ptrnT = Pattern.compile(tsPattern, Pattern.UNIX_LINES);
        Matcher mt = ptrnT.matcher(msg);


        Pattern prodP = Pattern.compile(TIME_STAMP_REGEX, Pattern.UNIX_LINES);
        Matcher mpt = prodP.matcher(msg);

        if(m.find()) {
            String ts = m.group(1);
            if(ts != null) {
                System.out.println("TS PTN: " + ts);
            }
        }

        if (mt.find()) {
            String tsValue = mt.group(1);
            if (tsValue != null) {
                System.out.println("TS : " + tsValue);
            }
        }

        if (mh.find()) {
            String host = mh.group(1);
            if (host != null) {
                System.out.println("HOST : " + host);
            }
        }

        if (mpt.find()) {
            String pts = mpt.group(1);
            if (pts != null) {
                System.out.println("TIMESTAMP : " + pts);
            }
        }
        String logIdPattern = "\"log_id\":\"(.*?)\"";
        Pattern ptrnl = Pattern.compile(logIdPattern, Pattern.UNIX_LINES);
        Matcher mtl = ptrnl.matcher(msg);
        if (mtl.find()) {
            String logId  = mtl.group(1);
            if (logId != null) {
                System.out.println("LOG ID : " + logId);
            }
        }
    }
}
