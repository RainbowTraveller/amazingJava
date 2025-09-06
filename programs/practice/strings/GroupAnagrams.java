/* Given an array of strings, group anagrams together.
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
  /**
   * The logic is to sort each string and use the sorted string as a key in a hashmap. The value is
   * a list of strings that are anagrams of the key. Finally, we return the values of the hashmap as
   * a list of lists.
   *
   * <p>Time Complexity: O(N * K log K) where N is the number of strings and K is the maximum length
   * of a string.
   *
   * <p>Space Complexity: O(N * K) for the hashmap.
   */
  public static void main(String[] args) {
    String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
    Map<String, List<String>> tracker = new HashMap<String, List<String>>();
    for (String s : input) {
      // sort the string
      char[] key = s.toCharArray();
      // sort the char array
      Arrays.sort(key);
      // convert back to string
      String strKey = new String(key);
      // get the list of anagrams for the sorted string
      List<String> group = tracker.getOrDefault(strKey, new LinkedList<String>());
      // add the original string to the list
      group.add(s);
      // put the list back in the hashmap
      tracker.put(strKey, group);
    }
    List<List<String>> groups = new LinkedList(tracker.values());
    System.out.println(" Group Anagrams : " + groups);
  }
}
