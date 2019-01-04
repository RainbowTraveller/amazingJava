import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class SmallestPatternSubSet {

    public static void main(String [] args ) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String pattern = sc.nextLine();
        /*String input = "asdbsdcsdleavbfc";
        String input = "asdbsdcsdleavbfca";
        String pattern = "abc";*/

        String smallestSubSet = findSmallestContainerSubString( input, pattern );
        System.out.println("Smallest Container Found : " + smallestSubSet);
    }

    public static String findSmallestContainerSubString(String candidate, String pattern ) {
        String minString = null;
        if(candidate != null && pattern != null && pattern.length() < candidate.length() ) {
            int index = 0;
            while(index < candidate.length()) {
                char candidateChar = candidate.charAt( index );
                if(pattern.indexOf(candidateChar) >= 0) {
                    //Call a function to get the container string length
                    String  currString = getContainerStringLength(candidate, pattern, index);
                    System.out.println(" Current String : " + currString);
                    if( minString == null || ( currString != null &&  currString.length() < minString.length() ) ) {
                        minString = currString;
                    }
                }
                index++;
            }
        }
        return minString;
    }

    public static String getContainerStringLength( String candidate, String pattern, int index ) {
        Map<Character, Integer> trackerMap = new HashMap<Character, Integer>();
        StringBuffer buff = new StringBuffer();
        System.out.println("INDEX VALUE : " + index );
        while(index < candidate.length()) {
            //if chat at index is present in the pattern
            //add it to the map and increment the occurrence count
            //when length of the map equals pattern length, desired container string is
            //obtained
            char currentChar = candidate.charAt(index);
            buff.append(currentChar);
            if( pattern.indexOf(currentChar) >= 0 ) {
                int frequency = 0;
                if(trackerMap.containsKey(currentChar)) {
                    frequency = trackerMap.get(currentChar);
                }
                frequency++;
                trackerMap.put( currentChar, frequency );
                if(trackerMap.size() == pattern.length()) {
                    return buff.toString();
                }
            }
            index++;
        }
        return null;
    }
}
