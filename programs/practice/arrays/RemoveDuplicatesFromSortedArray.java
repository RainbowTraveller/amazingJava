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
 * <p>Input: nums = [1,1,1,2,2,3] Output: 5, nums = [1,1,2,2,3,_] Explanation: Your function should
 * return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively. It does
 * not matter what you leave beyond the returned k (hence they are underscores).
 *
 * <p>Example 2:
 *
 * <p>Input: nums = [0,0,1,1,1,1,2,3,3] Output: 7, nums = [0,0,1,1,2,3,3,_,_] Explanation: Your
 * function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3
 * respectively. It does not matter what you leave beyond the returned k (hence they are
 * underscores).
 */
public class RemoveDuplicatesFromSortedArray {
  public static void main(String[] args) {

    System.out.println(
        RemoveDuplicatesFromSortedArray.removeDuplicates(new int[] {1, 1, 1, 2, 2, 3}));
    System.out.println(
        RemoveDuplicatesFromSortedArray.removeDuplicates(new int[] {0, 0, 1, 1, 1, 1, 2, 3, 3}));
    System.out.println(
        RemoveDuplicatesFromSortedArray.removeDuplicatesSimple(new int[] {1, 1, 1, 2, 2, 3}));
    System.out.println(
        RemoveDuplicatesFromSortedArray.removeDuplicatesSimple(
            new int[] {0, 0, 1, 1, 1, 1, 2, 3, 3}));
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
          cnt++; // modify the occurrence of the found element
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

  /**
   * Simpler approach Here we start from the 3rd element and compare it with the element two
   * positions behind the current position of the pointer If they are different, it means the
   * current element can be included Otherwise, it means the current element is a duplicate that
   * exceeds the allowed count of two This approach effectively ensures that each unique element
   * appears at most twice
   *
   * <p>Time Complexity: O(n), where n is the length of the input array. We traverse the array once.
   *
   * <p>Space Complexity: O(1), as we are using only a constant amount of extra space.
   *
   * <p>The j pointer starts at index 2 because the first two elements are always valid.
   *
   * <p>The loop iterates from index 2 to the end of the array.
   *
   * <p>The if condition nums[i] != nums[j - 2] is the key. It checks if the current element nums[i]
   * is a new unique number or a third (or more) occurrence of a number that's already been placed.
   *
   * <p>If it's a new number, it's placed at the j index, and j is incremented.
   *
   * <p>If it's a duplicate, j is not incremented, effectively "overwriting" the element in the next
   * valid position.
   *
   * <p>The final value of j is the length of the new array. This approach correctly handles the
   * in-place modification and avoids the messy and buggy logic of the provided code.
   */
  public static int removeDuplicatesSimple(int[] nums) {
    if (nums.length <= 2) {
      return nums.length;
    }

    int j = 2; // Pointer for the modified array
    for (int i = 2; i < nums.length; i++) {
      // Compare with the element two positions behind the j-pointer
      // This ensures at most two occurrences of any number
      if (nums[i] != nums[j - 2]) {
        nums[j] = nums[i];
        j++;
      }
    }
    return j;
  }
}
