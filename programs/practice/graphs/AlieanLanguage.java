/*  There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
*
*  Example 1:
*
*  Input:
*  [
*    "wrt",
*    "wrf",
*    "er",
*    "ett",
*    "rftt"
*  ]
*
*  Output: "wertf"
*  Example 2:
*
*  Input:
*  [
*    "z",
*    "x"
*  ]
*
*  Output: "zx"
*  Example 3:
*
*  Input:
*  [
*    "z",
*    "x",
*    "z"
*  ]
*
*  Output: ""
*
*  Explanation: The order is invalid, so return "".
*  Note:
*
*  You may assume all letters are in lowercase.
*  If the order is invalid, return an empty string.
*  There may be multiple valid order of letters, return any one of them is fine.
*/

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Set;

public class AlieanLanguage {

    public static void main(String[] args) {
        String[] words = { "wrt", "wrf", "er", "ett", "rftt"
                // "z", "z"
        };
        AlieanLanguage al = new AlieanLanguage();
        System.out.println("Topographical Order : " + al.getTopographicalOrder(words));
    }

    public String getTopographicalOrder(String[] words) {
        if (words != null) {
            // Tracks incoming edges from other vertices
            int[] inDegree = new int[26];
            // Arrays.stream(inDegree)
            // .forEach(System.out :: print);
            // This is graph implementation as adjacency list
            Map<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
            boolean isGraphPossible = bulildGraph(inDegree, graph, words);
            if (isGraphPossible) {
                return topgraphicalSort(inDegree, graph);
            }
        }
        return "";
    }

    public boolean bulildGraph(int[] inDegree, Map<Character, List<Character>> graph, String[] words) {
        // Initialize
        // important because for input like { "z", "z" } answer is "z"
        for (String word : words) {
            for (Character c : word.toCharArray()) {
                graph.putIfAbsent(c, new LinkedList());
            }
        }

        for (int i = 0; i < words.length - 1; ++i) {
            // Consider consecutive strings
            String currWord = words[i];
            String nextWord = words[i + 1];

            // Check that nextWord is not a prefix of currWord
    //        if (currWord.length() > nextWord.length() || currWord.startsWith(nextWord)) {
    //            return false;
    //        }

            // comparison is possible only for the length of
            // smaller one
            int min = Math.min(currWord.length(), nextWord.length());
            for (int k = 0; k < min; ++k) {
                char cw = currWord.charAt(k);
                char nw = nextWord.charAt(k);
                // Get first occurrence of mismatching character
                if (cw != nw) {
                    // We found one ordered pair
                    List<Character> neighbors = graph.getOrDefault(cw, new LinkedList<Character>());
                    if (!neighbors.contains(nw)) {
                        // Update the adjacency metrix
                        neighbors.add(nw);
                        // Add in degree of destination vertex
                        ++inDegree[nw - 'a'];
                        graph.put(cw, neighbors);
                        // This is important, as we need to add an empty
                        // list for destination node is it does not appear
                        // later a source
                        graph.putIfAbsent(nw, new LinkedList());
                    }
                    break; // We can only conclude about 1 pair of characters
                           // subsequent ones can be in any order
                }
            }
        }
        return true;
    }

    public String topgraphicalSort(int[] inDegree, Map<Character, List<Character>> graph) {
        Deque<Character> queue = new LinkedList<Character>();

        // Start with a node which has 0 in degree
        // that is a root node if we may call in the
        // context of graph
        for (Character c : graph.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                queue.offerLast(c);
            }
        }

        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()) {
            char curr = queue.pollFirst();
            // Add to the final string
            sb.append(curr);
            // Get all the neighbors
            List<Character> neighbors = graph.get(curr);
            for (Character c : neighbors) {
                // Decrease the degree of each neighbors
                --inDegree[c - 'a'];
                // add the neighbor to the queue
                if (inDegree[c - 'a'] == 0) {
                    queue.offerLast(c);
                }
            }
        }
        Set<Character> s = graph.keySet();
        // if the length of the final String does not match
        // with the key values in map which essentially is
        // all the unique nodes then we have some cycle which is
        // error condition
        return sb.length() == s.size() ? sb.toString() : "";
    }
}
