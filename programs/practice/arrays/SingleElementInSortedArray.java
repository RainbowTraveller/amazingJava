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
    System.out.println("Binary Search Loop : Complex " + singleNonDuplicate(input));
    System.out.println(
        "Binary Search Loop : Compact and efficient : " + singleNonDuplicateWithBS(input));
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

  /**
   * Typical binary search implementation with some additional checks for uniqueness of the element
   * at mid position.
   *
   * <p>The code snippet if (mid % 2 == 1) { mid--; } is a clever optimization in the binary search
   * algorithm for this specific problem. It ensures that the mid index you're comparing is always
   * at the start of a potential pair.
   *
   * <p>The logic works because the single element breaks the predictable pattern of pairs.
   *
   * <p>In a correctly paired array (like [1,1,2,2,3,3]), the first element of any pair is always at
   * an even index (0, 2, 4, ...).
   *
   * <p>If the single element is at an odd index, like [1,1,2,3,3,4,4], then 2 is the unique
   * element, and all pairs after it are shifted to the left, and start on odd indexes. (3,3) is at
   * indices 3,4 and (4,4) is at 5,6.
   *
   * <p>To keep the binary search consistent and simple, we always want to compare a number with its
   * partner. By forcing mid to be an even number, we guarantee we are checking nums[mid] against
   * nums[mid + 1].
   *
   * <p>So, the line if (mid % 2 == 1) { mid--; } ensures we're always at the first element of a
   * potential pair, which simplifies the logic for deciding which half of the array to search next.
   * We can then confidently check nums[mid] == nums[mid + 1] to determine if the single element is
   * to the left or right.
   *
   * <p>The Core Logic Explained The algorithm works on a simple principle: if an element is part of
   * a pair, its partner will always be at the very next index (nums[i+1]) if and only if all the
   * elements before it also appear in pairs.
   *
   * <p>Force mid to be Even: The line if (mid % 2 == 1) { mid--; } ensures that our mid pointer
   * always lands on an even index. This makes the next check predictable. We are essentially
   * forcing our binary search to only look at elements that would be the first in a normal pair.
   *
   * <p>Check for a Match: We then check nums[mid] == nums[mid + 1]. There are two possibilities:
   *
   * <p>Case A: nums[mid] == nums[mid + 1] (The elements match). This means we have found a regular
   * pair at indices mid and mid + 1. This proves that all elements to the left of mid must also be
   * in pairs (since the array is sorted and the single element would have broken the pattern).
   * Therefore, the unique element must be in the right half of the array, starting from mid + 2.
   *
   * <p>We can discard the left half of the search space: left = mid + 2.
   *
   * <p>Case B: nums[mid] != nums[mid + 1] (The elements do not match). This is the crucial part.
   * Since we know nums[mid] is at an even index, this mismatch can only mean one thing: either
   * nums[mid] is the single unique element itself, or the single element is somewhere to the left
   * of it, which has shifted the pairs so that nums[mid] is now the second element of a pair. In
   * both scenarios, the unique element must be in the left half of the array.
   *
   * <p>We can discard the right half of the search space: right = mid.
   *
   * <p>By forcing mid to be at an even index, we simplify the decision-making process into a
   * single, reliable check. We don't have to worry about the complexities of shifted pairs; the
   * logic handles it automatically.
   *
   * @param nums array of integers
   * @param min minimum index
   * @param max maximum index
   * @return unique element or -1 if not found
   */
  public static int usingTypicalBinarySearch(int[] nums, int min, int max) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      // Ensure mid is even. If odd, move it back one index
      if (mid % 2 == 1) {
        mid--;
      }
      // Check the pair starting at mid
      if (nums[mid] == nums[mid + 1]) {
        // The unique element must be in the right half
        // It hasn't appeared yet
        left = mid + 2;
      } else {
        // The unique element must be in the left half
        // It could be at mid
        right = mid;
      }
    }
    return nums[left];
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
