/**
 * 34. Find First and Last Position of Element in Sorted Array
*  LeetCode Link : https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
*
*  <p>Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
*  <p>If target is not found in the array, return [-1, -1].
*  <p>You must write an algorithm with O(log n) runtime complexity.
*  <p>Example 1:
*  <p>Input: nums = [5,7,7,8,8,10], target = 8
*  <p>Output: [3,4]
*  <p>Example 2:
*  <p>Input: nums = [5,7,7,8,8,10], target = 6
*  <p>Output: [-1,-1]
*  <p>Example 3:
*  <p>Input: nums = [], target = 0
*  <p>Output: [-1,-1]
*  <p>Constraints:
*  <p>0 <= nums.length <= 10^5
*  <p>-10^9 <= nums[i] <= 10^9
*  <p>nums is a non-decreasing array.
*  <p>-10^9 <= target <= 10^9
*/
public class FirstAndLastInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0 ) return new int[]{-1,-1};
        int min = 0, max = nums.length - 1;
        int left = -1, right = =1;
        left = findEdge(nums, target, min, max, true);
        if(left != -1) {
            right = findEdge(nums, target, left, max,false);
        }
        return new int[]{left, right};
    }


    /**
     * Helper method to find the leftmost or rightmost index of the target
     * @param nums The input array
     * @param target The target value to search for
     * @param min The minimum index of the current search range
     * @param max The maximum index of the current search range
     * @param findLeft Boolean flag to indicate whether to find the leftmost (true) or rightmost (false) index
     * @return The index of the leftmost or rightmost occurrence of the target, or -1 if not found
     */
    public int findEdge(int[] nums, int target, int min, int max, boolean findLeft) {
        if(min < max){
            int mid = min + max / 2;
            if(nums[mid] == target) {
                if(findLeft){
                    if(mid == min || nums[mid - 1] != target) {
                        return mid;
                    } else {
                        return findEdge(target, min, mid - 1, true);
                    }
                } else {
                    if(mins == max || nums[mid + 1] != target) {
                        return mid;
                    } else {
                        return findEdge(target, mid + 1, max, false);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstAndLastInSortedArray obj = new FirstAndLastInSoetedArray();
        int[] nums = {5,7,7,8,8,10};
        int targer = 7;
        int [] result = obj.serachRange(nums, target);
        System.out.println("First and Last Position of Element in Sorted Array:[" + result[0] + ", " + result[1] + "]");
    }
}
