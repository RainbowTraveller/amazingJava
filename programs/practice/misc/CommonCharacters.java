// Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.

// You may return the answer in any order.

// Example 1:

// Input: ["bella","label","roller"]
// Output: ["e","l","l"]
// Example 2:

// Input: ["cool","lock","cook"]
// Output: ["c","o"]

// bella ==> b, e, l, a
// lable ==> l, a, b, e
// common : e, l
// roller ==> r, o, l, e
// common ==> e,l
// e --> 1
//l --> 2
//common : c,o
// c --> 1
// o --> 1
import java.util.*;
import java.lang.*;

public class CommonCharacters {
  public static void main(String[] args) {

    List<String> input = new LinkedList<String>();
    /*input.add(new String("bella"));
    input.add(new String("label"));
    input.add(new String("roller"));*/

    input.add(new String("a"));
    input.add(new String("b"));
    input.add(new String("c"));

    Set<Character> common = getCommonCharacters(input);
    //System.out.println(common);
    List<Character> occurrences = getCharOccurrences(common, input);
    System.out.println(occurrences);
  }

  /**
   * Get set of common characters across all
   * strings
   */
  public static Set<Character> getCommonCharacters(List<String> input) {
    Set<Character> common = null;
    if(input != null) {
      common = new HashSet<Character>();
      for(String curr : input) {
        Set<Character> currUniqueChars = getUniqueCharsFromString(curr);
        if(common.size() == 0) {
          common = currUniqueChars;
        } else {
          common.retainAll(currUniqueChars);
        }
      }
    }
    return common;
  }

  /**
   *  get set of characters in a string
   */
  public static Set<Character> getUniqueCharsFromString(String input) {
    Set<Character> unique = new HashSet<Character>();
    for(int i = 0; i < input.length(); ++i) {
      unique.add(input.charAt(i));
    }
    return unique;
  }

  /**
   * Get occurrences of characters in all string and get min one.
   * That many times a character occurs in the 3 strings
   */
  public static List<Character> getCharOccurrences(Set<Character> common, List<String> input) {

    Map<Character, Integer> tracker = new HashMap<>();
    List<Character> occur = new LinkedList<>();
    if(common != null && input != null) {
      for(char c : common) {
        for(String eachString  : input) {
          int cnt = getCharCount(c, eachString);
          int currCount = tracker.getOrDefault(c, Integer.MAX_VALUE);
          tracker.put(c, Math.min(currCount, cnt));
        }
      }

      for(char c : tracker.keySet()) {
          int count = tracker.get(c);
          for(int i = 0; i < count; ++i) {
            occur.add(c);
          }
      }
    }
    return occur;
  }

  public static int getCharCount(char c,  String input) {
    int count = 0;
    if(input != null) {
      for(int i = 0; i < input.length(); ++i) {
        if(input.charAt(i) == c) {
          count++;
        }
      }
    }
    return count;
  }

}
