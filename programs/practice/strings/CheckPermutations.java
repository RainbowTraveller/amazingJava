public class CheckPermutations {

  /*
   * Checks if 2 input strings belong to the same permutation group
   *
   */
  public static String sort(String str) {
    char[] arr = str.toCharArray();
    java.util.Arrays.sort(arr);
    return new String(arr);
  }

  /**
   * Check if 2 strings are permutations of each other by sorting and comparing
   *
   * <p>Time complexity: O(n log n) due to sorting
   *
   * <p>Space complexity: O(n) due to character array
   *
   * @param s First input string
   * @param t Second input string
   * @return true if s and t are permutations of each other, false otherwise
   */
  public static boolean checkPerm(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    return sort(s).equals(sort(t));
  }

  /**
   * Check if 2 strings are permutations of each other by counting characters
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(1) assuming character set is fixed (e.g., ASCII)
   *
   * @param s First input string
   * @param t Second input string
   * @return true if s and t are permutations of each other, false otherwise
   */
  public static boolean checkPermCount(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    char[] charr = s.toCharArray();
    int[] count = new int[256];
    for (char c : charr) {
      count[c]++;
    }
    for (int i = 0; i < t.length(); ++i) {
      int c = t.charAt(i);
      if (--count[c] < 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String s = args[0];
    String t = args[1];
    if (checkPerm(s, t)) {
      System.out.println("String belong to same permutation group");
    } else {
      System.out.println("String don't belong to same permutation group");
    }

    if (checkPermCount(s, t)) {
      System.out.println("String belong to same permutation group");
    } else {
      System.out.println("String don't belong to same permutation group");
    }
  }
}
