/*
 *  Minimum Window Substring problem
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String pattern = sc.nextLine();
        /*
         * String input = "asdbsdcsdleavbfc"; String input = "asdbsdcsdleavbfca"; String
         * pattern = "abc";
         */

        // Exhaustive, considering all windows
        getMinWindowSubstring(input, pattern);

        // Smarter
        String smallestSubSet = findSmallestContainerSubStringSimpler(input, pattern);
        System.out.println("Smallest Container Found : " + smallestSubSet);
    }

    /**
     * Start from the beginning and consider all windows of length 1,2...upto end of
     * the string. Perform this for every index in the string. Check for each window
     * if the characters from pattern string appear and also the frequency matches
     * Find minimum such window
     */
    public static void getMinWindowSubstring(String candidate, String pattern) {

        int minWindowLen = Integer.MAX_VALUE;
        String minWindow = null;
        if (candidate != null && pattern != null) {
            int cLen = candidate.length();
            int pLen = pattern.length();

            // Map to track character and its frequency as
            // appearing in pattern string. This will be used
            // as a reference
            Map<Character, Integer> tracker = new HashMap<>();
            for (int i = 0; i < pLen; i++) {
                int frequency = tracker.getOrDefault(pattern.charAt(i), 0);
                tracker.put(pattern.charAt(i), frequency + 1);
            }

            // Start from each character in the String
            for (int i = 0; i < cLen; ++i) {
                // Window size 1, 2 ...till the end of String
                for (int j = i; j + i < cLen; ++j) {

                    // Fetch the current substring window
                    String window = candidate.substring(i, j + 1);
                    // Check if window contains all the characters along with the
                    // frequency
                    boolean isValidWindow = isWindowContainingAllChars(window, tracker);
                    if (isValidWindow) {
                        minWindowLen = Math.min(minWindowLen, window.length());
                        minWindow = window;
                    }
                }
            }
        }

        System.out.println("Min Window length : " + minWindowLen + " Window : " + minWindow);
    }

    /**
     * Helper function to check if the character and frequency matches with the
     * pattern
     *
     */
    public static boolean isWindowContainingAllChars(String window, Map<Character, Integer> tracker) {
        Map<Character, Integer> currWindowStatus = new HashMap<>();

        for (int i = 0; i < window.length(); i++) {
            int frequency = currWindowStatus.getOrDefault(window.charAt(i), 0);
            currWindowStatus.put(window.charAt(i), frequency + 1);
        }

        for (char k : tracker.keySet()) {
            int frequency = currWindowStatus.getOrDefault(k, 0);
            if (frequency == 1) {
                currWindowStatus.remove(k);
            } else {
                currWindowStatus.put(k, frequency - 1);
            }
        }

        return currWindowStatus.size() == 0;
    }

    public static String findSmallestContainerSubStringSimpler(String candidate, String pattern) {
        // Basic corner cases
        if (candidate == null || pattern == null || pattern.length() > candidate.length()) {
            return "";
        }

        // Create map of the character and frequency from patten string
        Map<Character, Integer> patternMap = new HashMap<Character, Integer>();
        for (int i = 0; i < pattern.length(); ++i) {
            char currentChar = pattern.charAt(i);
            int frequency = patternMap.getOrDefault(currentChar, 0);
            patternMap.put(currentChar, frequency + 1);
        }

        // Count of required unique characters
        int requiredCharCount = patternMap.size();
        // Keep track of matched elements to compare with requiredCharCount
        // But only increment this one when frequency of char also matches with
        // that in the patternMap
        int candidateCount = 0;
        // Sliding window limits
        int left = 0, right = 0;
        // Details of the output string
        int length = -1, starting = 0, ending = 0;
        // Keep track of all characters which are not part of pattern when the scanning
        // of
        // candidate begins. No point in including any extra character initially when
        // left = 0
        // if it is not in the pattern
        int initialCount = 0;

        // In the very beginning move left to a position where first char
        // from the pattern is encountered
        while (!patternMap.containsKey(candidate.charAt(left))) {
            left++;
            right++;
        }

        // Map for candidate characters
        Map<Character, Integer> candidateMap = new HashMap<Character, Integer>();
        // Check until right hits the end of candidate string
        while (right < candidate.length()) {
            char currentChar = candidate.charAt(right);

            // Process only when char match is found
            if (patternMap.containsKey(currentChar)) {
                // Start creating the map of the characters from the Candidate string
                int frequency = candidateMap.getOrDefault(currentChar, 0);
                candidateMap.put(currentChar, frequency + 1);
                // Check if a character from the candidate string is present in the pattern and
                // the frequency matches too. Increment the count for matched candidates
                if (candidateMap.get(currentChar) == patternMap.get(currentChar)) {
                    candidateCount++;
                }
            }

            while (left <= right && requiredCharCount == candidateCount) {
                if (length == -1 || right - left + 1 < length) {
                    length = right - left + 1;
                    starting = left;
                    ending = right;
                }

                // Start decreasing the window from left side
                char currentLeftMostChar = candidate.charAt(left);
                if (patternMap.containsKey(currentLeftMostChar)) {
                    // Reduce the frequency count from the candidate map for this particular
                    // character obtained
                    candidateMap.put(currentLeftMostChar, candidateMap.get(currentLeftMostChar) - 1);
                    // if this does not match with the frequency from the pattern map, then reduce
                    // candidate match count
                    if (candidateMap.get(currentLeftMostChar) < patternMap.get(currentLeftMostChar)) {
                        candidateCount--;
                    }
                }
                left++;
            }
            // Keep expanding the right side of the window
            right++;
        }
        return length == -1 ? "" : candidate.substring(starting, ending + 1);
    }

}
