
/**
 *      Given a string, find the length of the longest substring without repeating characters.
 *
 *      Example 1:
 *
 *      Input: "abcabcbb"
 *      Output: 3
 *      Explanation: The answer is "abc", with the length of 3.
 *      Example 2:
 *
 *      Input: "bbbbb"
 *      Output: 1
 *      Explanation: The answer is "b", with the length of 1.
 *      Example 3:
 *
 *      Input: "pwwkew"
 *      Output: 3
 *      Explanation: The answer is "wke", with the length of 3.
 *            Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
import java.util.Set;
import java.util.HashSet;

public class LongestSubStringwithUniqueChars {
    public static void main(String[] args) {
        String s = args[0];
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstringLinear(s));
        System.out.println(lengthOfLongestSubstringLinearIndex(s));
    }

    /*
     * O(n^3) solution and also O(n) space Brute force Checks for all possible
     * substrings and then uniqueness
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLen = -1;
        int len = 0;
        if (s != null) {
            maxLen = 1;//min length is 1 char
            len = s.length();
            for (int i = 0; i < len; ++i) {
                for (int j = i + 1; j < len; ++j) {
                    if (hasUniqueChars(s, i, j)) {
                        maxLen = Math.max(maxLen, j - i + 1);
                        System.out.println(" i : " + i + " j " + j);
                    }
                }
            }
        }
        return maxLen;
    }

    public static boolean hasUniqueChars(String s, int i, int j) {
        Set<Character> charSet = new HashSet<Character>();
        while (i <= j) {//Need to compare all characters so <=
            char curr = s.charAt(i);
            if (charSet.contains(curr)) {
                return false;
            }
            charSet.add(curr);
            i++;
        }
        return true;
    }

    /*
     * implementation with sliding window. As long as we encounter unique character
     * increment j and add the char to the set. When we have duplicate char, start
     * deleting char at i from set. Eventually it will delete all the chars from set
     * till is matches j and the procedure repeats we record the length when we add
     * a char to set
     */
    public static int lengthOfLongestSubstringLinear(String s) {
        int len = s.length();
        Set<Character> unique = new HashSet<>();
        int i = 0, j = 0, maxlen = 0;
        while (i < len && j < len) {
            if (unique.contains(s.charAt(j))) {
                unique.remove(s.charAt(i++));
            } else {
                unique.add(s.charAt(j++));
                maxlen = Math.max(maxlen, j - i);
            }
        }
        return maxlen;
    }

    /*
     * This involves no set. We just play with the indexes. We again have a starting
     * index and current index. Initially both start with 0.
     * If char at curr index is found between starting and curr index,
     * then it is repeating one. So we increment starting index to 1 more than this
     * found occurrence of the char. And continue. When not found we record the
     * length of the string obtained so far
     */
    public static int lengthOfLongestSubstringLinearIndex(String s) {
        int start = 0;
        int end = 0;
        int maxLen = 0;
        if (s != null) {
            while (start <= end && end < s.length()) {
                char endChar = s.charAt(end);
                int index = s.indexOf(endChar, start);
                if (index >= start && index < end) {
                    maxLen = Math.max(maxLen, end - start);
                    // Adjust the start to shrink the sliding window
                    // start with the next character where the previous occurrence
                    // of current end char
                    start = index + 1;// We have non unique character at this index
                }
                end++;
            }
        }
        maxLen = Math.max(maxLen, end - start);
        return maxLen;
    }
}
