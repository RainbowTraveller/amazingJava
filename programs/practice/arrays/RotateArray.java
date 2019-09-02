/*  Given an array, rotate the array to the right by k steps, where k is non-negative.
*
*  Example 1:
*
*  Input: [1,2,3,4,5,6,7] and k = 3
*  Output: [5,6,7,1,2,3,4]
*  Explanation:
*  rotate 1 steps to the right: [7,1,2,3,4,5,6]
*  rotate 2 steps to the right: [6,7,1,2,3,4,5]
*  rotate 3 steps to the right: [5,6,7,1,2,3,4]
*  Example 2:
*
*  Input: [-1,-100,3,99] and k = 2
*  Output: [3,99,-1,-100]
*  Explanation:
*  rotate 1 steps to the right: [99,-1,-100,3]
*  rotate 2 steps to the right: [3,99,-1,-100]
*/

import java.util.Arrays;
import java.util.stream.Collectors;

public class RotateArray {
    public static void main(String[] args) {
        int[] input = {7,1,2,3,4,5,6,8};
        System.out.println("Original Array : ");
        System.out.println(Arrays.stream(input)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(", ")));
        int n = 3;
        System.out.println("\nAfter Rotating right " + n +  " times");
        //rotateExtraSpace(input, n);
        rotate(input, n);
        System.out.println("Rotated Array : ");
        System.out.println(Arrays.stream(input)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(", ")));
    }

    /**
     * Using new array, extra space
     */
    public static void rotateExtraSpace(int[] input, int k) {
        int len = input.length;
        k = k % len; // if k > length
        int[] temp = new int[len];
        for(int i = 0; i < len; ++i) {
            System.out.println("i " + i + " destination index : " + (i + k) % len);
            temp[(i + k) % len] = input[i];
        }
        for(int i = 0; i < len; ++i) {
            input[i] = temp[i];
        }
    }

    // Kinda fast pointer of the list
    // if we go k steps each time we may come back to starting index
    // eventually. When this happens we have travelled all the cycle
    public static void rotate(int[] input, int k) {
        int len = input.length;
        k = k % len;
        int count = 0;
            int start = 0;
            int currIndex = start;
            int currNum = input[start];
        do {
            int nextIndex = (currIndex + k) % len;
            System.out.println("Curr Index  : " + currIndex + " Start : " + start + " Count : " + count + " next : " + nextIndex);
            int backup = input[nextIndex];
            input[nextIndex] = currNum;
            currNum = backup;
            currIndex = nextIndex;
            count++;
        } while( start != currIndex || count < len);
        System.out.println("Curr Index  : " + currIndex + " Start : " + start);
    }


    //Using reversing
    //1. Reverse entire array
    //2. Reverse first k elements
    //3. Reverse last k elements
    public static void rotateReverse(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

