import java.util.Arrays;

/*  Given an integer array of size n, find the maximum of the minimum’s of every window size in the array. Note that window size varies from 1 to n.
*  Input:  arr[] = {10, 20, 30, 50, 10, 70, 30}
*  Output:         70, 30, 20, 10, 10, 10, 10
*
*  First element in output indicates maximum of minimums of all
*  windows of size 1.
*  Minimums of windows of size 1 are {10}, {20}, {30}, {50}, {10},
*  {70} and {30}.  Maximum of these minimums is 70
*
*  Second element in output indicates maximum of minimums of all
*  windows of size 2.
*  Minimums of windows of size 2 are {10}, {20}, {30}, {10}, {10},
*  and {30}.  Maximum of these minimums is 30
*
*  Third element in output indicates maximum of minimums of all
*  windows of size 3.
*  Minimums of windows of size 3 are {10}, {20}, {10}, {10} and {10}.
*  Maximum of these minimums is 20
*
*  Similarly other elements of output are computed.
*/

public class MaxOfMinWindow {
    public static void main(String[] args) {
        int [] arr = {10, 20, 30, 50, 10, 70, 30};
        int window = 1;
        System.out.println("Max from min with window : " +  window + " is : " + getMaxOfMin(window, arr));

    }

    public static int getMaxOfMin(int window, int[] arr) {
        int max = Integer.MIN_VALUE;
        if(window > 0 && arr != null && window < arr.length) {
            int size = arr.length;
            for(int i = 0; i  + window  < size; ++i) {
                //System.out.println("MAX : " + max);
                //Get min of window and track max
                max  = Math.max(max, getCurrWindowMin(i, window, arr));
            }
        }
        return  max;
    }


    public static int getCurrWindowMin(int index, int window, int[] arr) {
        //Get sub array, sort and take first element
        int[] currWindow = Arrays.copyOfRange(arr, index, index + window);
        Arrays.sort(currWindow);
        return currWindow[0];
    }
}

