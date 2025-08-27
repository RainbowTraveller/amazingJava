/**
 * SearchString
 *
 * <p>This program checks if a given string (candidate) is part of another string (host) in a case
 * sensitive manner.
 *
 * <p>Usage: java SearchString <host_string> <candidate_string>
 *
 * <p>Example: java SearchString "HelloWorld" "World"
 *
 * <p>This will output: World is part of HelloWorld : true
 */
public class SearchString {
  // Find if a String is part of another string (case sensitive)
  public static void main(String[] args) {
    String host = args[0];
    String candidate = args[1];
    System.out.println(
        candidate + " is part of " + host + " : " + SearchString.isExactMatch(host, candidate));
  }

  /**
   * Check if input string is part of base string
   *
   * @param base The string to be searched within
   * @param input The string to search for
   * @return true if input is part of base, false otherwise
   */
  static boolean isExactMatch(String base, String input) {

    // Length of base and input strings
    int lBase = base.length();
    int linput = input.length();

    for (int i = 0; i < lBase; ++i) {
      // Check if the first character matches
      if (base.charAt(i) == input.charAt(0)) {
        // If it matches, check the subsequent characters
        // to see if the entire input string matches
        // starting from the next character in base
        int startIndex = i + 1;
        // trackingIndex tracks the position in the input string
        int trackingIndex = 1;
        // Continue checking characters while they match
        while (trackingIndex < linput
            && startIndex < lBase
            && base.charAt(startIndex) == input.charAt(trackingIndex)) {
          startIndex++; // Move to the next character in base
          trackingIndex++; // Move to the next character in input
          if (trackingIndex == linput) { // All characters in input matched
            return true;
          }
        }
      }
    }
    return false;
  }
}
