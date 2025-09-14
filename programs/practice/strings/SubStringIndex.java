import java.util.Scanner;

public class SubStringIndex {

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    String destination = scan.nextLine();
    String candidate = scan.nextLine();
    SubStringIndex ssi = new SubStringIndex();
    System.out.println(ssi.getSubStringIndex(destination, candidate));
    System.out.println(ssi.findPattern(destination, candidate));
    System.out.println(ssi.kmp(destination, candidate));
  }

  /*
   * Naive approach
   * Start comparing from start of the 2 lists, if mismatch occurs then candidate
   * is scanned from beginning and destination index is reset to one more than the previous
   * iteration
   *
   */
  public int getSubStringIndex(String destination, String candidate) {
    int lendestination = destination.length();
    int lenCandidate = candidate.length();
    int tracker = 0;
    while (lendestination - tracker >= lenCandidate) {
      int current = tracker;
      int ctracker = 0;
      while (current < lendestination && ctracker < lenCandidate) {
        if (destination.charAt(current) == candidate.charAt(ctracker)) {
          current++;
          ctracker++;
          if (ctracker == lenCandidate) {
            return tracker;
          }
        } else {
          break;
        }
      }
      tracker++;
    }
    return 0;
  }

  /**
   * Iterate through the source string and look for the first character of the pattern string
   *
   * <p>if found then start matching the subsequent characters of both strings
   *
   * <p>if all characters of the pattern string are matched then return the starting index otherwise
   * continue looking for the first character of the pattern string in the source string
   *
   * <p>Time complexity : O(n*m) where n is the length of source string and m is the length of
   * pattern string
   *
   * <p>Space complexity : O(1) no extra space used
   *
   * @param source the source string
   * @param pattern the pattern string
   * @return the starting index of the pattern in the source string or -1 if not found
   */
  public static int findPattern(String source, String pattern) {
    if (source != null && pattern != null) {
      for (int i = 0; i < source.length(); i++) {
        if (source.charAt(i) == pattern.charAt(0)) {
          int ptrack = 1;
          int strack = i + 1;
          while (ptrack < pattern.length()
              && strack < source.length()
              && source.charAt(strack) == pattern.charAt(ptrack)) {
            ptrack++;
            strack++;
          }
          if (ptrack == pattern.length()) {
            return i;
          }
        }
      }
    }
    return -1;
  }

  /**
   * Compare strings character by character starting at 0 index at the mismatch following conditions
   * need to be checked if j > 0 : meaning we have found some part of the string so far so look for
   * similar occurrence
   *
   * <p>otherwise no match found so far keep on looking with next character in the input pattern
   * string Leetcode : https://leetcode.com/problems/implement-strstr/
   *
   * <p>The logic is as follows :
   *
   * <p>1. Compare characters of both strings if they match then increment both the pointers
   *
   * <p>2. If all characters of the pattern string are matched then return the starting index
   *
   * <p>3. If there is a mismatch and j > 0 then set j to prefix[j-1] and continue comparing
   *
   * <p>4. If there is a mismatch and j == 0 then increment i to look for the next character in the
   * source string
   *
   * <p>Time complexity : O(n+m) where n is the length of source string and m is the length of
   * pattern string
   *
   * <p>Space complexity : O(m) for storing the prefix table
   */
  public int kmp(String destination, String candidate) {
    int lendestination = destination.length();
    int lenCandidate = candidate.length();

    int[] prefix = prefixTable(candidate);
    int i = 0, j = 0;
    while (i < lendestination) {
      if (destination.charAt(i) == candidate.charAt(j)) {
        if (j == lenCandidate - 1) {
          return i - lenCandidate + 1;
        } else {
          i++;
          j++;
        }
      } else if (j > 0) {
        j = prefix[j - 1];
      } else {
        i++;
      }
    }
    return -1;
  }

  /**
   * Creating prefix table
   *
   * <p>a a a a b a a c d 0 1 2 3 0 1 2 0 0
   *
   * <p>a b c d a b e a b f 0 0 0 0 1 2 0 1 2 0
   *
   * <p>Track first repeated occurrence of the first character continue increasing the prefix values
   * if the subsequent characters match at the mismatch following conditions need to be checked if j
   * > 0 : meaning we have found at least one repeated character then start from its previous
   * occurrence
   *
   * <p>otherwise no match found so far keep on looking with next character in the input pattern
   * string
   */
  private int[] prefixTable(String candidate) {
    int[] prefix = new int[candidate.length()];
    int i = 1, j = 0;
    prefix[0] = 0;
    while (i < candidate.length()) {
      if (candidate.charAt(i) == candidate.charAt(j)) {
        prefix[i] = j + 1;
        i++;
        j++;
      } else if (j > 0) {
        j = prefix[j - 1];
      } else {
        prefix[i] = 0;
        i++;
      }
    }
    for (int c : prefix) {
      System.out.print(c + " ");
    }
    System.out.println();
    return prefix;
  }
}
