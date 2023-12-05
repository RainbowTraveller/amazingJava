/**
 * Write a service that counts the number of occurrences of each character in a string. Focus on
 * edge cases and validations.
 *
 * <p>Please use only primitives and not Data Structures like Map, and Set
 *
 * +--------------------+---------+--------------------+
 * | s = "Hello World!" | s = ""  | s = "Welcome 007!" |
 * +--------------------+---------+--------------------+
 * | Output:            | Output: | Output:            |
 * | D: 1               |         | 0: 2               |
 * | E: 1               |         | 7: 1               |
 * | H: 1               |         | C: 1               |
 * | L: 3               |         | E: 2               |
 * | O: 2               |         | L: 1               |
 * | R: 1               |         | M: 1               |
 * | W: 1               |         | O: 1               |
 * |                    |         | W: 1               |
 * +--------------------+---------+--------------------+
 */
public class CharacterCountService {
  public static void main(String[] args) {
    CharacterCountService.count("Hello World!");
    CharacterCountService.count("Welcome 007!");
  }

  public static void count(String s) {
    //Only 0-9 and capital letters
    int[] frequency = new int[36];
    if (s != null) {
      for (int i = 0; i < s.length(); i++) {
        char currChar = s.charAt(i);
        // If char is between 0 - 9
        if (currChar - '0' <= 9 && currChar - '0' >= 0) {
          frequency[currChar - '0']++;
        } else {
          currChar = Character.toUpperCase(currChar);
          //Check from A - Z
          if (currChar - 'A' >= 0 && currChar - 'A' <= 25) {
            // first 10 are occupied by 0 - 9
            frequency[10 + currChar - 'A']++;
          }
        }
      }

      System.out.println(s);
      System.out.println();
      for (int j = 0; j < frequency.length; ++j) {
        if (frequency[j] > 0) {
          if (j <= 9) {
            System.out.println((char) ('0' + j) + ": " + frequency[j]);
          } else {
            System.out.println((char) ('A' + (j - 10)) + ": " + frequency[j]);
          }
        }
      }
      System.out.println();
    }
  }
}
