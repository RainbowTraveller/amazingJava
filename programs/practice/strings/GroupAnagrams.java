/* Given an array of strings, group anagrams together.
 */
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Arrays;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] input = { "eat", "tea", "tan", "ate", "nat", "bat" };
        Map<String, List<String>> tracker = new HashMap<String, List<String>>();
        for (String s : input) {
            char[] key = s.toCharArray();
            Arrays.sort(key);
            String strKey = new String(key);
            List<String> group = tracker.getOrDefault(strKey, new LinkedList<String>());
            group.add(s);
            tracker.put(strKey, group);
        }
        List<List<String>> groups = new LinkedList(tracker.values());
        System.out.println(" Group Anagrams : " + groups);
    }

}
