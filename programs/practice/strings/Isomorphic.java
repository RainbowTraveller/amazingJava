import java.util.HashMap;
import java.util.Map;

public class Isomorphic {
  public static void main(String[] args) {
    System.out.println(Isomorphic.isIsomorphic("paper", "titie"));
    System.out.println(Isomorphic.isIsomorphic("foo", "egg"));
    System.out.println(Isomorphic.isIsomorphic("bob", "pop"));
    System.out.println(Isomorphic.isIsomorphic("bob", "mop"));
  }

  /**
   * Logic: Use a hashmap to store the mapping of characters from string s to string t. Iterate
   * through the characters of both strings simultaneously. For each character in string s, check if
   * it is already present in the hashmap. If it is present, check if the corresponding character in
   * string t matches the mapped character. If it does not match, return false. If the character
   * from string s is not present in the hashmap, check if the character from string t is already
   * mapped to another character. If it is, return false. If it is not, add the mapping to the
   * hashmap. If the loop completes without returning false, return true.
   *
   * <p>Time Complexity: O(n) where n is the length of the strings.
   *
   * <p>Space Complexity: O(1) since the hashmap will contain at most 26 key-value pairs for
   * lowercase letters.
   *
   * @param s the first string
   * @param t the second string
   * @return true if the strings are isomorphic, false otherwise
   */
  public static boolean isIsomorphic(String s, String t) {
    Map<Character, Character> mapping = new HashMap<Character, Character>();

    for (int i = 0; i < s.length(); ++i) {
      if (mapping.containsKey(s.charAt(i))) {
        // Check if key is present and if yes then the value should match the char
        // from the target string
        if (t.charAt(i) != mapping.get(s.charAt(i))) {
          return false;
        }
      } else {
        // Key is not present but value is already mapped to another key
        // which is wrong
        if (mapping.containsValue(t.charAt(i))) {
          return false;
        }
        mapping.put(s.charAt(i), t.charAt(i));
      }
    }
    return true;
  }
}
