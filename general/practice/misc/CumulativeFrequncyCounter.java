import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named CumulativeFrequncyCounter.
 *
 * If you need more classes, simply define them inline.


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

    List<String> domains = CumulativeFrequncyCounter.getDomains( counts );
    Map<String, Integer> separatedCounts = new HashMap<String, Integer>();

	System.out.println( domains );
	separatedCounts = CumulativeFrequncyCounter.getCounts( separatedCounts, domains, counts );

	System.out.println(separatedCounts);

  }


  public static List<String> getDomains( String [] input ) {
    List<String> sources = new LinkedList<String>();
    for (String source : input ) {
        sources.add( source.split( ",", 2 )[1]);
    }
    return sources;
  }


  public static Map<String, Integer> getCounts(Map<String, Integer> mapping, List<String> domains, String[] originalCounts) {
	Set<String> uniqueKeys = new HashSet<String>();

    for(String domain : domains) {
        StringBuffer searchKey = new StringBuffer();
		//Split the domain name into tokens
		System.out.println("Domain  : ==> " + domain);
        String [] splitTokens = domain.split("\\.");
        int len = splitTokens.length;
		int startIndex = len - 1;
		int endIndex = 1;

		if( len <= 2 ) {
			 endIndex = 0;
		}

		System.out.println("End index : " + endIndex);
        for(int i = startIndex; i >= endIndex ; i--) {
            searchKey.insert(0, splitTokens [ i ]);
			System.out.println(" ");
			System.out.println("Token : " + splitTokens[i] );
            String searchKeyStr = searchKey.toString();
			if(!uniqueKeys.contains(searchKeyStr)) {
				for(String oc : originalCounts) {
				   if( oc.indexOf( searchKeyStr) != -1) {
					  int count = Integer.valueOf(oc.split(",")[0]);
					  if(mapping.containsKey( searchKeyStr)) {
						 int countValue = mapping.get( searchKeyStr );
						 count += countValue;
					  }
					  System.out.println("Search Key : " + searchKeyStr);
					  mapping.put(searchKeyStr, count);
				   }
				}
			}
			searchKey.insert(0, ".");
			uniqueKeys.add(searchKeyStr);
		}
    }
    return mapping;
  }

  public static Map<String, Integer>
}

