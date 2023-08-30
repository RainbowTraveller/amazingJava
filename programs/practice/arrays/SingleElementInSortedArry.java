public class SingleElementInSortedArray {

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

    public static void main(String[] args) {

    }

    public static int usingTypicalBinarySearch(int[] nums, int min, int max) {
        if(min > max) {
            return -1;
        }
        int mid = (max + min) / 2;
        if(mid >= 1 && mid < nums.length - 1 && nums[mid - 1] != nums[mid] 
                    && nums[mid] != nums[mid + 1]) {
                        return nums[mid];
                    } else if((mid == 0 && nums[0] != nums[1]) || (mid == nums.length - 1 && nums[mid] != nums[mid - 1])){
                        return nums[mid];
                    } else {
                        int numLeft = binarySearch(nums, min, mid - 1);
                        int numRight = binarySearch(nums, mid + 1, max);
                        return numLeft == -1 ? numRight : numLeft;
                    }
    }

    /**
     * We start by setting lo and hi to be the lowest and highest index (inclusive) of the array,
     * and then iteratively halve the array until we find the single element or until there is only one element left. 
     * We know that if there is only one element in the search space, it must be the single element, so should terminate the search.


     On each loop iteration, we find mid, and determine the odd/ evenness of the sides and save it in a variable called halvesAreEven.
     By then looking at which half the middle element's partner is in (either last element in the left subarray or 
     first element in the right subarray), we can decide which side is now (or remained)
     odd-lengthed and set lo and hi to cover the part of the array we now know the single element must be in.

    The trickiest part is ensuring we update lo and hi correctly based on the values of mid and halvesAreEven. 
    These diagrams should help you understand the cases. When solving problems like this, 
    it's often good to draw a diagram and think really carefully about it to avoid off-by-one errors. 
    Avoid using a guess and check approach.
     */
    public static int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean halvesAreEven = (hi - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                //Case 1: Mid’s partner is to the right, and the halves were originally even.
                if (halvesAreEven) {
                    lo = mid + 2;
                } else {
                    //Case 2: Mid’s partner is to the right, and the halves were originally odd.
                    hi = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                //Case 3: Mid’s partner is to the left, and the halves were originally even.
                if (halvesAreEven) {
                    hi = mid - 2;
                } else {
                    //Case 4: Mid’s partner is to the left, and the halves were originally odd.
                    lo = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo];
    }
}
