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
        String[] counts = {
            "900,google.com",
            "60,mail.yahoo.com",
            "10,mobile.sports.yahoo.com",
            "40,sports.yahoo.com",
            "300,yahoo.com",
            "10,stackoverflow.com",
            "2,en.wikipedia.org",
            "1,es.wikipedia.org" };

        //Separate each given domain and the count
        Map<String, Integer> inputSeparated = separateDomainAndCount( counts );
        //Process each domain to obtain basic domains to look for
        Set<String> domains = getDomains( inputSeparated.keySet() );
        //Search obtained basic domain in the input and check for presence
        //add the frequency encountered
        for( String uniqueDomain : domains ) {
            int count = 0;
            for(String key : inputSeparated.keySet()) {
                if( key.contains( uniqueDomain ) ) {
                    count += inputSeparated.get( key );
                }
            }
            System.out.println( count + "  " + uniqueDomain );
        }
    }


  /**
   *  Consider all the input domains and create set of required domains
   *  call helper function to process each candidate domain string
   */
  public static Set<String> getDomains( Set<String> keys ) {
    Set<String> validDomains = new HashSet<String>();
    for (String source : keys ) {
        System.out.println("Current Input domain : " + source);
        List<String> currentValidDomains = getCurrentValidDomains( source );
        //Using streams and internal iterator
        currentValidDomains.stream()
            .forEach(validDomains::add);//Method reference
    }
    return validDomains;
  }

  /**
   *  candidate: mail.yahoo.com
   *  Output :  mail.yahoo.com
   *            yahoo.com
   *            com
   */
    public static List<String> getCurrentValidDomains( String singleSource ) {
        List<String> domains = new ArrayList<String>();
        int index  = 0;
        while(index >= 0) {
            if(index != 0) {
                index++;
            }
            String currentDomain = singleSource.substring(index );
            domains.add(currentDomain);
            System.out.println("C D : " + currentDomain);
            index = singleSource.indexOf(".", index  + 1);
        }
        return domains;
  }

  /**
   * Separated the frequency from the name of the domain
   * and stored them as key value pair
   * where domain is the key and the value is the frequency
   */
  public static Map<String, Integer> separateDomainAndCount( String[] input ) {
      Map<String, Integer> inputDomainToCount = new HashMap<String, Integer>();
      for(String i : input ) {
          String[] inputSplit = i.split(",");
          if(inputSplit.length == 2) {
              String domain = inputSplit[ 1 ];
              int count = Integer.valueOf( inputSplit[ 0 ] );
              System.out.println("Domain : " + domain + " -- " + "Count : " + count);
              inputDomainToCount.put( domain, count );
          }
      }
      return inputDomainToCount;
  }
}

