/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

 *   Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
 *   Example 1:
 *   Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 *   Output: true
 *   Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *   Example 2:
 *   Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 *   Output: false
 *   Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *   Example 3:
 *   Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 *   Output: false
 *   Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

*/
public class CheckValidOrder {
    public static void main(String[] args) {
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        CheckValidOrder.isAlienSorted(words, order);
    }

    public static boolean isAlienSorted(String[] words, String order) {
        boolean isCurrentPair = true;
        Set<Integer> track = new HashSet<Integer>();
        if(order!= null && words != null && words.length > 1) {
            int len = words.length;
            while(isCurrentPair) {
                for(int i = 0; i < len; ++ i) {
                    if( i > 0 ){
                        String curr = words[i];
                        String prev = words[i - 1];
                        System.out.println("Curr :" + curr + " Prev : " + prev );
                        int index = 0;
                        while(index < curr.length() &&  index < prev.length()) {
                            if(curr.charAt(index) != prev.charAt(index)) {
                                char c = curr.charAt(index);
                                char p = prev.charAt(index);
                                if(order.indexOf(p) > order.indexOf(c))    {
                                    System.out.println("Inside C :" + c + " P : " + p );
                                    return false;
                                } else {
                                    isCurrentPair = false;
                                    break;
                                }
                            }
                            index++;
                        }

                        if(!isCurrentPair) {
                            isCurrentPair = true;
                            continue;
                        } else {
                            if(curr.length() < prev.length()) {
                                return false;
                            }
                        }
                    }
                }
                isCurrentPair = false;
            }
        }
    }
}
