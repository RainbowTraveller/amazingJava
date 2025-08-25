// Find a subarray from a given array such that it's product is largest
// The original array may contain -ve numbers as well
import java.util.ArrayList;
import java.util.List;

public class MaxProdSubArray {
  public static void main(String[] args) {
    int[] arr = {1, 2, -4, 5, -2, 3, -5, 7};
    List<Integer> input = new ArrayList<Integer>();
    for (Integer i : arr) {
      input.add(i);
    }
    System.out.println("Max Continuous Product : " + maxProduct(input));
    int[] arrz = {0, 0, -4, 0, -20, 0, -3, 0};
    System.out.println("Max Continuous Product Zeros : " + maxProductWithZero(arrz));
  }

  /**
   * This function returns the maximum product of a subarray in the given array. The array may
   * contain negative numbers as well. The idea is to keep track of both max and min product ending
   * at current position. When we encounter a negative number, the max product becomes min and min
   * becomes max. We update the global max whenever we get a new max.
   *
   * @param nums input array
   * @return maximum product of a subarray
   */
  public static int maxProdWithZero(int[] nums) {
    int max_ending_here = 0;
    int min_ending_here = 0;
    int max_so_far = 0;

    if (nums != null && nums.length > 0) {
      // not 1 but first element is used for
      // initialization
      max_ending_here = nums[0];
      min_ending_here = nums[0];
      max_so_far = nums[0];

      for (int i = 1; i < nums.length; ++i) {
        if (nums[i] > 0) {
          // Choose min and max between current no. or its operation with
          // current min or max
          max_ending_here = Math.max(nums[i], max_ending_here * nums[i]);
          min_ending_here = Math.min(nums[i], min_ending_here * nums[i]);
        } else if (nums[i] == 0) {
          // Can't help here, has to be zeroed
          max_ending_here = 0;
          min_ending_here = 0;

        } else {

          int temp = min_ending_here;
          min_ending_here = max_ending_here;
          max_ending_here = temp;
          // This is the same thing as above when no. is positive
          // but the values of corr. elements have been swapped
          max_ending_here = Math.max(nums[i], max_ending_here * nums[i]);
          min_ending_here = Math.min(nums[i], min_ending_here * nums[i]);
        }
        max_so_far = Math.max(max_so_far, max_ending_here);
      }
    }
    return max_so_far;
  }

  /**
   * This function returns the maximum product of a subarray in the given array. The array may
   * contain negative numbers as well as zeros. The idea is to keep track of both max and min
   * product ending at current position. When we encounter a negative number, the max product
   * becomes min and min becomes max. When we encounter a zero, we reset both max and min to zero.
   * We update the global max whenever we get a new max.
   *
   * @param a input array
   * @return maximum product of a subarray
   */
  public static int maxProductWithZero(int[] a) {
    int max_so_far, max_ending_here, min_ending_here;
    max_so_far = max_ending_here = min_ending_here = a[0];

    for (int i = 1; i < a.length; i++) {
      if (a[i] < 0) {
        // when a[i] < 0
        // max * a[i] will become min
        // min * a[i] will become max
        int t = max_ending_here;
        max_ending_here = min_ending_here;
        min_ending_here = t;
      }

      /*
         if a[i] = 0 case is taken care of where max and min ending here
         are made 0 as per the logic
      */
      max_ending_here = Math.max(a[i], max_ending_here * a[i]);
      min_ending_here = Math.min(a[i], min_ending_here * a[i]);
      max_so_far = Math.max(max_so_far, max_ending_here);
    }
    return max_so_far;
  }
}
