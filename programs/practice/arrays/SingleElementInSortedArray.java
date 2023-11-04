/*
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10
*/
public class SingleElementInSortedArray {

  public static void main(String[] args) {
    int[] input = {1, 1, 2, 3, 3, 4, 4, 8, 8};
    System.out.println(singleNonDuplicate(input));
    System.out.println(singleNonDuplicateWithBS(input));
  }

  /**
   * Conditions to be checked
   *
   * <p>1. If the array is of size 1 then return the only element present
   *
   * <p>2.if min > max then return -1.
   *
   * <p>3. Calculate mid and check if it is unique element in the surrounding elements
   *
   * <p>4. Check for extremes : if mid is 0 or len - 1 and check the uniquness as well
   *
   * <p>5. or call recusrively left and right halves looking for an element : return non negative
   * one from them
   *
   * @param input
   * @return
   */
  public static int singleNonDuplicateWithBS(int[] input) {
    if (input != null && input.length > 0) {
      if (input.length < 2) {
        return input[0];
      }
      return usingTypicalBinarySearch(input, 0, input.length);
    }
    return -1;
  }

  public static int usingTypicalBinarySearch(int[] nums, int min, int max) {
    if (min <= max) {
      // Calculate the middle index
      int mid = (max + min) / 2;
      if (mid >= 1
          && mid < nums.length - 1
          && nums[mid - 1] != nums[mid]
          && nums[mid] != nums[mid + 1]) {
        // This is a non extreme position in the middle and not at the start or end of the array
        // Check for uniqueness of the element
        return nums[mid];
      } else if ((mid == 0 && nums[0] != nums[1])
          || (mid == nums.length - 1 && nums[mid] != nums[mid - 1])) {
        //Check for extreme positions and uniqueness as well
        return nums[mid];
      } else {
        //if not found then call recursively for left and right halves
        int numLeft = usingTypicalBinarySearch(nums, min, mid - 1);
        int numRight = usingTypicalBinarySearch(nums, mid + 1, max);
        //try to return non nogative one.
        return numLeft == -1 ? numRight : numLeft;
      }
    }
    return -1;
  }

  /**
   * We start by setting lo and hi to be the lowest and highest index (inclusive) of the array, and
   * then iteratively halve the array until we find the single element or until there is only one
   * element left. We know that if there is only one element in the search space, it must be the
   * single element, so should terminate the search.
   *
   * <p>
   *
   * <p>On each loop iteration, we find mid, and determine the odd/ evenness of the sides and save
   * it in a variable called halvesAreEven. By then looking at which half the middle element's
   * partner is in (either last element in the left subarray or first element in the right
   * subarray), we can decide which side is now (or remained) odd-lengthed and set lo and hi to
   * cover the part of the array we now know the single element must be in.
   *
   * <p>The trickiest part is ensuring we update lo and hi correctly based on the values of mid and
   * halvesAreEven. These diagrams should help you understand the cases. When solving problems like
   * this, it's often good to draw a diagram and think really carefully about it to avoid off-by-one
   * errors. Avoid using a guess and check approach.
   */
  public static int singleNonDuplicate(int[] nums) {
    int lo = 0;
    int hi = nums.length - 1;
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      boolean halvesAreEven = (hi - mid) % 2 == 0;
      if (nums[mid + 1] == nums[mid]) {
        // Case 1: Mid’s partner is to the right, and the halves were originally even.
        if (halvesAreEven) {
          lo = mid + 2;
        } else {
          // Case 2: Mid’s partner is to the right, and the halves were originally odd.
          hi = mid - 1;
        }
      } else if (nums[mid - 1] == nums[mid]) {
        // Case 3: Mid’s partner is to the left, and the halves were originally even.
        if (halvesAreEven) {
          hi = mid - 2;
        } else {
          // Case 4: Mid’s partner is to the left, and the halves were originally odd.
          lo = mid + 1;
        }
      } else {
        return nums[mid];
      }
    }
    return nums[lo];
  }
}
