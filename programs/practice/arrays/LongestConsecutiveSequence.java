import java.util.Arrays;

/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.
Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9


NOTE :
The aim is to find sequence of numbers which may be anywhere in the original input array but when sorted
form longest sequence of consecutive numbers. This is not same as increasing, the word consicutive is important
 */

public class LongestConsecutiveSequence {
  public static void main(String[] args) {
    System.out.println(longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
  }

  public static int longestConsecutive(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    //Sort
    Arrays.sort(nums);
    int longestStreak = 1;
    int currStreak = 1;
    //Start from 1 as we need to compare with previous no. in array.
    for (int i = 1; i < nums.length; ++i) {
      //Check if adjacent nos are not same
      if (nums[i] != nums[i - 1]) {
        //Check if they differ by 1
        if (nums[i] == nums[i - 1] + 1) {
          //if consecutive keep increasing current streak count
          currStreak++;
        } else {
          //if not select longest one based on what we have so far
          longestStreak = Math.max(longestStreak, currStreak);
          currStreak = 1;
        }
      }
    }
    //Do not forget to check for last streak obtained
    longestStreak = Math.max(longestStreak, currStreak);
    return longestStreak;
  }
}
