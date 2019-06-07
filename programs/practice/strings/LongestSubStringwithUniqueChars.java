/**
 *		Given a string, find the length of the longest substring without repeating characters.
 *
 *		Example 1:
 *
 *		Input: "abcabcbb"
 *		Output: 3
 *		Explanation: The answer is "abc", with the length of 3.
 *		Example 2:
 *
 *		Input: "bbbbb"
 *		Output: 1
 *		Explanation: The answer is "b", with the length of 1.
 *		Example 3:
 *
 *		Input: "pwwkew"
 *		Output: 3
 *		Explanation: The answer is "wke", with the length of 3.
 *            Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
import java.util.Set;
import java.util.HashSet;

public class LongestSubStringwithUniqueChars {
	public static void main(String [] args) {
		String s = args[0];
		System.out.println(lengthOfLongestSubstring(s));
		System.out.println(lengthOfLongestSubstringLinear(s));
		System.out.println(lengthOfLongestSubstringLinearIndex(s));
	}

	/*
	 * O(n^2) solution and also O(n) space
	 */
	public static int lengthOfLongestSubstring(String s) {
        Set<Character> tracker  = new HashSet<Character>();
        int len = 0;
        if(s != null) {
			//Start with each index from 0
            for(int j = 0; j < s.length(); ++j) {
				//look for string with unique characters
                for(int i = j; i < s.length(); ++i) {
                    char currentChar = s.charAt(i);
					//if this character is already in the set, then longest string
					//starting at given index i is obtained
                    if(tracker.contains(currentChar)) {
                        if(len < tracker.size()) {
							//if set size hence size of the string with unique chars
							//so far is greater than previous such obtained
							//replace it with new size
                            len = tracker.size();
                        }
						//Clear the set
                        tracker.clear();
                    }
					//another unique character found, add to set
                    tracker.add(currentChar);
                }
				//Stop, don't go anywhere, check the size of last set obtained
                if(len < tracker.size()) {
                    len = tracker.size();
                }
                tracker.clear();
            }
        }
        return len;
    }

	/*
	 * Using sliding window, current valid string is between start and
	 * end
	 */
	public static int lengthOfLongestSubstringLinear(String s) {
		//Current iteration length
		int len = 0;
        int start = 0;
        int end = 0;
		//Max length obtained so far
        int maxLen = 0;
        if(s != null) {
			//Keep looking till start is < end and end is not yet
			//equal to end of the string
            while(start <= end && end < s.length()) {
				//Character at the end is new char to be added to window
                char endChar = s.charAt(end);
				//Check if same character exists from start( inclusive )
                int index = s.indexOf(endChar, start);
                if(index >= 0 && index < end) {
					//Yes...! meaning this character at end makes string
					//not qualified aka without unique characters
                    int count = 0;
					//So backup length obtained so far if it is greater than maxLen
                    maxLen = Math.max(maxLen, len);
					//From start check all the characters not equal to endChar
                    while(start < end && s.charAt(start) != endChar) {
						//get char count
                        count++;
						//Shrink sliding window
                        start++;
                    }
					//Shrink sliding window by 1 more to exclude character found
					//which is the index
                    start += 1;
					// and also characters to be skipped
                    count++;
					//Adjust length by removing count no. of characters
					//but also add 1 for the current end character obtained
                    len = len - count + 1;
                } else {
					//Unique character, increase length
                    len++;
                }
                end++;
            }
        }
		//final value of maxLen, return it
        maxLen = Math.max(maxLen, len);
        return maxLen;
	}

	public static int lengthOfLongestSubstringLinearIndex(String s) {
        int start = 0;
        int end = 0;
        int maxLen = 0;
        if(s != null) {
            while(start <= end && end < s.length()) {
                char endChar = s.charAt(end);
                int index = s.indexOf(endChar, start);
                if(index >= start && index < end) {
                    maxLen = Math.max(maxLen, end - start);
					//Adjust the start to shrink the sliding window
					//start with the next character where the previous occurrence
					//of current end char
                    start = index + 1;
					//if index = 3, characters are 4 but we already
					//have end character added so reduce length
					//by index value which is perfect
                }
                end++;
            }
        }
        maxLen = Math.max(maxLen, end - start);
        return maxLen;
    }
}
