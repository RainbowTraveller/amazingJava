/*
 * Input : 1112233455436
 * Output : 3122231425141316
 *
 * Convert a number into another such that is indicates no. of consecutive appearances of a number followed by the number in the input
 *
 */

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class LookAndSayImpl {
  public ArrayList<Integer> list1 = null;
  public ArrayList<Integer> list2 = null;

  public static void main(String args[]) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    LookAndSayImpl soln = new LookAndSayImpl();
    Scanner in = new Scanner(System.in);
    System.out.println("Please enter the number e.g. 11233133222: ");
    String num = in.nextLine();
    System.out.println("Look and Say output : e.g. Two ones,Three 2s etc: " + soln.LookAndSay(num));
    System.out.println("Please enter the iterations: ");
    int itr = in.nextInt();
    System.out.println(
        "Look and Say output after : " + itr + " iterations : " + soln.LookAndSay(num, itr));
    in.close();
  }

  public String LookAndSay(String numeric) {
    StringBuffer strBuff = new StringBuffer();
    char prev = numeric.charAt(0);
    int frequency = 1;
    for (int i = 1; i < numeric.length(); ++i) {
      char curr = numeric.charAt(i);
      if (prev == curr) {
        frequency++;
      } else {
        strBuff.append(frequency);
        strBuff.append(prev);
        prev = curr;
        frequency = 1;
      }
    }
    // Important to handle last number and its frequency
    strBuff.append(frequency);
    strBuff.append(prev);
    return strBuff.toString();
  }

  public String LookAndSay(String numeric, int itr) {
    String input = numeric;
    for (int i = 0; i < itr; ++i) {
      input = LookAndSay(input);
    }
    return input;
  }
}
