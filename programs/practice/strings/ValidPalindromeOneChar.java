public class ValidPalindromeOneChar {
    public static void main(String[] args) {
        ValidPalindromeOneChar vpoc = new ValidPalindromeOneChar();
        System.out.println(vpoc.validPalindrome("aba"));
        System.out.println(vpoc.validPalindrome("abca"));
        System.out.println(vpoc.validPalindrome("abcad"));
        System.out.println(vpoc.validPalindrome("aeeee"));

    }

    public boolean validPalindrome(String s) {
        if (s != null && s.length() > 0) {
            int start = 0, end = s.length() - 1;

            while (start < end) {
                if (s.charAt(start) != s.charAt(end)) {
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
