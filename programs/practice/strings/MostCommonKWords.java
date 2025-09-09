/**
 * LeetCode 692. Top K Frequent Words https://leetcode.com/problems/top-k-frequent-words/
 *
 * <p>Given an array of strings words and an integer k, return the k most frequent strings. Return
 * the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency
 * by their lexicographical order.
 *
 * <p>Example 1:
 *
 * <p>Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 *
 * <p>Output: ["i","love"]
 *
 * <p>Explanation: "i" and "love" are the two most frequent words.
 *
 * <p>Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * <p>Example 2:
 *
 * <p>Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 *
 * <p>Output: ["the","is","sunny","day"]
 *
 * <p>Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *
 * <p>with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * <p>Constraints:
 *
 * <p>1 <= words.length <= 500
 */
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MostCommonKWords {
  public static void main(String[] args) {

    MostCommonKWords mckw = new MostCommonKWords();
    String[] words = {
      "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "a", "a", "a", "a",
      "a", "a", "b", "b", "b"
    };
    int n = 4;
    System.out.println("Top  " + n + " frequent words : " + mckw.topKFrequent(words, 4));
    System.out.println("Top  " + n + " frequent words : " + mckw.topKFrequentHeap(words, 4));
  }

  /**
   * The logic is as follows:
   *
   * <p>1. Use a TreeMap to count the frequency of each word. TreeMap is used to keep the words in
   * alphabetical order.
   *
   * <p>2. Convert the entry set of the map to a list.
   *
   * <p>3. Sort the list based on the frequency in descending order.
   *
   * <p>4. If the frequency is the same, sort the words in alphabetical order.
   *
   * <p>5. Return the first k words from the sorted list.
   *
   * <p>Time complexity: O(N log N) where N is the number of unique words.
   *
   * <p>Space complexity: O(N) where N is the number of unique words.
   *
   * @param words
   * @param k
   * @return List of top k frequent words
   */
  public List<String> topKFrequent(String[] words, int k) {
    List<String> ans = new LinkedList<>();
    if (words != null && words.length > 0 && k <= words.length) {
      Map<String, Integer> tracker = new TreeMap<String, Integer>();
      for (String word : words) {
        tracker.put(word, tracker.getOrDefault(word, 0) + 1);
      }

      List<Map.Entry<String, Integer>> entries = new LinkedList<>(tracker.entrySet());
      entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

      for (Map.Entry<String, Integer> entry : entries.subList(0, k)) {
        ans.add(entry.getKey());
      }
      /*
       * List<String> candidates = new ArrayList(tracker.keySet()); //Compare the
       * counts, if they are equal then compare the strings for alphabetical order
       * //or descending order of frequency meaning more frequent earlier
       * Collections.sort(candidates, (w1, w2) ->
       * tracker.get(w1).equals(tracker.get(w2)) ? w1.compareTo(w2) : tracker.get(w2)
       * - tracker.get(w1));
       *
       * return candidates.subList(0, k);
       */
    }
    return ans;
  }

  /**
   * The logic is as follows:
   *
   * <p>1. Use a TreeMap to count the frequency of each word. TreeMap is used to keep the words in
   * alphabetical order.
   *
   * <p>2. Use a min-heap (priority queue) to keep track of the top k frequent words.
   *
   * <p>3. The comparator for the priority queue is defined such that if the frequency of two words
   * is the same, the word with the lower alphabetical order comes first. Otherwise, the word with
   * the lower frequency comes first.
   *
   * <p>4. If the size of the heap exceeds k, remove the word with the lowest frequency (or highest
   * alphabetical order if frequencies are the same).
   *
   * <p>5. Finally, extract the words from the heap and add them to the result list in reverse order
   * to get the correct order.
   *
   * <p>Time complexity: O(N log k) where N is the number of unique words.
   *
   * <p>Space complexity: O(N) where N is the number of unique words.
   *
   * @param words
   * @param k
   * @return List of top k frequent words
   */
  public List<String> topKFrequentHeap(String[] words, int k) {

    List<String> ans = new LinkedList<>();
    if (words != null && words.length > 0 && k <= words.length) {
      Map<String, Integer> tracker = new TreeMap<String, Integer>();
      for (String word : words) {
        tracker.put(word, tracker.getOrDefault(word, 0) + 1);
      }

      // if frequency is equal use compareTo for strings to sort in alphabetical order
      // or sort in descending order of frequency
      // The order of w1 and w2 is important here while calling compare to and taking count diff
      // as it avoid extra reverse sorting step down the line while returning the list
      // min-heap meaning least frequent at the top
      PriorityQueue<String> heap =
          new PriorityQueue<>(
              (w1, w2) ->
                  tracker.get(w1) == tracker.get(w2)
                      ? w1.compareTo(w2)
                      : tracker.get(w1) - tracker.get(w2));
      // System.out.println(tracker);
      for (String word : tracker.keySet()) {
        heap.offer(word);
        // check the size of the heap, if it exceeds k, remove the least frequent
        if (heap.size() > k) {
          // remove the least frequent or highest alphabetical order if frequencies are same
          heap.poll();
        }
        // System.out.println("Queue : " + heap);
      }

      // System.out.println("Queue : " + heap);
      // extract the words from the heap and add them to the result list in reverse order
      while (!heap.isEmpty()) {
        ans.add(0, heap.poll());
      }
      // System.out.println("List  : " + ans);
    }
    return ans;
  }
}
