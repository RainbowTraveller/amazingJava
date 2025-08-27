/*
 * Given an array of integers and a sum, find out all the pair in the array
 * which add up to the given sum
 *
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NosEqualToSum {
  public static void main(String[] args) {
    int[] arr = {2, 10, 0, 25, 17, 20, 41, 23, 30, 5, 13, 39, 7};
    int sum = 30;
    // Get Indexes of the elements
    getPairIndexes(arr, sum);
    System.out.println("Another method :");
    // Get Values of the elements
    getPairValues(arr, sum);
  }

  /**
   * This method uses two pointer approach to find the pairs in the array which add up to the given
   * sum.
   *
   * @param arr - input array
   * @param sum - target sum
   */
  public static void getPairValues(int[] arr, int sum) {
    Arrays.sort(arr);
    int start = 0;
    int end = arr.length - 1;

    while (start < end) {
      if (arr[start] + arr[end] == sum) {
        System.out.println("PAIR: " + arr[start] + " " + arr[end]);
        start++;
        end--;
      } else if (arr[start] + arr[end] < sum) {
        start++;
      } else {
        end--;
      }
    }
  }

  /**
   * This method uses HashMap to find the pairs in the array which add up to the given sum.
   *
   * @param nums - input array
   * @param target - target sum
   */
  public static void getPairIndexes(int[] nums, int target) {
    // Map to store the diff value and index of current value
    Map<Integer, Integer> tracker = new HashMap<Integer, Integer>();

    for (int i = 0; i < nums.length; ++i) {
      // If current value is present in map, then we have found a pair
      if (tracker.containsKey(nums[i])) {
        System.out.println("Pair : " + tracker.get(nums[i]) + " " + i);
      } else {
        // Store the diff value and index of current value, so that if
        // corr pair value is encountered we have
        tracker.put(target - nums[i], i);
      }
    }
  }
}
