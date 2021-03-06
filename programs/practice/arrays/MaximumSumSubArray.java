// Find a subarray from a given array such that it's sum is largest
// The original array may contain -ve numbers as well
import java.util.List;
import java.util.ArrayList;

public class MaximumSumSubArray {
    public static void main(String[] args) {

        int [] input = { -2,1,-3,4,-1,2,1,-5,4 };
        System.out.println(MaximumSumSubArray.getMaxSumSubarray(input));
        System.out.println(MaximumSumSubArray.getMaxSumSubarrayNaive(input));
    }

    static List<Integer> getMaxSumSubarray(int[] input) {
        List<Integer> elements = null;
        if(input != null && input.length > 0) {

            //Final max value of the sum which will be maximum
            int maxSoFar = input[0];

            //Maximum sum till this element continuously increasing
            int maxUptoThis = input[0];

            //list will host the subarray elements
            elements = new ArrayList<Integer>();
            //start-end indexes to get subarray
            int start  = -1;
            int end = -1;
            for(int i = 1; i < input.length; ++i) {
                maxUptoThis = Math.max(maxUptoThis + input[i], input[i]);
                if(maxUptoThis == input[i]) {
                    start = i;
                }
                maxSoFar = Math.max(maxUptoThis, maxSoFar);
                if(maxSoFar == maxUptoThis) {
                    end = i;
                }
            }
            System.out.println("Start : " + start + " End : " + end + " Max Sum : " + maxSoFar);
            for(int i = start; i <= end; ++i) {
                elements.add(input[i]);
            }
        }
        return elements;
    }

    public static List<Integer> getMaxSumSubarrayNaive( int [] arr ) {
        List<Integer> elements = null;
        if(arr != null && arr.length > 0) {
            int max = Integer.MIN_VALUE;
            int start  = 0;
            int end = 0;
            //list will host the subarray elements
            elements = new ArrayList<Integer>();
            for(int i = 0; i < arr.length; ++i) {
                int curr = 0;
                for(int j = i; j < arr.length; ++j) {
                    curr += arr[j];
                    max = Math.max(max, curr);
                    if(max == curr) {
                        start = i;
                        end = j;
                    }
                }
            }
            System.out.println("MAX : " + max + " Start : " + start + " End : " + end);
            for(int i = start; i <= end; ++i) {
                elements.add(arr[i]);
            }
        }
        return elements;
    }
}
