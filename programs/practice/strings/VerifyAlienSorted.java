/*  In an alien language, surprisingly they also use English lowercase letters, but possibly in a different order.
*  The order of the alphabet is some permutation of lowercase letters.
*
*  Given a sequence of words written in the alien language, and the order of the alphabet,
*  return true if and only if the given words are sorted lexicographically in this alien language.
*
*
*
*  Example 1:
*
*  Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
*  Output: true
*  Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
*  Example 2:
*
*  Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
*  Output: false
*  Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
*  Example 3:
*
*  Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
*  Output: false
*  Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
*

* What is Lexicographical Order?
* Lexicographical order (often shortened to "lexical order" or sometimes called alphabetical order) is the mathematical generalization of the order of words in a dictionary.
*
* It defines how two sequences (in this case, strings of letters) are ordered based on the first point where they differ.
*
* The rule is: Compare character by character from left to right.
*
* The Core Rules:
* Compare First Difference: If two words differ at position i, the word with the character that comes first in the alphabet (e.g., 'a' before 'b') is the lexicographically smaller word.
*
* Prefix Rule: If one word is a prefix of another (e.g., "cat" vs. "cater"), the shorter word is always considered the lexicographically smaller word.
*/

public class VerifyAlienSorted {
  public static void main(String[] args) {
    String[] words1 = {"hello", "leetcode"};
    System.out.println(VerifyAlienSorted.isAlienSorted(words1, "hlabcdefgijkmnopqrstuvwxyz"));

    String[] words2 = {"word", "world", "row"};
    System.out.println(VerifyAlienSorted.isAlienSorted(words2, "worldabcefghijkmnpqstuvxyz"));

    String[] words3 = {"apple", "app"};
    System.out.println(VerifyAlienSorted.isAlienSorted(words3, "abcdefghijklmnopqrstuvwxyz"));
  }

  public static boolean isAlienSorted(String[] words, String order) {

    for (int i = 0; i < words.length - 1; i++) {
      // current word
      String curr = words[i];
      // next word
      String next = words[i + 1];
      // tracking index
      int index = 0;
      // Get min length out of both words
      int minLength = curr.length() < next.length() ? curr.length() : next.length();
      // When smaller one ends and both are same so far this will remain true
      // and if smaller word comes after larger one then clearly it is false condition
      boolean isSameSoFar = true;
      while (index < minLength) {
        // if char at index from current comes later than next one by order then false
        /* if (order.indexOf(curr.charAt(index)) > order.indexOf(next.charAt(index))) {
            return false;
            // We only need first non matching char and it should be in lexicographical
            // order
            // when we get that we know 2 things
            // 1. The strings are not same so far
            // 2, Lexicographical order is correct and next chars do not matter so break the
            // loop and
            // consider next pair
          } else if (order.indexOf(curr.charAt(index)) < order.indexOf(next.charAt(index))) {
            isSameSoFar = false;
            break;
          } else {
            // chars from both strings at index are same
            // so consider next one
            index++;
          }
        }
        // if next string is subset of current then the order is not maintained
        if (isSameSoFar && curr.length() > next.length()) {
          return false;
        } */
        if (curr.charAt(index) != next.charAt(index)) {
          if (order.indexOf(curr.charAt(index)) > order.indexOf(next.charAt(index))) {
            return false;
          } else {
            // correct order found so break while
            break;
          }
        }
        index++;
      }
    }
    return true;
  }
}
