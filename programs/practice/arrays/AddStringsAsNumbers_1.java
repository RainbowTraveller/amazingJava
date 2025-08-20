import java.io.*;
import java.util.*;

/*
Write a function that adds two strings, where each string represents n arbitrarily large non-negative numbers.

function String addNumbers(String str1, String str2)

Examples:
- addNumbers("123456789012345678901 23456789", "12345678 234567890123456789012") should return "123456789012358024579 234567890123480245801".
- addNumbers("123 456 789", "11 22 33") should return "134 478 822".

 * If you need more classes, simply define them inline.
 */

public class AddStringsAsNumbers_1 {
  public static void main(String[] args) {

    // System.out.println(addNumbers("123456789012345678901 23456789", "12345678
    // 234567890123456789012"));
    try {
      // System.out.println(addNumbers("123 456 789", "11 22 33"));
      System.out.println(addNumbers("123456789012345678901", "12345678 234567890123456789012"));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static String addNumbers(String str1, String str2) throws Exception {
    // check for null or empty strings
    if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
      throw new InvalidInputException("Either of the input string is null or empty");
    }

    // Split the strings into individual numbers
    String[] input1 = str1.split("\\s+");
    String[] input2 = str2.split("\\s+");

    int len1 = input1.length;
    int len2 = input2.length;

    int index1 = 0, index2 = 0;

    StringBuffer result = new StringBuffer();

    while (index1 < len1 || index2 < len2) {
      // If one of the arrays is exhausted, use an empty string for that number
      String num1 = index1 >= len1 ? "" : input1[index1++];
      String num2 = index2 >= len2 ? "" : input2[index2++];
      result.append(addNumsAsStrings(num1, num2));
      result.append(" ");
    }

    return result.toString().trim();
  }

  /**
   * Adds two numbers represented as strings and returns the result as a string. Assumes that the
   * input strings are valid non-negative integers.
   *
   * @param s1 First number as a string
   * @param s2 Second number as a string
   * @return The sum of the two numbers as a string
   */
  public static String addNumsAsStrings(String s1, String s2) {
    int len1 = s1.length();
    int len2 = s2.length();
    StringBuffer result = new StringBuffer();

    int index1 = len1 - 1, index2 = len2 - 1;
    int carry = 0;
    while (index1 >= 0 || index2 >= 0) {
      // If one of the strings is exhausted, use '0' for that digit
      int num1 = index1 < 0 ? 0 : s1.charAt(index1--) - '0';
      int num2 = index2 < 0 ? 0 : s2.charAt(index2--) - '0';

      int sum = num1 + num2 + carry;
      int storedSum = sum % 10;
      carry = sum / 10;
      result.append(storedSum);
    }

    if (carry != 0) {
      result.append(carry);
    }

    return result.reverse().toString();
  }

  static class InvalidInputException extends Exception {
    static final long serialVersionUID = 1L;

    InvalidInputException(String message) {
      super(message);
    }
  }
}
