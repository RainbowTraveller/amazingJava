/*Given an array of integers, find if the array contains any duplicates.
 *
 *Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 *
 *Example 1:
 *
 *Input: [1,2,3,1]
 *Output: true
 *Example 2:
 *
 *Input: [1,2,3,4]
 *Output: false
 *Example 3:
 *
 *Input: [1,1,1,3,3,4,3,2,4,2]
 *Output: true
 */

import java.util.Arrays;

public class ContainsDuplicates {
  public static void main(String[] args) {

    // int[] input = { 1,2,3,1  };
    int[] input = {1, 2, 3, 4};
    // int[] input = { 1,1,1,3,3,4,3,2,4,2 };
    System.out.println("Contains Duplicates : " + containsDuplicate(input));
  }

  /**
   * Method to check if the array contains duplicates
   *
   * @param nums
   * @return boolean
   */
  public static boolean containsDuplicate(int[] nums) {
    if (nums != null && nums.length > 0) {
      Arrays.sort(nums);
      for (int i = 0; i < nums.length - 1; ++i) {
        if (nums[i + 1] == nums[i]) {
          // System.out.println(nums[i] +  "  " + nums[i + 1]);
          return true;
        }
      }
      return false;
    }
    return false;
  }
}
