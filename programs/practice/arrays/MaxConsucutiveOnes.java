/*
These are variations of the Maximum Consecutive Ones
*/

public class MaxConsucutiveOnes {

  public static void main(String[] args) {
    MaxConsucutiveOnes maxConsucutiveOnes = new MaxConsucutiveOnes();

    int[] nums = {1, 0, 1, 1, 0, 1};
    System.out.println("Simple Consecutive : " + maxConsucutiveOnes.findMaxConsecutiveOnes(nums));
    System.out.println("Zero Flip : " + maxConsucutiveOnes.findMaxConsecutiveOnesOneZeroFlip(nums));
    System.out.println(
        "Zero Flip Window : " + maxConsucutiveOnes.findMaxConsecutiveOnesOneZeroFlipWindow(nums));
    int[] numsLonger = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
    System.out.println(
        "K Zero Flip Window : "
            + maxConsucutiveOnes.findMaxConsecutiveOnesOneZeroFlipWindowNZeros(numsLonger, 3));
  }

  /*
   PART : 1
   Given a binary array nums, return the maximum number of consecutive 1's in the array.

   Example 1:

   Input: nums = [1,1,0,1,1,1]
   Output: 3
   Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
   Example 2:

   Input: nums = [1,0,1,1,0,1]
   Output: 2
  */
  public int findMaxConsecutiveOnes(int[] nums) {
    int cons = 0, currCons = 0;

    for (int num : nums) {
      if (num == 1) {
        // When one is found just count
        currCons++;
      } else {
        // if not check with the count found so far
        // select the max one
        cons = Math.max(cons, currCons);
        // Reset the count for current calculations
        currCons = 0;
      }
    }
    return Math.max(cons, currCons);
  }

  /*
     PART : 2
     Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
     Example 1:

     Input: nums = [1,0,1,1,0]
     Output: 4
     Explanation:
     - If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
     - If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
     The max number of consecutive ones is 4.
     Example 2:

     Input: nums = [1,0,1,1,0,1]
     Output: 4
     Explanation:
     - If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
     - If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
     The max number of consecutive ones is 4.
  */

  public int findMaxConsecutiveOnesOneZeroFlip(int[] nums) {
    // Tracks if the any zero is encountered so far
    boolean isZero = false;
    // Counting ones before and after zero
    int before = 0, after = 0;
    // Counting ones
    int cons = 0;

    if (nums.length <= 1) return nums.length;

    for (Integer num : nums) {
      // Handling when zero is already encountered
      if (isZero) {
        if (num == 0) { // This is second zero encountered
          /*
          here we have counted before and after
          so anything before previous zero is irrelevant
          hence anything after that prev 0 becomes before for current one
          and after is reset to 0
           */
          before = after;
          after = 0;
        } else {
          // if zero is encountered and then this is 1 then
          // it is after that is incremented
          after++;
        }
      } else {
        if (num == 0) {
          // First zero, acknowledge it
          isZero = true;
        } else {
          // No zero yet so increment before count
          before++;
        }
      }
      // Alaways keep track of the consecutive ones
      // notice the isZero condition for accounting flipping zero only when one has
      // encountered
      cons = Math.max(cons, before + after + (isZero ? 1 : 0));
    }
    return cons;
  }

  public int findMaxConsecutiveOnesOneZeroFlipWindow(int[] nums) {
    int noOfZeros = 0;
    int left = 0, right = 0;
    int cons = 0;
    // right index tracks the array
    while (right < nums.length) {
      // Zero enconutered
      if (nums[right] == 0) {
        // Account for it
        noOfZeros++;
      }
      // Opps 2 zeros already
      while (noOfZeros == 2) {

        if (nums[left] == 0) {
          // un account for previous zero
          noOfZeros--;
        }
        // Shrink the window
        left++;
      }
      // Window will contain maximum 1 zero
      // Count the window size as all consecutive ones
      cons = Math.max(cons, right - left + 1);
      // Increase the window scope
      right++;
    }
    return cons;
  }

  /*
  Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

  Example 1:

  Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
  Output: 6
  Explanation: [1,1,1,0,0,1,1,1,1,1,1]
  Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
  Example 2:

  Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
  Output: 10
  Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
  Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
  */

  public int findMaxConsecutiveOnesOneZeroFlipWindowNZeros(int[] nums, int n) {
    int noOfZeros = 0;
    int left = 0, right = 0;
    int cons = 0;
    // right index tracks the array
    while (right < nums.length) {
      // Zero enconutered
      if (nums[right] == 0) {
        // Account for it
        noOfZeros++;
      }
      // Opps 2 zeros already
      while (noOfZeros == n + 1) {

        if (nums[left] == 0) {
          // un account for previous zero
          noOfZeros--;
        }
        // Shrink the window
        left++;
      }
      // Window will contain maximum 1 zero
      // Count the window size as all consecutive ones
      cons = Math.max(cons, right - left + 1);
      // Increase the window scope
      right++;
    }
    return cons;
  }
}
