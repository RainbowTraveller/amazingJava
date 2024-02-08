public class ValidPalindromeOneChar {
  public static void main(String[] args) {
    ValidPalindromeOneChar vpoc = new ValidPalindromeOneChar();
    System.out.println(vpoc.validPalindrome("aba"));
    System.out.println(vpoc.validPalindrome("abca"));
    System.out.println(vpoc.validPalindrome("abcad"));
    System.out.println(vpoc.validPalindrome("aeeee"));
  }

  /*
     Given a string
     return true
         if it can be made a palindrome by removing at most one char ( 0 or 1 )
         which means return true even if it is already palindrome
  */
  public boolean validPalindrome(String s) {
    if (s != null && s.length() > 0) {
      int start = 0, end = s.length() - 1;

      while (start < end) {
        // Identify first pair which is not matching
        if (s.charAt(start) != s.charAt(end)) {
          // check if by removing each unmatching char, remaining string is palindrome or not
          return isPalindrome(s, start + 1, end) || isPalindrome(s, start, end - 1);
        }
        start++;
        end--;
      }
    }
    return true;
  }

  private boolean isPalindrome(String s, int l, int h) {
    while (l < h) {
      if (s.charAt(l++) != s.charAt(h--)) {
        return false;
      }
    }
    return true;
  }
}
