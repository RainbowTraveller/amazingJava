// Given two strings, A and B, of equal length, find whether it is possible to split both strings at the same index such that merging the first part of A and the second part of B forms a palindrome. Return the location of the split. Palindrome is a word that reads the same backward as forward.
// If the solution can not be found, return -1.
// Example:
// "abcdefgh" and "dasedcba" return 4, since we can cut the strings like "abcd" and "dcba"

// qwwr
// asdq
// qww + q (3)
// count = 1;
// len1 = 4
// len2 = 4
//
// 0 len2- 1
//
// 0 len2 - 2
// 1 len2 - 1
//

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class SameIndexSplitPalindrome {
    public static void main(String[] args) {

        // System.out.println("Index :" +
        // checkPrefixAndSuffixPalindrome("qwwr","asdq"));

        System.out.println("Index :" + checkPrefixAndSuffixPalindrome("abcdefgh", "dasedcba"));

    }

    public static int checkPrefixAndSuffixPalindrome(String s1, String s2) {
        int index = -1;
        if (s1 != null && s2 != null) {
            int len1 = s1.length();
            int len2 = s2.length();

            if (s1.charAt(0) == s2.charAt(len2 - 1)) {
                index = helper(s1, s2, len1, len2, 0, len2 - 1);
            }
        }
        return index;
    }

    public static int helper(String s1, String s2, int len1, int len2, int index1, int index2) {

        if (index1 < len1 && index2 >= 0) {
            String sub1 = s1.substring(0, index1 + 1);
            System.out.println("Substring 1 : " + sub1);
            String sub2 = s2.substring(index2, s2.length());
            System.out.println("Substring 2 : " + sub2);
            if (!isPalindrome(sub1.concat(sub2))) {
                return index1 == len2 - index2 ? index1 : -1;
            }
            int one = helper(s1, s2, len1, len2, index1, index2 - 1);
            int two = helper(s1, s2, len1, len2, index1 + 1, index2);
            if (one < 0 && two < 0) {
                return -1;
            } else if (one > 0 && two > 0) {
                return Math.min(one, two);
            } else {
                return one > 0 ? one : two;
            }
        }
        return -1;
    }

    public static boolean isPalindrome(String str) {
        if (str != null) {
            System.out.println("Palindrome ?  " + str);
            int start = 0, end = str.length() - 1;
            while (start < end) {
                if (str.charAt(start) != str.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
        return false;
    }
}
