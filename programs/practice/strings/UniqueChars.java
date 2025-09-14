import java.util.HashMap;

public class UniqueChars {

  /*
   * The program demonstrates different ways of
   * checking if a string contains all unique characters or not
   */

  public static void main(String[] args) {
    String str = args[0];
    System.out.println("Input String : " + str);

    if (checkUniquenessMap(str) == true) {
      System.out.println("String has all unique characters");
    } else {
      System.out.println("String has some duplicate characters");
    }

    if (checkUniquenessDoubleLoop(str) == true) {
      System.out.println("String has all unique characters");
    } else {
      System.out.println("String has some duplicate characters");
    }

    if (checkUniquenessDoubleLessLoop(str) == true) {
      System.out.println("String has all unique characters");
    } else {
      System.out.println("String has some duplicate characters");
    }

    if (checkUniquenessArr(str) == true) {
      System.out.println("String has all unique characters");
    } else {
      System.out.println("String has some duplicate characters");
    }

    if (checkUniquenessBitwise(str) == true) {
      System.out.println("String has all unique characters");
    } else {
      System.out.println("String has some duplicate characters");
    }
  }

  /**
   * Check uniqueness using HashMap
   *
   * <p>Algorithm :
   *
   * <ul>
   *   <li>Convert the string to character array
   *   <li>Iterate through the character array
   *   <li>For each character, convert it to lower case and check if it is present in the HashMap
   *   <li>If not present, add it to the HashMap
   *   <li>If present, return false
   *   <li>If the loop completes, return true
   * </ul>
   *
   * <p>Time Complexity : O(n)
   *
   * <p>Space Complexity : O(n)
   */
  public static boolean checkUniquenessMap(String str) {
    char[] chars = str.toCharArray();
    HashMap<Character, Integer> chmap = new HashMap<Character, Integer>();
    for (char c : chars) {
      c = Character.toLowerCase(c);
      if (chmap.get(c) == null) {
        chmap.put(c, 1);
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * Check uniqueness using double loop
   *
   * <p>Algorithm :
   *
   * <ul>
   *   <li>Convert the string to character array
   *   <li>Iterate through the character array using a for-each loop
   *   <li>For each character, initialize a count variable to 0
   *   <li>Iterate through the character array again using a for-each loop
   *   <li>If the character matches the outer loop character (case insensitive), increment the count
   *       variable
   *   <li>If the count variable is greater than 1, return false
   *   <li>If the outer loop completes, return true
   * </ul>
   *
   * <p>Time Complexity : O(n^2)
   *
   * <p>Space Complexity : O(1)
   */
  public static boolean checkUniquenessDoubleLoop(String str) {
    char[] chars = str.toCharArray();
    for (char c : chars) {
      int count = 0;
      for (char c2 : chars) {
        if (Character.toLowerCase(c) == Character.toLowerCase(c2)) {
          count++;
          if (count > 1) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Check uniqueness using double loop with less iterations
   *
   * <p>Algorithm :
   *
   * <ul>
   *   <li>Convert the string to character array
   *   <li>Iterate through the character array using a for loop
   *   <li>For each character, initialize a count variable to 0
   *   <li>Iterate through the character array again using a for loop starting from the next index
   *       of the outer loop
   *   <li>If the character matches the outer loop character (case insensitive), return false
   *   <li>If the outer loop completes, return true
   * </ul>
   *
   * <p>Time Complexity : O(n^2)
   *
   * <p>Space Complexity : O(1)
   */
  public static boolean checkUniquenessDoubleLessLoop(String str) {
    char[] chars = str.toCharArray();
    int arrlen = chars.length;
    for (int i = 0; i < arrlen; ++i) {
      int count = 0;
      for (int j = i + 1; j < arrlen; ++j) {
        if (Character.toLowerCase(chars[i]) == Character.toLowerCase(chars[j])) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check uniqueness using boolean array
   *
   * <p>Algorithm :
   *
   * <ul>
   *   <li>Check if the length of the string is greater than 128, if yes return false
   *   <li>Create a boolean array of size 256 (to cover extended ASCII)
   *   <li>Iterate through the string
   *   <li>For each character, convert it to lower case and get its ASCII value
   *   <li>If the boolean array at that index is true, return false
   *   <li>If the boolean array at that index is false, set it to true
   *   <li>If the loop completes, return true
   * </ul>
   *
   * <p>Time Complexity : O(n)
   *
   * <p>Space Complexity : O(1)
   */
  public static boolean checkUniquenessArr(String str) {
    int len = str.length();
    if (len > 128) {
      return false;
    }
    boolean[] chk_track = new boolean[256];
    for (int i = 0; i < len; ++i) {
      int val = Character.toLowerCase(str.charAt(i));
      if (chk_track[val] == true) {
        return false;
      }
      chk_track[val] = true;
    }
    return true;
  }

  /**
   * Check uniqueness using bitwise operations
   *
   * <p>Algorithm
   *
   * <ul>
   *   <li>Check if the length of the string is greater than 128, if yes return false
   *   <li>Initialize an integer variable check to 0
   *   <li>Iterate through the string
   *   <li>For each character, convert it to lower case and get its position in the alphabet (0-25)
   *   <li>Create a bit mask by left shifting 1 by the position value
   *   <li>If the bitwise AND of check and the bit mask is greater than 0, return false
   *   <li>Update check by performing bitwise OR with the bit mask
   *   <li>If the loop completes, return true
   * </ul>
   *
   * <p>Time Complexity : O(n)
   *
   * <p>Space Complexity : O(1)
   */
  public static boolean checkUniquenessBitwise(String str) {
    int len = str.length();
    if (len > 128) {
      return false;
    }
    int check = 0;
    for (int i = 0; i < len; ++i) {
      char lch = Character.toLowerCase(str.charAt(i));
      int val = lch - 'a';
      int bitter = 1 << val; // Rotate 1 to left val no. of times
      System.out.println("Value : " + val);
      System.out.println("Checker : " + Integer.toString(check, 2));
      System.out.println("Bitter : " + Integer.toString(bitter, 2));
      System.out.println("-----------------------------------------");
      if ((check & bitter) > 0) { // Two set bits at same position will be > 0
        return false;
      }
      check |= bitter; // Add the bit mask to checker
    }
    return true;
  }
}
