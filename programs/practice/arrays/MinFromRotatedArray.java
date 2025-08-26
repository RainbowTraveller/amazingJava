public class MinFromRotatedArray {
  public static void main(String[] args) {
    int[] a = {4, 5, 6, 7, 0, 1, 2};
    System.out.println("Min : " + findMin(a));
    System.out.println("Min Optimal: " + findMinOptimal(a));
    int[] b = {6, 7, 0, 1, 2, 4, 5};
    System.out.println("Min : " + findMin(b));
    System.out.println("Min Optimal: " + findMinOptimal(b));
    int[] c = {3, 1, 2};
    System.out.println("Min : " + findMin(c));
    System.out.println("Min Optimal: " + findMinOptimal(c));
    int[] d = {5, 4, 3, 2, 1};
    System.out.println("Min : " + findMin(d));
    System.out.println("Min Optimal: " + findMinOptimal(d));
    int[] e = {1, 2, 3, 4, 5};
    System.out.println("Min : " + findMin(e));
    System.out.println("Min Optimal: " + findMinOptimal(c));
  }

  // 4,5,6,7,0,1,2
  // 6,7,0,1,2,4,5
  public static int findMin(int[] nums) {
    int min = Integer.MIN_VALUE;
    if (nums != null) {
      min = helper(nums, 0, nums.length - 1);
    }
    return min;
  }

  /**
   * 1. Find mid <br>
   * 2. Compare mid with start and end <br>
   * 3. If mid > start, then left part is sorted, so min must be in right part <br>
   * 4. If mid < start, then right part is sorted, so min must be in left part <br>
   * 5. Repeat until s <= e
   */
  public static int helper(int[] nums, int s, int e) {
    if (s <= e) {
      if (e - s == 1) {
        return Math.min(nums[s], nums[e]);
      } else if (e == s) {
        return nums[s];
      }

      int mid = (s + e) / 2;
      // System.out.println("Mid : " + mid + " S " + s + " E " + e);
      // Consider mid element as well, as it may be the one
      // don't ignore it with mid + 1 or mid - 1
      if (nums[mid] > nums[s]) { // middle greater than start
        if (nums[s] > nums[e]) { // start is greater than end
          return helper(nums, mid, e);
        }
        return helper(nums, s, mid);
      } else if (nums[mid] < nums[s]) {
        if (nums[e] < nums[mid]) {
          return helper(nums, mid, e);
        } else {
          return helper(nums, s, mid);
        }
      }
    }
    return -1;
  }

  /**
   * Optimal approach using binary search <br>
   * Time complexity: O(log n) <br>
   * Space complexity: O(1) <br>
   * Initialize Pointers: The algorithm starts by setting two pointers, left and right, to the
   * beginning and end of the array, respectively. <br>
   * This defines the current search space. left = 0 right = nums.length - 1 <br>
   * Binary Search Loop: <br>
   * The while (left < right) loop is the heart of the algorithm. It continues as long as the search
   * space has more than one element. The loop will terminate when left and right converge to the
   * same index, which will be the index of the minimum element. Inside the loop, a mid pointer is
   * calculated. The formula left + (right - left) / 2 is used to prevent potential integer overflow
   * that could occur with (left + right) / 2 for very large arrays. The Core Comparison: At each
   * step, the algorithm performs a crucial comparison: if (nums[mid] > nums[right]). This single
   * check tells us which half of the array contains the minimum element. <br>
   * Case 1: nums[mid] > nums[right] This scenario indicates that the minimum element must be in the
   * right half of the current search space. Why? Because the array is sorted, if nums[mid] is
   * greater than nums[right], it means the "rotation point" (the minimum element) lies somewhere
   * between mid and right. The left side of the array (nums[left] to nums[mid]) is sorted and does
   * not contain the minimum. To discard the left half, we update the left pointer to mid + 1. <br>
   * Case 2: nums[mid] <= nums[right] This means the minimum element is either at mid or somewhere
   * in the left half (nums[left] to nums[mid]). The right side (nums[mid] to nums[right]) is sorted
   * and does not contain the minimum (unless nums[mid] is the minimum itself). To narrow the
   * search, we discard the right half by setting right = mid. We don't use mid - 1 because mid
   * itself could be the minimum element. <br>
   * Loop Termination and Result: The loop continues until left and right point to the same index.
   * At this point, the search space has been reduced to a single element. This element is
   * guaranteed to be the minimum, as all other elements have been correctly discarded. The function
   * then returns nums[left] (or nums[right], since they are the same).
   */
  public static int findMinOptimal(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int left = 0;
    int right = nums.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      // This is the correct way to narrow the search.
      // If the middle element is greater than the rightmost element,
      // the pivot (minimum) must be in the right half.
      if (nums[mid] > nums[right]) {
        left = mid + 1;
      }
      // If the middle element is less than or equal to the rightmost,
      // the pivot must be in the left half (including the middle).
      else {
        right = mid;
      }
    }
    // When the loop terminates, left and right will point to the same index,
    // which is the minimum element.
    return nums[left];
  }
}
