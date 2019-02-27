// Find a subarray from a given array such that it's product is largest
// The original array may contain -ve numbers as well
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MaxProdSubArray {
    public static void main( String[] args ) {
        int[] arr = { 1, 2, -4, 5, -2, 3, -5, 7 };
        List<Integer> input = new ArrayList<Integer>();
        for(Integer i : arr) {
            input.add(i);
        }
        System.out.println("Max Continuous Product : " +  maxProduct(input));
        int[] arrz = { 0, 0, -4, 0, -20, 0, -3, 0 };
        System.out.println("Max Continuous Product Zeros : " +  maxProductWithZero(arrz));
    }

    /*
     *  This is very naive and won't handle cases like [0, 0, 0, -2]
     *  As basic initialization is done to 1
     */
    public static int maxProduct(List<Integer> numbers) {
        int maxSoFar = 1;
        int maxEndingHere = 1;
        int minEndingHere = 1;

        for( Integer current : numbers) {
            //Number is positive no issue
            if( current > 0) {
                //maxEndingHere *= current; //Max ending here will get bigger
                maxEndingHere = Math.max(current,   maxEndingHere * current); //for visual consistency with other function
                minEndingHere = Math.min(1,         minEndingHere * current);//Choose min appropriately or 1 is always there
            }
            if( current == 0) {
                //whatever value of product till here
                //here it becomes zero, so back to square one
                //initialize to 1
                maxEndingHere = 1;
                minEndingHere = 1;
            }

            if( current < 0 ) {
                //Now here turning of table
                //maxEndingHere = 540 minEndingHere = 1 say
                //current no. is -4
                //prevMin = 1
                //minEndingHere = -2160
                //maxEndingHere = 1
                int prevMin = minEndingHere;
                minEndingHere = Math.min( current,  maxEndingHere * current);
                maxEndingHere = Math.max( 1,        prevMin * current );
            }
            maxSoFar = Math.max( maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    /*
        Simplified logic followed by compact version of the
        same function
    */
    public static int maxProdWithZero( int[] nums ) {
        int max_ending_here = 0;
        int min_ending_here = 0;
        int max_so_far = 0;

        if(nums != null && nums.length > 0) {
            //not 1 but first element is used for
            //initialization
            max_ending_here = nums[0];
            min_ending_here = nums[0];
            max_so_far = nums[0];

            for(int i = 1; i < nums.length; ++i) {
                if(nums[i] > 0) {
                    //Choose min and max between current no. or its operation with
                    //current min or max
                    max_ending_here  = Math.max( nums[i], max_ending_here * nums[i]);
                    min_ending_here  = Math.min( nums[i], min_ending_here * nums[i]);
                } else if (nums[i] == 0) {
                    //Can't help here, has to be zeroed
                    max_ending_here = 0;
                    min_ending_here = 0;

                } else {

                    int temp = min_ending_here;
                    min_ending_here = max_ending_here;
                    max_ending_here = temp;
                    //This is the same thing as above when no. is positive
                    //but the values of corr. elements have been swapped
                    max_ending_here  = Math.max( nums[i], max_ending_here * nums[i]);
                    min_ending_here  = Math.min( nums[i], min_ending_here * nums[i]);

                }
                max_so_far = Math.max(max_so_far, max_ending_here);
            }
        }
        return max_so_far;
    }
    /*
     * Now here all the helper variables are initialized to first
     * element
     */
    public static int maxProductWithZero( int[] a ) {
        int max_so_far, max_ending_here, min_ending_here;
        max_so_far = max_ending_here = min_ending_here = a[0];

        for (int i = 1; i < a.length; i++) {
            if(a[i] < 0) {
                // when a[i] < 0
                // max * a[i] will become min
                // min * a[i] will become max
                int t = max_ending_here;
                max_ending_here = min_ending_here;
                min_ending_here = t;
            }

            /*
                if a[i] = 0 case is taken care of where max and min ending here
                are made 0 as per the logic
             */
            max_ending_here = Math.max( a[i], max_ending_here * a[i] );
            min_ending_here = Math.min( a[i], min_ending_here * a[i] );
            max_so_far  = Math.max(max_so_far, max_ending_here);
        }
        return max_so_far;
    }
}
