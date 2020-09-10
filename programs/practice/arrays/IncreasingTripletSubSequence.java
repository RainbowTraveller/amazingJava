/*  Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
*
*  Formally the function should:
*
*  Return true if there exists i, j, k
*  such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
*  Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
*
*  Example 1:
*
*  Input: [1,2,3,4,5]
*  Output: true
*  Example 2:
*
*  Input: [5,4,3,2,1]
*  Output: false
*/

public class IncreasingTripletSubSequence {
    public static void main(String[] args) {
        IncreasingTripletSubSequence its = new IncreasingTripletSubSequence();
        //int[] arr = { 5, 7, 1, 2, 3 };
         //int[] arr = {5,7,12,9};
         int[] arr = {5,7,1,-4,-5,-6,-7,3};
        System.out.println(its.increasingTripletSubSequence(arr));

    }

    public boolean increasingTripletSubSequence(int[] arr) {
        int len = arr.length;
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        for (int i = 0; i < len; ++i) {
            if (arr[i] < smallest) {
                smallest = arr[i];

            } else if (arr[i] > smallest && arr[i] < secondSmallest) {
                secondSmallest = arr[i];
            } else if (arr[i] > secondSmallest) {
                return true;
            }

        }
        return false;

    }
}
