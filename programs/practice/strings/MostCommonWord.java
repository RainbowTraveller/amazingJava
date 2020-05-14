/*  Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
*
*  Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
*
*
*
*  Example:
*
*  Input:
*  paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
*  banned = ["hit"]
*  Output: "ball"
*  Explanation:
*  "hit" occurs 3 times, but it is a banned word.
*  "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
*  Note that words in the paragraph are not case sensitive,
*  that punctuation is ignored (even if adjacent to words, such as "ball,"),
*  and that "hit" isn't the answer even though it occurs more because it is banned.
*/
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class MostCommonWord {
    public static void main(String[] args) {
        MostCommonWord mcw = new MostCommonWord();
        String para = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        System.out.println("Most Common Unbanned word :" + mcw.mostCommonWord(para, banned));
    }


    public String mostCommonWord(String paragraph, String[] banned) {
        if(paragraph != null && banned != null) {
            Map<String, Integer> tracker = new HashMap<>();
            Set<String> ban = new HashSet<>();
            for(String b : banned ) {
                ban.add(b);
            }
            String[] words = paragraph.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
            for(String word : words) {
                if(!ban.contains(word)) {
                    tracker.put(word, tracker.getOrDefault(word, 0) + 1);
                }
            }

            /*Set<Map.Entry<String,Integer>> entries = tracker.entrySet();
            List<Map.Entry<String,Integer>> wordFrequency = new ArrayList<>(entries);
            Collections.sort(wordFrequency, (a,b) -> (b.getValue() - a.getValue()));
            Map.Entry<String,Integer> highest = wordFrequency.get(0);
            return highest.getKey();*/

            return Collections.max(tracker.entrySet(), Map.Entry.comparingByValue()).getKey();

        }
        return null;
    }
}

