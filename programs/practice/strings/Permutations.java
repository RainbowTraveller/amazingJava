import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Permutations {
  /*
   * To obtain all the possible permutations of a string e.g. abc = abc acb bac
   * bca cab cba
   *
   * aaa is also considered to be consists of 3 different a's
   *
   * allowed[] : boolean array to keep track of allowed char at each level
   *
   * for abc : 3 * 2 * 1 = 6 possible outcomes so at each level no of allowed
   * chars decreases
   *
   * following map shows allowed map after considering particular character f f f
   * t f f t t f
   *
   * a a // blanks in vertical column indicate the chars not allowed b ab// each
   * column represents one recursive call c abc -- c a ac b acb --
   *
   *
   */

  /**
   * Program to print all permutations of a string The logic is as follows:
   *
   * <p>1. Maintain a boolean array to keep track of allowed characters
   *
   * <p>2. At each level of recursion, pick an allowed character, mark it as not allowed and append
   * it to the permutation buffer
   *
   * <p>3. Recur with the updated permutation buffer and allowed array
   *
   * <p>4. When the length of the permutation buffer equals the length of the input string, print
   * the permutation
   *
   * <p>5. Backtrack by marking the character as allowed again and removing it from the permutation
   * buffer
   *
   * @param i Input string for which permutations are to be generated
   * @param perm StringBuffer to hold the current permutation being built
   * @param allowed boolean array to track which characters are still available for use in the
   *     current permutation
   */
  public static void permsSimple(String i, StringBuffer perm, boolean[] allowed) {
    if (perm.length() == i.length()) {
      System.out.println(perm.toString());
      return;
    } else {
      for (int j = 0; j < i.length(); ++j) {
        if (allowed[j]) {
          perm.append(i.charAt(j));
          allowed[j] = false;
          permsSimple(i, perm, allowed);
          allowed[j] =
              true; // reset the map to allow the char to be considered in prev recursive call
          perm.deleteCharAt(perm.length() - 1); // also remove char from the buffer
        }
      }
    }
  }

  /**
   * Computes and prints permutations of a string containing repeated Characters The logic is
   * similar to the one above except that we maintain a frequency array instead of boolean array
   *
   * @param inputArr Array of unique characters from the input string
   * @param output StringBuffer to hold the current permutation being built
   * @param allowed int array to track the frequency of each character available for use in the
   *     current permutation
   * @param len Length of the input string
   */
  public static void perms(char[] inputArr, StringBuffer output, int[] allowed, int len) {
    if (output.length() == len) {
      System.out.println(output.toString());
      return;
    }
    for (int i = 0; i < allowed.length; ++i) {
      if (allowed[i] != 0) {
        allowed[i]--;
        output.append(inputArr[i]);
        perms(inputArr, output, allowed, len);
        allowed[i]++;
        output.deleteCharAt(output.length() - 1);
      }
    }
  }

  /**
   * Finds and prints all unique permutations of a given string using a recursive backtracking
   * approach. This method handles strings with repeating characters correctly.
   *
   * @param str The input string for which to find permutations.
   */
  public static void findPermutations(String str) {
    if (str == null || str.length() == 0) {
      return;
    }

    // Convert the string to a character array and sort it.
    // Sorting is crucial for handling duplicate characters correctly and avoiding
    // duplicate
    // permutations.
    char[] chars = str.toCharArray();
    Arrays.sort(chars);

    // Use a boolean array to track which characters have been used in the current
    // permutation.
    boolean[] used = new boolean[str.length()];

    // Use a StringBuilder to efficiently build the current permutation.
    StringBuilder currentPermutation = new StringBuilder();

    // Start the recursive backtracking process.
    permsEfficient(String.valueOf(chars), currentPermutation, used);
  }

  /**
   * The core recursive backtracking function to generate permutations.
   *
   * @param input The sorted input string (as a character array in a string).
   * @param currentPermutation The StringBuilder that holds the current permutation being built.
   * @param used A boolean array to track used characters.
   */
  private static void permsEfficient(
      String input, StringBuilder currentPermutation, boolean[] used) {
    // Base Case: If the length of the current permutation equals the input length, we have a
    // complete permutation.
    if (currentPermutation.length() == input.length()) {
      System.out.println(currentPermutation.toString());
      return;
    }

    // Recursive Step: Iterate through each character of the input string.
    for (int j = 0; j < input.length(); j++) {

      // Check for valid character to use in the current permutation.
      // A character is valid if it has not been used, and if it is not a duplicate
      // of a previously skipped character in this level of recursion.
      // With sorted input, duplicates are adjacent.
      // The condition ensures that we only use the first occurrence of a character
      // in each recursive call.
      // This prevents generating the same permutation multiple times.
      // e.g. for "aab", when we reach the second 'a', we skip it if the first 'a' was
      // not used, Meaaning that if we continue with second 'a', it would lead to situation
      // where first `a` will eventually be used in subsequent recursive calls leading to
      // duplicate permutations.
      if (!used[j] && (j == 0 || input.charAt(j) != input.charAt(j - 1) || used[j - 1])) {

        // Mark the character as used.
        used[j] = true;
        // Append the character to the current permutation.
        currentPermutation.append(input.charAt(j));

        // Recursively call perms to build the rest of the permutation.
        permsEfficient(input, currentPermutation, used);

        // Backtracking step: Undo the choices to explore other possibilities.
        // Remove the last character added.
        currentPermutation.deleteCharAt(currentPermutation.length() - 1);
        // Mark the character as unused.
        used[j] = false;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("Permutations without considering repeated chars");
    String input = args[0];
    int len = input.length();
    boolean[] allowed = new boolean[len];
    for (int i = 0; i < len; ++i) {
      allowed[i] = true;
    }
    StringBuffer permutation = new StringBuffer(input.length());
    permsSimple(input, permutation, allowed);

    Map<Character, Integer> unique = new HashMap<Character, Integer>();
    StringBuffer output = new StringBuffer(len);
    char[] inputArr = input.toCharArray();
    for (int i = 0; i < len; ++i) {
      int frequency = unique.getOrDefault(inputArr[i], 0);
      unique.put(inputArr[i], frequency + 1);
    }
    int[] allowedCount = new int[unique.size()];
    System.out.println("Permutations with repeated chars");
    int i = 0;
    // Note: take only set of characters with are unique
    // Frequency is already computed
    inputArr = new char[unique.size()];
    for (Character c : unique.keySet()) {
      allowedCount[i] = unique.get(c);
      inputArr[i] = c;
      i++;
    }
    perms(inputArr, output, allowedCount, len);
    System.out.println("Permutations handling repeated chars correctly");
    findPermutations(input);
  }
}
