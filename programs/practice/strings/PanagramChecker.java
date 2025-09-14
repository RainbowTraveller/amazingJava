import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * PanagramChecker : This class checks if a given sentence is a pangram or not.
 *
 * <p>leecode: https://leetcode.com/problems/check-if-the-sentence-is-pangram/
 *
 * <p>A pangram is a sentence where every letter of the English alphabet appears at least once.
 */
public class PanagramChecker {

  private TreeSet<Character> alphabets; // Required Set of alphabets
  private TreeSet<Character> inputSet; // Set of input alphabets

  public PanagramChecker() {
    alphabets = new TreeSet<Character>();
    for (int i = 'a'; i <= 'z'; ++i) {
      alphabets.add((char) i);
    }
    inputSet = new TreeSet<Character>();
  }

  /**
   * Gets the missing alphabets
   *
   * <p>If all characters are present, returns empty String
   *
   * <p>If input is null, returns all characters
   *
   * <p>If input is empty, returns all characters
   *
   * @param input : Input String
   * @return : String of missing characters
   */
  public String getMissingLetters(String input) {
    String missingAlphabets = "";
    if (input == null) {
      input = "";
    }

    char[] inputArray = input.toCharArray();
    this.inputSet.clear();
    for (Character c : inputArray) {
      // Create set from input String characters
      char currentChar = Character.toLowerCase(c);
      if (this.alphabets.contains(currentChar)) {
        this.inputSet.add(currentChar);
      }
    }
    return getSetDiff();
  }

  /*
   * Get difference of 2 Sets to deduce missing characters
   * @return String of missing characters
   */
  private String getSetDiff() {
    List<Character> result =
        this.alphabets.stream()
            .filter(x -> !this.inputSet.contains(x))
            .collect(Collectors.toList());
    Collections.sort(result);
    StringBuffer sb = new StringBuffer();
    for (Character c : result) {
      sb.append(c);
    }
    return sb.toString();
  }

  /**
   * Method to check if the sentence is a pangram using boolean array The logics is as follows:
   *
   * <p>1. Create a boolean array of size 26 to track the presence of each letter.
   *
   * <p>2. Iterate through each character in the sentence.
   *
   * <p>3. For each character, calculate its index (0 for 'a', 1 for 'b', ..., 25 for 'z').
   *
   * <p>4. If the character is a lowercase letter and hasn't been seen before, mark it as seen in
   * the boolean array and increment the count of unique letters found.
   *
   * <p>5. After processing all characters, check if the count of unique letters is 26.
   *
   * <p>6. If the count is 26, return true (the sentence is a pangram); otherwise, return false.
   *
   * @param sentence The input sentence to check.
   * @return true if the sentence is a pangram, false otherwise.
   */
  public boolean checkIfPangram(String sentence) {
    boolean[] track = new boolean[26];
    int cnt = 0;
    for (char c : sentence.toCharArray()) {
      int index = (int) c - 'a';
      if (index >= 0 && index < 26 && track[index] == false) {
        // System.out.print(c + " ");
        track[index] = true;
        cnt++;
      }
    }
    // System.out.print("Count " + cnt);
    return cnt == 26;
  }

  public static void main(String[] args) {
    PanagramChecker pc = new PanagramChecker();
    System.out.println(
        "Missing = " + pc.getMissingLetters("A quick brown fox jumps over the lazy dog"));
    System.out.println(pc.getMissingLetters(""));
    System.out.println(pc.getMissingLetters("A slow yellow frog crawls under the proactive dog"));
    System.out.println(pc.getMissingLetters("Lions, and tigers, and bears, oh my!"));
  }
}
