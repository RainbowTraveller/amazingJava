
/*  Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
*
*  Example 1:
*
*  Input: "eceba"
*  Output: 3
*  Explanation: t is "ece" which its length is 3.
*  Example 2:
*
*  Input: "ccaabbb"
*  Output: 5
*  Explanation: t is "aabbb" which its length is 5.
*/

import java.util.*;

public class LongestSubStringWithAtMostNDistictChars {
    public static void main(String[] args) {

        System.out.println("Input : " + "eceba" + " :: " + lengthOfLongestSubstringTwoDistinct("eceba"));

    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {

        int len = 2;

        if (s.length() < 2) {
            return s.length();
        }

        if (s != null && s.length() >= 2) {
            int left = 0, right = 0;
            Map<Character, Integer> track = new HashMap<>();
            while (right < s.length()) {
                if (track.size() < 3) {
                    //update char and its index in the map
                    //so this will contain latest index of any character
                    track.put(s.charAt(right), right);
                }
                if (track.size() == 3) {
                    //There are now 3 distinct chars that are found
                    //Get the smallest index from the map which indicates one of the 2 chars
                    //already found, Remove it
                    int index = Collections.min(track.values());
                    track.remove(s.charAt(index));
                    left = index + 1;
                }
                right++;
                // System.out.println(" " + left + " :::: " + right + " ");
                //Calculate length every time
                len = Math.max(len, right - left);
            }
        }

        return len;
    }
}
