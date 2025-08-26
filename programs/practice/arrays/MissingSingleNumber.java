/*
 * from a set of n numbers but only n - 1 numbers are given.
 * The n numbers are positive numbers and range from 1 to n with no duplicates
 */
public class MissingSingleNumber {
  public static void main(String[] args) {
    int[] arr = {1, 2, 4, 5, 3, 7};
    MissingSingleNumber msn = new MissingSingleNumber();
    msn.getMissingNumberBySum(arr);
    msn.getMissingNumberXOR(arr);
  }

  public void getMissingNumberBySum(int[] arr) {
    if (arr != null) {
      // one numbers is missing
      // n numbers are positive numbers and range from 1 to n with no duplicates
      // so the range is from 1 to arr.length + 1
      int range = arr.length + 1;

      // sum of first n natural numbers = n * (n + 1) / 2
      int sum = range * (range + 1) / 2;

      // subtracting the sum of given numbers from the sum of first n natural numbers
      for (int i = 0; i < arr.length; ++i) {
        sum -= arr[i];
      }

      System.out.println(" Missing no. by Sum : " + sum);
    }
  }

  /**
   * The provided program finds a single missing number in an array using the XOR (exclusive OR)
   * bitwise operation. This method is highly efficient as it avoids sorting or summing up numbers,
   * making it a great solution for this problem.
   *
   * <p>The XOR Logic The core idea behind this algorithm is based on two key properties of the XOR
   * operator:
   *
   * <p>Identity Property: A ^ 0 = A. A number XORed with zero is the number itself.
   *
   * <p>Inverse Property: A ^ A = 0. A number XORed with itself is zero.
   *
   * <p>Commutative & Associative Properties: A ^ B = B ^ A and A ^ (B ^ C) = (A ^ B) ^ C. The order
   * of operations does not matter.
   *
   * <p>Combining these, if you have a sequence of numbers and you XOR all of them together, the
   * pairs of identical numbers will cancel each other out, leaving only the unique number. For
   * example, (1 ^ 2 ^ 3) ^ (1 ^ 2) = (1 ^ 1) ^ (2 ^ 2) ^ 3 = 0 ^ 0 ^ 3 = 3.
   *
   * <p>Program Explanation The program works by applying this principle to find the missing number.
   *
   * <p>XOR of the Array Elements (x1):
   *
   * <p>The first loop calculates the XOR sum of all elements present in the input array arr.
   *
   * <p>x1 is initialized to the first element arr[0]. The loop then iterates from the second
   * element (i=1) to the end, XORing each element with x1.
   *
   * <p>At the end of this loop, x1 holds the XOR sum of all numbers in the given array.
   *
   * <p>XOR of the Complete Sequence (x2):
   *
   * <p>The second loop calculates the XOR sum of all numbers from 1 to n+1, where n is the number
   * of elements in the arr.
   *
   * <p>The problem implies that the array arr should contain numbers from 1 to n+1 with one
   * missing. So, a complete sequence would have n+1 elements.
   *
   * <p>x2 is initialized to 1, and the loop XORs it with all numbers from 2 up to arr.length + 1.
   *
   * <p>After this loop, x2 holds the XOR sum of all numbers in the complete, non-missing sequence.
   *
   * <p>Find the Missing Number:
   *
   * <p>The final step is to XOR x1 and x2 (x1 ^ x2).
   *
   * <p>The XOR of the two sums will effectively cancel out all the common numbers, leaving only the
   * one number that was in the complete sequence (x2) but not in the given array (x1).
   *
   * <p>The result is the missing number.
   */
  public void getMissingNumberXOR(int[] arr) {
    if (arr != null) {
      int x1 = arr[0];
      int x2 = 1;

      // XOR of all the elements in the array
      for (int i = 1; i < arr.length; ++i) {
        x1 = x1 ^ arr[i];
      }

      // XOR of all the elements from 1 to n
      for (int i = 2; i <= arr.length + 1; ++i) {
        x2 = x2 ^ i;
      }

      // XOR of the above two will give the missing number
      System.out.println(" Missing no. by XOR : " + (x1 ^ x2));
    }
  }
}
