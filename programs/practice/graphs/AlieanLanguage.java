import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Deque;
import java.util.HashMap;
import java.util.Set;

public class AlieanLanguage {

    public static void main(String [] args) {
        String[] words = {
            "z",
            "x",
            "z"
        };
        AlieanLanguage al = new AlieanLanguage();
        System.out.println(al.getTopographicalOrder(words));
    }

    public String getTopographicalOrder(String [] words) {
        if(words != null) {
            //Tracks incoming edges from other vertices
            int [] inDegree = new int[26];
            //This is graph implementation as adjacency list
            Map<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
            bulildGraph(inDegree, graph, words);
            return topgraphicalSort(inDegree, graph);
        }
        return "";
    }

    public void bulildGraph(int[] inDegree, Map<Character, List<Character>> graph, String[] words) {
        for(int i = 0 ; i < words.length - 1; ++i ) {
            //Consider consecutive strings
            String currWord = words[i];
            String nextWord = words[i + 1];

            //comparison is possible only for the length of
            //smaller one
            int min = Math.min(currWord.length(), nextWord.length());
            for(int k = 0; k < min; ++k) {
                char cw =  currWord.charAt(k);
                char nw =  nextWord.charAt(k);
                //Get first occurrence of mismatching character
                if(cw != nw) {
                    // We found one ordered pair
                    List<Character> neighbors = graph.getOrDefault(nw, new LinkedList<Character>());
                    if(!neighbors.contains(nw)) {
                        //Update the adjacency metrix
                        neighbors.add(nw);

                        //Add in degree of destination vertex
                        ++inDegree[nw - 'a'];
                        graph.put(cw, neighbors);
                        //This is important, as we need to add an empty
                        //list for destination node is it does not appear
                        //later a source
                        graph.putIfAbsent(nw, new LinkedList());
                    }
                    break; // We can only conclude about 1 pair of characters
                           // subsequent ones can be in any order
                }
            }
        }
        System.out.println("Graph :" + graph);
    }

    public String topgraphicalSort(int [] inDegree,Map<Character, List<Character>> graph ) {
        Deque<Character> queue = new LinkedList<Character>();

        //Start with a node which has 0 in degree
        //that is a root node if we may call in the
        //context of graph
        for(Character c : graph.keySet()) {
            if(inDegree[c - 'a'] == 0) {
                queue.offerLast(c);
            }
        }

        StringBuffer sb = new StringBuffer();
        while(!queue.isEmpty()) {
            char curr = queue.pollFirst();
            //Add to the final string
            sb.append( curr );
            //Get all the neighbors
            List<Character> neighbors = graph.get(curr);
            for(Character c : neighbors) {
                //Decrease the degree of each neighbors
                --inDegree[c - 'a'];
                //add the neighbor to the queue
                if(inDegree[c - 'a'] == 0) {
                    queue.offerLast(c);
                }
            }
        }
        Set<Character> s = graph.keySet();
        //if the length of the final String does not match
        //with the key values in map which essentially is
        //all the unique nodes then we have some cycle which is
        //error condition
        return sb.length() == s.size() ? sb.toString() : "";
    }
}
