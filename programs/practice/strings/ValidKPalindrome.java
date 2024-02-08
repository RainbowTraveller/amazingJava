import java.util.Set;
import java.util.LinkedHashSet;

class ValidKPalindrome {
  public static void main(String[] args) {
    ValidKPalindrome validKPalindrome = new ValidKPalindrome();
    System.out.println(validKPalindrome.isValidPalindrome("abcdfdecba", 2));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfdecba", 3));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfdecba", 1));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfghjdecba", 1));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfghjdecba", 4));
    System.out.println(validKPalindrome.isValidPalindrome("abcdfghjdecba", 3));
    System.out.println(validKPalindrome.isValidPalindrome("dfghjde", 4));
    System.out.println(validKPalindrome.isValidPalindrome("dfghjde", 3));
  }

  /**
   * Check if a string contains a palindrome when at most K chars are removed
   * This first removes the palidrom part if any present in the string
   * @param s input string
   * @param k at most these many chars can be deleted from the input string
   * @return true or false depending on whether the string contains a palidrome
   */
  public boolean isValidPalindrome(String s, int k) {
    if (s != null && !s.isEmpty()) {
      int start = 0, end = s.length() - 1;

      while (start < end) {
        // Identify first pair which is not matching
        if (s.charAt(start) != s.charAt(end)) {
          // check if by removing each unmatching char, remaining string is palindrome or not
          // First get the substring to check
          String sub = s.substring(start, end + 1);
          System.out.println("String to process : " + sub + " Value of K : " + k);
          // The substring  length equals max char to remove
          // then yes we already know that remaining string is palindrome
          // so yes and return true
          if (sub.length() == k) {
            return true;
          } // else if (sub.length() > k) {
          return isKPalindrome(sub, new LinkedHashSet<Integer>(), 0, k);
          /* } else {
            // it length is less then the K then not possible
            return false;
          }*/
        }
        start++;
        end--;
      }
    }
    return false;
  }

  /**
   * Checks if a given string is a palidrome if atmost K chars are allowed to be deleted ( 0 to K)
   * It considers all the possible strings that can be obtained by dropping chars starting from 0 to
   * K if any of the string is palindrome it returns true
   *
   * @param input string that needs to be considered
   * @param indexes set of indexes for char to be removed from the input string to check for
   *     palindrome
   * @param start index at which we need to start checking the chars to be removed
   * @param k max no of chars allowed to be deleted
   * @return true if input string contains a palidrome false otherwise
   */
  public boolean isKPalindrome(String input, Set<Integer> indexes, int start, int k) {
    StringBuffer buffer = getDesiredBuffer(input, indexes);
    //    System.out.println("Curr Buff " + buffer);
    if (k == 0) {
      // We have removed max no. of characters
      // hence this is final check. if this is a palidrome we found one
      return isPalindrome(buffer);
    } else { // we have removed < k characters

      if (!isPalindrome(buffer)) { // is given buffer palindrome
        for (int i = start; i < input.length(); ++i) {
          /*
           if k = 2, this will consider following set of indexes
           0-1, 0-2, 0-3
           1-2, 1-3
           2-3
           3
          */
          // add this index indicating that next iteration will have char at this index
          // removed : remember the parameter has index set and immidiately after entering
          // the function we get the desired buffer and check for palindrome
          indexes.add(i);
          // Call recursively by indicating char at next index can be dropped
          // also indicate how many chars upto k are dropped
          if (!isKPalindrome(input, indexes, i + 1, k - 1)) {
            removeLastElement(indexes);
          } else {
            // if palindrome found return true
            return true;
          }
        }
      } else {
        // if palidrome found return true
        return true;
      }
      return false;
    }
  }

  /*
  Remove the last index in the linked hash set
   */
  public void removeLastElement(Set<Integer> indexes) {
    int size = indexes.size();
    for (int element : indexes) {
      size--;
      if (size == 0) {
        indexes.remove(element);
      }
    }
  }

  /*
  Returns a buffer by removing the chars at indexes contained in the set
   */
  public StringBuffer getDesiredBuffer(String input, Set<Integer> indexesToRemove) {
    char[] charsFromString = input.toCharArray();
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < input.length(); i++) {
      if (!indexesToRemove.contains(i)) { // if index not in the set then add char to buffer
        buffer.append(charsFromString[i]);
      }
    }
    return buffer;
  }

  /*
  Check if a string buffer contains palidrome string
   */
  public boolean isPalindrome(StringBuffer buffer) {
    if (buffer.length() == 0) {
      return false;
    }
    int start = 0;
    int end = buffer.length() - 1;
    while (start < end) {
      if (buffer.charAt(start) != buffer.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }
}
