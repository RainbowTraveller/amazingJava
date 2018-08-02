import java.util.Set;
import java.util.HashSet;
import java.lang.StringBuffer;

/**
 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */
public class UniqueSubStr {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        Set<Character> unique = new HashSet<Character>();
        StringBuffer sb = new StringBuffer();
        int oldCount = 0;
        int currCount = 0;
        String oldStr = null;
        String newStr = null;
        for(int i=0; i < len; ++i) {
            char curr = s.charAt(i);
            if(unique.contains(curr)) {
                if(oldCount < currCount) {
                    oldStr = sb.toString();
                    oldCount = currCount;
                }
                //oldCount = (oldCount > currCount ? oldCount : currCount);
                sb.delete(0, sb.length());
                unique.clear();
                currCount = 1;
            } else {
                currCount++;
            }
            sb.append(curr);
            unique.add(curr);
        }
        oldCount = (oldCount > currCount ? oldCount : currCount);
        if(oldCount > currCount) {
            System.out.println(oldStr);
        } else {
            System.out.println(sb.toString());
        }
        return oldCount;
    }

    public static void main(String args[]) {
        String str = args[0];
        UniqueSubStr us = new UniqueSubStr();
        System.out.println(us.lengthOfLongestSubstring(str));
    }
}
