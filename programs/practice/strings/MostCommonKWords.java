import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MostCommonKWords {
    public static void main(String[] args) {

        MostCommonKWords mckw = new MostCommonKWords();
        String[] words = { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" };
        int n = 4;
        System.out.println("Top  " + n + " frequent words : " + mckw.topKFrequent(words, 4));
        System.out.println("Top  " + n + " frequent words : " + mckw.topKFrequentHeap(words, 4));

    }

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

    public List<String> topKFrequentHeap(String[] words, int k) {

        List<String> ans = new LinkedList<>();
        if (words != null && words.length > 0 && k <= words.length) {
            Map<String, Integer> tracker = new TreeMap<String, Integer>();
            for (String word : words) {
                tracker.put(word, tracker.getOrDefault(word, 0) + 1);
            }

            //if frequency is equal use compareTo for strings to sort in alphabetical order
            //or sort in descending order of frequency
            //The order of w1 and w2 is important here while calling compare to and taking count diff
            //as it avoid extra reverse sorting step down the line while returning the list
            PriorityQueue<String> heap = new PriorityQueue<>((w1,
                    w2) -> tracker.get(w1) == tracker.get(w2) ? w1.compareTo(w2) : tracker.get(w2) - tracker.get(w1));
            for (String word : tracker.keySet()) {
                heap.offer(word);
                if (heap.size() > k) {
                    heap.poll();
                }
            }

            //System.out.println("Queue : " + heap);
            while(!heap.isEmpty()) {
                ans.add(heap.poll());
            }
            // System.out.println("List  : " + ans);
        }
            return ans;

    }
}

