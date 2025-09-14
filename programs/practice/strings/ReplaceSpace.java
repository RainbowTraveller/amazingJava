/**
 * Leetcode : https://leetcode.com/problems/replace-spaces/ Replaces space with '%20'. Input also
 * gives actual length of original string and assume that String has enough space to contain
 * additional string e.g. Input : "Mr. Smith ", 8 Output : "Mr.%20Smith"
 */
public class ReplaceSpace {

  public static String replaceSpace(String s, int len) {
    /*
     * O(n^2)
     */
    char[] arr = s.toCharArray();
    int length = len;
    for (int i = 0; i < length; ++i) {
      System.out.println("I value : " + arr[i]);
      if (arr[i] == ' ') {
        for (int j = (length - 1); j >= i; --j) {
          System.out.println("J " + j);
          arr[j + 2] = arr[j];
        }
        length = length + 2;
        System.out.println("I " + i);
        arr[i] = '%';
        arr[i + 1] = '2';
        arr[i + 2] = '0';
        i += 2;
      }
    }

    return new String(arr);
  }

  public static String replaceSpaceOofn(String str, int len) {
    int spaceCount = 0;

    for (int i = 0; i < len; ++i) {
      if (str.charAt(i) == ' ') {
        spaceCount++;
      }
    }
    int newLength = len + spaceCount * 2; // per space we add 2 more chars
    char[] arr = str.toCharArray();

    for (int i = len - 1; i >= 0; --i) {
      if (arr[i] == ' ') {
        arr[newLength - 1] = '0';
        arr[newLength - 2] = '2';
        arr[newLength - 3] = '%';
        newLength -= 3;
      } else {
        arr[newLength - 1] = arr[i];
        newLength -= 1;
      }
    }
    return new String(arr);
  }

  public static void main(String[] args) {
    String str = "Mr John Smith    ";
    String s = replaceSpace(str, 13);
    System.out.println(s);
    String t = replaceSpaceOofn(str, 13);
    System.out.println(t);
  }
}
