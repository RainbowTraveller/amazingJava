/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Logic : Water will be stored or trapped when there is a container. For a container to be formed we need 2 walls. What about the height of the walls?
 * The water will be stored till the height of the smaller wall. So for all position between start ( 0  ) and end ( length  - 1), find out what is the leftMost
 * max height of a column and rightmost max height of the column. Take the minimum of them and also subtract the height of the current column. This will give the
 * water trapped by the current column. Add up to calculate all the water tapped by current set up
 */
public class TrapRainWater {
    public static void main(String [] args) {
        int [] num = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Water Trapped : Brute " + trap(num));
        System.out.println("Water Trapped : Dynamic  " + trapDyn(num));
    }

    /*
     * Brute force
     * for each i, traverse the array to left most point and right most point to get the left and right max
     * resp. Calculate the water trapped then.
     */

    public static int trap(int[] height) {
        int trapped = 0;
        if(height != null) {
            //Do not consider the left most and rightmost element
            //as they can not trap any water
            for(int i = 1; i < height.length - 1; ++i) {
                int left_max = 0, right_max = 0;
                int j = i;
                //Travel to left get max left height
                while(j >= 0) {
                    left_max = Math.max( left_max, height[j]);
                    j--;
                }
                int k = i;
                //Travel to right get max right height
                while(k < height.length) {
                    right_max = Math.max(right_max, height[k]);
                    k++;
                }

                //Water trapped by this column
                trapped += Math.min(left_max, right_max) - height[i];
            }
        }
        return trapped;
    }

    public static int trapDyn(int[] height) {
        int trapped = 0;
        int [] leftMax = new int[height.length];
        int [] rightMax = new int[height.length];
        int size = height.length;
        //Do not consider the left most and rightmost element
        //as they can not trap any water
        if(height != null && height.length > 0) {
            //Initialize with 0th column
            leftMax[0] = height[0];
            for(int j = 1; j < size - 1; ++j) {
                //At each point left most height is left most of previous or current height
                leftMax[j] = Math.max( leftMax[j - 1], height[j]);
            }

            //Initialize with nth column
            rightMax[size - 1] = height[ size - 1];
            //At each point right most height is rightmost of previous or current height
            for(int j = size - 2; j >= 0; j--) {
                rightMax[j] = Math.max(rightMax[ j + 1], height[j]);
            }
            //Based on the data collected in the above operations calculate the final trapped water units
            for(int i = 1; i < size - 1; ++i) {
                trapped += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
        }
        return trapped;
    }


    /**
     * if we observe in first 2 approaches we notice that at a given point
     * the water quantity is decided by the lowest of the left and right.
     * So as long as one side less less than other just keep considering that
     * to calculate water quantity at a location. When it becomes greater than
     * its counterpart then we switch
     */
    public static int trapLinear(int [] height) {
        int water = 0;
        int leftMax = 0;
        int rightMax = 0;

        int left  = 0, right = height.length - 1;

        while(left < right) {
            if(height[left] <= height[right]) {
                height[left] >= leftMax ? leftMax = height[left] : water += leftMax - height[left];
                left++;
            } else {
                height[right] >= rightMax ? rightMax = height[right] : water += rightMax - height[right];
            }
        }
        return water;
    }

}
