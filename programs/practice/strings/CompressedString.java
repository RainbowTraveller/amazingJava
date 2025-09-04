import java.util.ArrayList;

/*
 * Program to count frequency of the char and compress it
 * e.g. input aabbccddeee
 * output = a2b2c2d2e3
 *
 * input:  abcd
 * output: abcd (as 'compressed' string a1b1c1d1 is greater than original)
 *
 */

public class CompressedString {

  /**
   * Method to get compressed string The logic is to keep track of current char and its count When a
   * new char is encountered, append the current char and its count to the result Then reset the
   * current char and count Finally, append the last char and its count If the compressed string is
   * not smaller than the original, return the original
   *
   * @param s input string
   * @return compressed string or original string if compressed is not smaller
   */
  public static String getCompressed(String s) {
    char token = ' ';
    int count = 0;
    ArrayList<Character> compressed = new ArrayList<Character>();
    for (int i = 0; i < s.length(); ++i) {
      if (i > 0) {
        if (s.charAt(i) == token) {
          count++;
        } else {
          compressed.add(token);
          compressed.add((Character.forDigit(count, Character.MAX_RADIX)));
          token = s.charAt(i);
          count = 1;
        }
      } else {
        token = s.charAt(i);
        count = 1;
      }
    }
    compressed.add(token);
    compressed.add((Character.forDigit(count, Character.MAX_RADIX)));
    if (compressed.size() < s.length()) {
      s = "";
      for (char c : compressed) {
        s += c;
      }
    }
    return s;
  }

  public static String getCompressedArr(String s) {
    char token = ' ';
    int index = 0;
    int count = 0;
    int len = s.length();
    char[] compressed = new char[2 * len];
    for (int i = 0; i < s.length(); ++i) {
      if (i > 0) {
        if (s.charAt(i) == token) {
          count++;
        } else {
          compressed[index++] = token;
          compressed[index++] = Character.forDigit(count, Character.MAX_RADIX);
          token = s.charAt(i);
          count = 1;
        }
      } else {
        token = s.charAt(i);
        count = 1;
      }
    }
    compressed[index++] = token;
    compressed[index] = Character.forDigit(count, Character.MAX_RADIX);

    if (index < s.length()) {
      s = new String(compressed);
    }
    return s;
  }

  /**
   * Simplified version of the above method without checking length Here we use StringBuffer to
   * build the compressed string The first character is initialized outside the loop This acts as a
   * first token instead of setting it to ' '
   *
   * @param s input string
   * @return compressed string
   */
  public static String getCompressedSimplified(String s) {
    StringBuffer buff = new StringBuffer();
    if (s != null) {
      char token = s.charAt(0);
      int count = 1;
      for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == token) {
          count++;
        } else {
          buff.append(token);
          buff.append(Integer.toString(count));
          token = s.charAt(i);
          count = 1;
        }
      }
      buff.append(token);
      buff.append(Integer.toString(count));
    }
    return buff.toString();
  }

  public static void main(String[] args) {
    String input = args[0];
    System.out.println("Compressed String : " + getCompressed(input));
    System.out.println("Compressed String : " + getCompressedArr(input));
    System.out.println("Compressed String : " + getCompressedSimplified(input));
  }
}
