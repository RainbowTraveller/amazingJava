/*  Given an unsorted integer array, find the smallest missing positive integer.
*
*  Example 1:
*
*  Input: [1,2,0]
*  Output: 3
*  Example 2:
*
*  Input: [3,4,-1,1]
*  Output: 2
*  Example 3:
*
*  Input: [7,8,9,11,12]
*  Output: 1
*  Note:
*
*  Your algorithm should run in O(n) time and uses constant extra space.
*/
public class FirstMissingPositive {
    public static void main(String[] args) {

    }

    public int firstMissingPositive(int[] nums) {
        /*First things first check the array length. Now for length l we can have max. number in that
         * array l
         * e.g. [3,5] : here least positive no. is 1
         *      now this will contain [1,2] as first 2 positive nos. similarly [3,5,6] --> [1,2,3] are first 3 consecutive positive nos. hence max no. = 3
         *      if we have something like [1,3,5] --> then no. is 2 again it is less than 3 which is max no.
         *      So the conclusion is ignore all nos. <= 0 and greater than length of the input array. We won't require those numbers for searching our answer.
         */
        int len = nums.length;
        //Empty array means first missing no. is 1
        if(len == 0) { return 1;}

        boolean one = false;
        for(int i = 0; i < len; ++i) {
            //If a number is <= 0 or > array length then replace it with 1
            if(nums[i] <= 0 || nums[i] > len)  {
                nums[i] = 1;
            } else if(nums[i] == 1) {
                //Oh..we found 1 so record it
                one = true;
            }
        }

        //One not found obviously the first missing positive is 1
        if(!one) {
            return 1;
        }

        //We found 1 and there is only 1 element in array, hence answer is 2
        if( len == 1) { return 2;}

        /* now we user places 0 to n - 1 to track numbers 1 to n.
         * where details for n being stored at position 0
         * Here we track all numbers and mark number at the index of given number that is encountered to
         * a -ve value
         * e.g. currnum = 4 then we mark arr[4] = - arr[4]. We don't want to do it multiple times, so
         * each time we take abs value of at the index.
         * if num == length put it at index 0 as we have only length -1 indexes
         */
        for(int i = 0; i < len; ++i) {
            int num = Math.abs(nums[i]);
            if(num == len) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[num] = -Math.abs(nums[num]);
            }
        }

        //Now check every index for positive values
        //first index encountered is the missing smallest positive
        for(int i = 1;i < len; ++i) {
            if(nums[i] > 0) {
                return i;
            }
        }

        //oh was not found between 1 and last index
        //check for 0th index
        if(nums[0] > 0) {
            return len;
        }
        // not found still....hummm looks like it has 1 to len elements so next smallest positive will be len + 1
        return len + 1;
    }
}

