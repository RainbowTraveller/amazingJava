/*
 *  Given a sorted array and a target value, return the index if the target is found.
 *  If not, return the index where it would be if it were inserted in order.
 *  You may assume no duplicates in the array.
 *  Example 1:
 *
 *  Input: [1,3,5,6], 5
 *  Output: 2
 *  Example 2:
 *
 *  Input: [1,3,5,6], 2
 *  Output: 1
 *  Example 3:
 *
 *  Input: [1,3,5,6], 7
 *  Output: 4
 *  Example 4:
 *
 *  Input: [1,3,5,6], 0
 *  Output: 0
 */
public class InsertPosition {
    public static void main(String[] args) {
        int[] input = { 1, 3, 5, 6 };
        System.out.println(searchInsert(input, 5));
        System.out.println(searchInsert(input, 2));
        System.out.println(searchInsert(input, 7));
        System.out.println(searchInsert(input, 0));
    }

    public static int searchInsert(int[] nums, int target) {
        if(nums != null) {
            //Corner case 1
            if(target < nums[0]) {
                return 0;
            }

            //Corner case 2
            if(target > nums[nums.length -1]) {
                return nums.length;
            }
            return helper(nums, 0, nums.length - 1, target);
        }
        return -1;
    }

    public static int helper(int[] nums, int start, int end, int target) {
        while(true) {
            int mid = (start + end) / 2;
             if( nums[ mid ] == target) {
                return mid;
             }
             if ( nums[ mid ] < target ) {
                 //Check if target false between lesser and greater number
                 if( mid + 1 < nums.length && nums[mid + 1] > target) {
                     return mid + 1;
                 }
                 start = mid + 1;

             } else {
                 //Check if target false between greater and lesser number
                 if( mid - 1 >= 0 && nums[mid - 1] < target) {
                     return mid;
                 }
                 end = mid - 1;
             }
        }
    }
}
