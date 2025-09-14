/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]

*/
import java.util.*;

public class RepeatedDNASequence {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    List<String> answer = findRepeatedDnaSequences(input);
    for (String s : answer) {
      System.out.println(s);
    }
  }

  /**
   * The function to find repeated DNA sequences
   *
   * <p>It first checks if the input string is valid and has a length of at least 10.
   *
   * <p>It then iterates through the string, extracting each 10-letter-long substring and tracking
   * its occurrences using a HashMap.
   *
   * <p>If a substring appears more than once, it is added to the result list.
   *
   * <p>Finally, the function returns the list of repeated sequences.
   *
   * <p>Time complexity: O(n), where n is the length of the input string.
   *
   * <p>Space complexity: O(m), where m is the number of unique 10-letter-long substrings.
   *
   * @param s The input DNA string
   * @return List of repeated 10-letter-long sequences
   */
  public static List<String> findRepeatedDnaSequences(String s) {
    // Resultant list to store repeated sequences
    List<String> seq = new LinkedList<String>();
    // HashMap to track occurrences of each 10-letter-long substring
    // This is to maintain unique substrings and their counts
    // Set can also be used
    Map<String, Integer> tracker = new HashMap<String, Integer>();
    if (s != null && s.length() >= 10) {

      for (int i = 0; i + 10 <= s.length(); ++i) {
        String current = s.substring(i, i + 10);
        // This will print without needing extra space
        if (s.indexOf(current, i + 1) >= 0) {
          // Checking for occurrence from
          // next index from current start index
          // of the current string
          System.out.println(current);
        }
        tracker.put(current, tracker.getOrDefault(current, 0) + 1);
      }
    }
    for (String str : tracker.keySet()) {
      int count = tracker.get(str);
      if (count > 1) {
        seq.add(str);
      }
    }
    return seq;
  }
}
