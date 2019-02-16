/*
 *  Minimum Windowing Substring problem
 *  Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 *   Example:
 *
 *   Input: S = "ADOBECODEBANC", T = "ABC"
 *   Output: "BANC"
 *   Note:
 *
 *   If there is no such window in S that covers all characters in T, return the empty string "".
 *   If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
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
        //Basic corner cases
        if(candidate == null || pattern == null || pattern.length() > candidate.length()) {
            return "";
        }

        //Create map of the character and frequency from patten string
        Map<Character, Integer> patternMap = new HashMap<Character, Integer>();
        for(int i = 0; i < pattern.length(); ++i) {
            char currentChar = pattern.charAt(i);
            int frequency = patternMap.getOrDefault(currentChar, 0);
            patternMap.put(currentChar, frequency + 1);
        }

        //Count of required unique characters
        int requiredCharCount = patternMap.size();
        int candidateCount = 0;
        //Sliding window limits
        int left = 0, right = 0;
        //Details of the output string
        int length = 0, starting = 0, ending = 0;

        //Map for candidate characters
        Map<Character, Integer> candidateMap = new HashMap<Character, Integer>();
        while(right < candidate.length()) {
            //Start creating the map of the characters from the Candidate string
            char currentChar = candidate.charAt(right);
            int frequency = candidateMap.getOrDefault(currentChar, 0);
            candidateMap.put(currentChar, frequency + 1);

            //Check if a character from the candidate string is present in the pattern and
            //the frequency matches too. Increment the count for matched candidates
            if(patternMap.containsKey(currentChar) && candidateMap.get(currentChar) == patternMap.get(currentChar)) {
                candidateCount++;
            }

            while(left <= right && requiredCharCount == candidateCount) {
                if(length == 0 || right - left + 1 < length) {
                    length = right - left + 1;
                    starting = left;
                    ending = right;
                }
                //Start decreasing the window from left side
                char currentLeftMostChar = candidate.charAt(left);
                //Reduce the frequency count from the candidate map for this particular character obtained
                candidateMap.put(currentLeftMostChar, candidateMap.get(currentLeftMostChar) - 1);
                //if this does not match with the frequency from the pattern map, then reduce candidate match count
                if(patternMap.containsKey(currentLeftMostChar) && candidateMap.get(currentLeftMostChar) < patternMap.get(currentLeftMostChar)) {
                    candidateCount--;
                }
                left++;
            }
            //Keep expanding the right side of the window
            right++;
        }
        return candidate.substring(starting, ending + 1);
    }
}
