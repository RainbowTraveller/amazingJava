import java.util.*;

public class Combinations {

  /*
   *
   *  a b c ab bc ac abc
   *
   */

  /**
   * Prints all combinations of the input string.
   *
   * <p>TIme complexity: O(n * 2^n) where n is the length of the string.
   *
   * <p>Space complexity: O(n) for the recursion stack.
   *
   * @param str The string to generate combinations from.
   */
  public static void generateCombinations(String str) {
    if (str == null) {
      return;
    }

    /*
     Note : If you want combinations of unique characters only, uncomment the below code.

    // Step 1: Extract unique characters from the input string using a Set.
    Set<Character> uniqueChars = new HashSet<>();
    for (char c : str.toCharArray()) {
      uniqueChars.add(c);
    }

    // Step 2: Convert the Set of unique characters back to a string.
    StringBuilder uniqueStrBuilder = new StringBuilder();
    for (char c : uniqueChars) {
      uniqueStrBuilder.append(c);
    }
    String uniqueStr = uniqueStrBuilder.toString();
    */
    System.out.println("Generating combinations for: \"" + str + "\"");
    System.out.println("Empty string"); // The empty combination
    // generateCombinationsHelper("", str);
    // Use a StringBuilder for efficient string building in recursion
    generateCombinationsHelperOptimized(str, new StringBuilder(), 0);
  }

  /**
   * A recursive helper method to build and print combinations.
   *
   * @param prefix The current combination being built.
   * @param remaining The part of the string yet to be processed.
   */
  private static void generateCombinationsHelper(String prefix, String remaining) {
    // Base case: print the current prefix, which is a valid combination.
    System.out.println(prefix);

    // Recursive step: iterate through the remaining characters.
    for (int i = 0; i < remaining.length(); i++) {
      // "Include" the current character and recurse with the rest.
      generateCombinationsHelper(prefix + remaining.charAt(i), remaining.substring(i + 1));
    }
  }

  /**
   * Recursive helper method to build and print combinations efficiently. It uses a "prefix" and a
   * "start index" to avoid re-processing characters.
   *
   * @param input The original input string.
   * @param prefix The current combination being built.
   * @param start The starting index for the current recursion level.
   */
  private static void generateCombinationsHelperOptimized(
      String input, StringBuilder prefix, int start) {
    for (int i = start; i < input.length(); i++) {
      // 1. Include the current character
      prefix.append(input.charAt(i));

      // 2. Print the new combination
      System.out.println(prefix.toString());

      // 3. Recurse with the rest of the string
      generateCombinationsHelperOptimized(input, prefix, i + 1);

      // 4. Backtrack: remove the last character to explore other paths
      prefix.deleteCharAt(prefix.length() - 1);
    }
  }

  public static void main(String[] args) {
    String input = args[0];
    generateCombinations(input);
  }
}
