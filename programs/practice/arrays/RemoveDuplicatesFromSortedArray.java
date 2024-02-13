/**
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such
 * that each unique element appears at most twice. The relative order of the elements should be kept
 * the same.
 *
 * <p>Since it is impossible to change the length of the array in some languages, you must instead
 * have the result be placed in the first part of the array nums. More formally, if there are k
 * elements after removing the duplicates, then the first k elements of nums should hold the final
 * result. It does not matter what you leave beyond the first k elements.
 *
 * <p>Return k after placing the final result in the first k slots of nums.
 *
 * <p>Do not allocate extra space for another array. You must do this by modifying the input array
 * in-place with O(1) extra memory.
 *
 * <p>Custom Judge:
 *
 * <p>The judge will test your solution with the following code:
 *
 * <p>int[] nums = [...]; // Input array int[] expectedNums = [...]; // The expected answer with
 * correct length
 *
 * <p>int k = removeDuplicates(nums); // Calls your implementation
 *
 * <p>assert k == expectedNums.length; for (int i = 0; i < k; i++) { assert nums[i] ==
 * expectedNums[i]; } If all assertions pass, then your solution will be accepted.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [1,1,1,2,2,3] Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should
 * return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively. It does
 * not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 * <p>Input: nums = [0,0,1,1,1,1,2,3,3] Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3
 * respectively. It does not matter what you leave beyond the returned k (hence they are underscores).
 */
public class RemoveDuplicatesFromSortedArray {
  public static void main(String[] args) {

    System.out.println( RemoveDuplicatesFromSortedArray.removeDuplicates(new int[] {1,1,1,2,2,3}));
    System.out.println( RemoveDuplicatesFromSortedArray.removeDuplicates(new int[] {0,0,1,1,1,1,2,3,3}));
  }

  public static int removeDuplicates(int[] nums) {

    int len = nums.length;
    // Start index, indicates valid position to copy a number to
    // This is the return value pointing to the end of the array
    // which is in the desired situation
    int s = 1;
    // This is tracking the new elements to be considered
    int e = 1;
    // Start with first element
    int curr = nums[0];
    int cnt = 1;
    while (e <= len - 1) { // till the tracking reaches the end
      if (nums[e] == curr) { // so current no is already occurred
        if (cnt < 2) { // looks like not 2 times in a row
          nums[s] = nums[e]; // sometimes this is redundent copy especially initially
          s++; // point to the new position where the next element will be added
          cnt++; // modify the occurrece of the the found element
        }
        e++; // point to then next number to be considered
      } else { // numbers are not equal, indicating the new number has occurred for the first time
        nums[s] = nums[e]; // just move it to appropriate location pointed by the s index
        curr = nums[e]; // curr is the new number whose occurrences we need to track
        cnt = 1; // so far the new number was found only once
        s++; // increment both pointers
        e++;
      }
    }
    return s;
  }
}
