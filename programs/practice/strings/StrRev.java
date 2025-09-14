/*
 * Simple String reverse program
 *
 */
import java.util.Scanner;

class StrRev {
  private String str;

  public static void main(String[] args) {
    String str = "";
    System.out.println("Please Enter a String: ");
    Scanner sc = new Scanner(System.in);
    try {
      str = sc.nextLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
    StrRev sr = new StrRev(str);
    System.out.println(sr);
    sr.reverse();
    System.out.println(sr);
  }

  public StrRev(String str) {
    this.str = str;
  }

  public void reverse() {
    if (this.str.isEmpty()) {
      System.out.println("EMPTY STRING");
    } else {
      int length = this.str.length();
      int begin = 0;
      int end = length - 1;
      char[] strArr = this.str.toCharArray();
      while (begin < end) {
        char temp = strArr[begin];
        strArr[begin] = strArr[end];
        strArr[end] = temp;
        begin++;
        end--;
      }
      this.str = String.valueOf(strArr);
    }
  }

  public String toString() {
    return this.str;
  }
}
