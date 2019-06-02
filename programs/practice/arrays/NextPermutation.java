/*
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation {
    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the current permutation (, separated)");
        String input = sc.nextLine();
        String[] values = input.split(",");
        int [] nums = Arrays.stream(values)
                        .mapToInt(Integer::parseInt)
                        .toArray();
        System.out.println("Next nextPermutation : " + nextPermutation(nums));
    }

    public static String nextPermutation(int[] nums) {
        //Start from the end and find first element less then previous element
        //i.e. smallest element so far from right
        if(nums != null) {
            int end = nums.length - 1;
            while(end > 0 && nums[end - 1] >= nums[end]) {
                end--;
            }

            int small = end - 1;
            //Now find smallest element greater then above found element in the same area
            //start from end and find one.
            //This is to get next element in lexicographic order
            if( small >= 0) {
                int index = nums.length - 1;
                while(index >=0 && nums[index] <= nums[small]) {
                    index--;
                }
                //System.out.println(Arrays.toString(nums));
                //Swap these 2 elements
                int temp = nums[small];
                nums[small] = nums[index];
                nums[index] = temp;
            }
            //Sort array after this element
            //this makes sure that smallest arrangement of all right side elements is done
            //maintaining lexicographical ordering
            Arrays.sort(nums, small + 1, nums.length);
        }
        return Arrays.toString(nums);
    }
}
