/*    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
*
*    Note:
*
*    The same word in the dictionary may be reused multiple times in the segmentation.
*    You may assume the dictionary does not contain duplicate words.
*    Example 1:
*
*    Input: s = "leetcode", wordDict = ["leet", "code"]
*    Output: true
*    Explanation: Return true because "leetcode" can be segmented as "leet code".
*    Example 2:
*
*    Input: s = "applepenapple", wordDict = ["apple", "pen"]
*    Output: true
*    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
*                 Note that you are allowed to reuse a dictionary word.
*    Example 3:
*
*    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
*    Output: false
*/

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class WordBreak {
    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<String>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        WordBreak wb = new WordBreak();
        System.out.println("Result : " + wb.wordBreak("catsandog", wordDict));
        System.out.println("Result : " + wb.wordBreakMem("catsandog", wordDict));
        System.out.println("Result : " + wb.wordBreakBFS("catsandog", wordDict));
        System.out.println("Result : " + wb.wordBreakDyn("catsandog", wordDict));
    }

    /**
     *  We are checking each prefix to check if is it a valid word, and
     *  we call function with same process on remaining string.
     *  So if we have some prefix which is valid word and subsequent calls to same function are also
     *  look good, we found a valid division
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<String>(wordDict);
        return workBreakHelper(s, words,0);
    }

    public boolean workBreakHelper(String s, Set<String> words, int start) {
        if(start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); ++end ) {
            if(words.contains(s.substring(start, end )) && workBreakHelper(s, words, end)) {
                return true;
            }
        }
        return false;
    }


    /**
     * We record the processing from the previous iterations so as to avoid rework is we encounter same path in
     * subsequent iterations.
     */
    public boolean wordBreakMem(String s, List<String> wordDict) {
        Set<String> words = new HashSet<String>(wordDict);
        return workBreakHelperMem(s, words,0, new Boolean[s.length()]);
    }

    public boolean workBreakHelperMem(String s, Set<String> words, int start,  Boolean [] valids) {
        if(start == s.length()) {
            return true;
        }

        if(valids[start] != null ) {
            return valids[start];
        }

        for (int end = start + 1; end <= s.length(); ++end ) {
            if(words.contains(s.substring(start, end )) && workBreakHelperMem(s, words, end, valids)) {
                return valids[start] = true;
            }
        }
        return valids[start] = false;
    }

    /*
     *  We start from beginning and check upto what index a substring is present in dictionary.
     *  We maintain these index in a queue.
     *  So with index start there can be 2 different end1 and end2 such that start-end1 and start-end2 strings are valid.
     *  No worries we know there is at least 1 valid substring from start. So we add both end1 and end2 in queue indicating that we need
     *  to explore string from these 2 indexes. At the same time we mark start as visited.
     *  Next iteration end1 is removed from the queue and processed to find subsequent indexes upto which valid substrings are possible.
     *  Meanwhile if we encounter end index to be matching string length we have valid result.
     *
     *  In case we don't find end index from a start index which is leading to a valid substring, queue will be empty hence no valid
     *  division is possible.
     *
     */
    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> words = new HashSet<String>(wordDict);
        Deque<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.offer(0);
        while(!queue.isEmpty()) {
            int start = queue.poll();
            if(!visited[start]) {
                for (int end = start  + 1; end <= s.length() ; ++end ) {
                    if(words.contains(s.substring(start, end))) {
                        queue.offer(end);
                        if(end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = false;
            }
        }
        return false;
    }

/*
 * The intuition behind this approach is that the given problem (ss) can be divided into subproblems s1s1 and s2s2. If these subproblems individually satisfy the required conditions, the complete problem, ss also satisfies the same.
 * e.g. "catsanddog" can be split into two substrings "catsand", "dog". The subproblem "catsand" can be further divided into "cats","and", which individually are a part of the dictionary making "catsand" satisfy the condition.
 * Going further backwards, "catsand", "dog" also satisfy the required criteria individually leading to the complete string "catsanddog" also to satisfy the criteria.

 *   Now, we'll move onto the process of dp array formation. We make use of dp array of size n+1, where n is the length of the given string.
 *   We also use two index pointers i and j, where i refers to the length of the substring (s') considered currently starting from the beginning, and j refers to the index partitioning the current substring (s′)
 *   into smaller substrings s'(0,j) and s'(j+1,i). To fill in the dp array, we initialize the element dp[0] as true, since the null string is always present in the dictionary, and the rest of the elements of dp as false.
 *   We consider substrings of all possible lengths starting from the beginning by making use of index ii. For every such substring,
 *   we partition the string into two further substrings s1′ and s2′ in all possible ways using the index j (Note that the i now refers to the ending index of s2′).
 *   Now, to fill in the entry dp[i], we check if the dp[j] contains true, i.e. if the substring s1′ fulfills the required criteria. If so, we further check if s2' is present in the dictionary.
 *   If both the strings fulfill the criteria, we make dp[i] as true, otherwise as false..
 */

 public boolean wordBreakDyn(String s, List<String> wordDict) {
        Set<String> words = new HashSet<String>(wordDict);
        //tracking has length 1 more than actual length of the string
        boolean[] endingAt = new boolean[s.length() + 1 ];

        //Null string is always present in a string and in the words set as well
        endingAt[0] = true;

        //checking the string ending at i
        for (int i = 1; i <= s.length(); ++i) {
            //From starting to i check if strings can be divided into 2 words which are valid
            for(int j = 0; j < i; ++j) {
                // meaning one ending at j and other from j to i
                // check all possible j positions upto i
                if(endingAt[j] && words.contains(s.substring(j, i))){
                    //if one such division is found then we know that string upto i
                    //can be divided into valid words
                    endingAt[i] = true;
                    //we just want one such partition
                    //to check that such a division is possible
                    break;
                }
            }
        }
        // return final finding present at the end of the array
        return endingAt[s.length()];
    }
}

