/*  Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
*
*  Your algorithm's runtime complexity must be in the order of O(log n).
*
*  If the target is not found in the array, return [-1, -1].
*
*  Example 1:
*
*  Input: nums = [5,7,7,8,8,10], target = 8
*  Output: [3,4]
*  Example 2:
*
*  Input: nums = [5,7,7,8,8,10], target = 6
*  Output: [-1,-1]
*/

public class SearchRange {
    public static void main(String[] args) {
        SearchRange sr = new SearchRange();
        int[] nums = { 5, 7, 7, 8, 8, 10 };

        sr.searchRangeEfficient(nums, 8);
    }

    /*
     * The overall algorithm works fairly similarly to the linear scan approach,
     * except for the subroutine used to find the left and rightmost indices
     * themselves. Here, we use a modified binary search to search a sorted array,
     * with a few minor adjustments. First, because we are locating the leftmost (or
     * rightmost) index containing target (rather than returning true iff we find
     * target), the algorithm does not terminate as soon as we find a match.
     * Instead, we continue to search until lo == hi and they contain some index at
     * which target can be found. The other change is the introduction of the left
     * parameter, which is a boolean indicating what to do in the event that target
     * == nums[mid]; if left is true, then we "recurse" on the left subarray on
     * ties. Otherwise, we go right. To see why this is correct, consider the
     * situation where we find target at index i. The leftmost target cannot occur
     * at any index greater than i, so we never need to consider the right subarray.
     * The same argument applies to the rightmost index.
     *
     * The first animation below shows the process for finding the leftmost index,
     * and the second shows the process for finding the index right of the rightmost
     * index.
     *
     */

    // returns leftmost (or rightmost) index at which `target` should inserted in
    // sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public int[] searchRangeEfficient(int[] nums, int target) {
        int[] targetRange = { -1, -1 };

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;

        return targetRange;
    }

    /*
     * Uses vanilla binary search but scans for the left and right end so O(n)
     *
     */
    public int[] searchRange(int[] nums, int target) {
        int start = -1;
        int end = -1;

        if (nums != null) {
            int position = binarySearch(nums, target, 0, nums.length - 1);
            if (position >= 0) {
                start = position;
                end = position;
                while (start != 0 && nums[start - 1] == target) {
                    start--;
                }
                while (end != nums.length - 1 && nums[end + 1] == target) {
                    end++;
                }
            }
        }

        return new int[] { start, end };
    }

    public static int binarySearch(int[] nums, int target, int min, int max) {
        if (min > max) {
            return -1;
        }

        int mid = (min + max) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < target) {
            min = mid + 1;
        } else {
            max = mid - 1;
        }

        return binarySearch(nums, target, min, max);
    }
}
