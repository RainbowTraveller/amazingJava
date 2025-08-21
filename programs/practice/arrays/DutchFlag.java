/* Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */

/*

The idea of solution is to move curr pointer along the array, if nums[curr] = 0 - swap it with nums[p0], if nums[curr] = 2 - swap it with nums[p2].


Initialise the rightmost boundary of zeros : p0 = 0. During the algorithm execution nums[idx < p0] = 0.
Initialise the leftmost boundary of twos : p2 = n - 1. During the algorithm execution nums[idx > p2] = 2.
Initialise the index of current element to consider : curr = 0.
While curr <= p2 :

    If nums[curr] = 0 : swap currth and p0th elements and move both pointers to the right.
    If nums[curr] = 2 : swap currth and p2th elements. Move pointer p2 to the left.
    If nums[curr] = 1 : move pointer curr to the right.

*/

public class DutchFlag {
  public static void main(String[] args) {
    int[] nums = new int[] {2, 0, 2, 1, 1, 0};
    DutchFlag.sortColors(nums);
    for (int i : nums) {
      System.out.println(i);
    }
    // Arrays.stream(nums).forEach(System::println);
  }

  public static void sortColors(int[] nums) {
    int index_0 = 0;
    int index_2 = nums.length - 1;
    int i = 0;
    int temp = 0;
    while (i <= index_2) {
      if (nums[i] == 2) {
        temp = nums[index_2];
        nums[index_2] = 2;
        nums[i] = temp;
        index_2--;
      } else if (nums[i] == 0) {
        temp = nums[index_0];
        nums[index_0] = 0;
        nums[i] = temp;
        index_0++;
        i++;
      } else {
        i++;
      }
    }
  }
}
