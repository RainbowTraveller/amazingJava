/*
* Write a function to find the longest common prefix string amongst an array of strings.
*
* If there is no common prefix, return an empty string "".
*
* Example 1:
*
* Input: ["flower","flow","flight"]
* Output: "fl"
* Example 2:
*
* Input: ["dog","racecar","car"]
* Output: ""
* Explanation: There is no common prefix among the input strings.
*/

public class LongestCommonPrefix {
	public static void main(String[] args) {
		String[] input = { "flower", "flow", "flight" };
		System.out.println("L C prefix : " + longestCommonPrefix(input));
	}

	public static String longestCommonPrefix(String[] strs) {
		StringBuffer buff = new StringBuffer();
		if (strs != null && strs.length >= 1) {
			int index = 0;
			boolean process = true;
			while (process) {
				if (index >= strs[0].length()) {
					process = false;
				} else {
					char c = strs[0].charAt(index);
					for (int i = 1; i < strs.length; ++i) {
						if (index >= strs[i].length() || strs[i].charAt(index) != c) {
							return buff.toString();
						}
					}
					buff.append(c);
					index++;
				}
			}
		}
		return buff.toString();
	}
}
