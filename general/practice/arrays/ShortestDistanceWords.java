public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        int distance = 0;
        for(int i = 0; i < words.length; ++i) {
            if(words[i].equals(word1) || words[i].equals(word2)) {
                if(words[i].equals(word1)) {
                    index1 = i;
                }

                if(words[i].equals(word2)) {
                    index2 = i;
                }

                if(index1 >= 0 && index2 >= 0 ) {
                    if(distance == 0 || distance > Math.abs(index1 - index2)) {
                        distance = Math.abs(index1 - index2);
                        System.out.println(distance);
                    }
                }
            }
        }
        return distance;
    }
}
