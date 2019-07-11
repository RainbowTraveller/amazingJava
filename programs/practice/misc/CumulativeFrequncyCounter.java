import java.io.*;
import java.util.*;

/*
 Write a function that takes this input as a parameter and returns a data structure containing the number of hits that were recorded on each domain AND each domain under it. For example, an impression on "sports.yahoo.com" counts for "sports.yahoo.com", "yahoo.com", and "com". (Subdomains are added to the left of their parent domain. So "sports" and "sports.yahoo" are not valid domains.)
Expected output (in any order):
1320    com
 900    google.com
 410    yahoo.com
  60    mail.yahoo.com
  10    mobile.sports.yahoo.com
  50    sports.yahoo.com
  10    stackoverflow.com
   3    org
   3    wikipedia.org
   2    en.wikipedia.org
   1    es.wikipedia.org
 */

public class CumulativeFrequncyCounter {
    public static void main(String[] args) {
        String[] counts = { "900,google.com",
                            "60,mail.yahoo.com",
                            "10,mobile.sports.yahoo.com",
                            "40,sports.yahoo.com",
                            "300,yahoo.com",
                            "10,stackoverflow.com",
                            "2,en.wikipedia.org",
                            "1,es.wikipedia.org" };

        Map<String, Integer> domains = new HashMap();
        for (String domain : counts) {
            // String[] split = domain.split("\\s+");
            String[] split = domain.split(",");

            String candidate = split[1];
            int count = Integer.valueOf(split[0]);
            int dotIndex = 0;
            while (dotIndex >= 0) {
                String currDomain = candidate.substring(dotIndex == 0 ? dotIndex : dotIndex + 1);
                domains.put(currDomain, domains.getOrDefault(currDomain, 0) + count);
                dotIndex = candidate.indexOf('.', dotIndex + 1);
            }
        }

        for (String dom : domains.keySet())
            System.out.println(" " + domains.get(dom) + " " + dom);
    }
}
