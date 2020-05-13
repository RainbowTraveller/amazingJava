/*  Write an algorithm to determine if a number n is "happy".
*
*  A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1
*  (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
*
*  Return True if n is a happy number, and False if not.
*
*  Example:
*
*  Input: 19
*  Output: true
*  Explanation:
*  12 + 92 = 82
*  82 + 22 = 68
*  62 + 82 = 100
*  12 + 02 + 02 = 1
*/

import java.util.Set;
import java.util.HashSet;

public class HappyNumber {
    public static void main(String[] args) {
        HappyNumber hp = new HappyNumber();
        System.out.println("19 : " + hp.isHappy(19));
        System.out.println("231 : " + hp.isHappy(231));
        System.out.println("91 : " + hp.isHappy(91));
        System.out.println("27123 : " + hp.isHappy(27123));

    }
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<Integer>();
        while(n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNextNum(n);
        }
        return n == 1;
    }

    public int getNextNum(int n) {
        int sum = 0;
        while(n != 0) {
            sum = sum +  (int)( Math.pow( n % 10, 2) );
            n = n / 10;
        }
        return sum;
    }
}

