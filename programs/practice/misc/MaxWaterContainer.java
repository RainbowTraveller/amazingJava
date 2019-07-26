/*  Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 *  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
 *  which together with x-axis forms a container, such that the container contains the most water.

    Note: You may not slant the container and n is at least 2.
    Input: [1,8,6,2,5,4,8,3,7]
    Output: 49
*/
public class MaxWaterContainer {
    public static void main( String[] args) {
        int [] height = { 1,8,6,2,5,4,8,3,7 };
        /*int [] height = { 1, 1};
        int [] height = { 0, 0};
        int [] height = { 1, 10, 10, 1};
        int [] height = { 1, 10, 1, 20};*/
        System.out.println("Max Area " + maxArea(height));
    }

    public static int maxArea(int[] height) {
        int w = -1;
        if(height != null) {
            //Left Max
            int lmax = height[0];
            //Right Max
            int rmax = height[ height.length - 1];

            int start = 0, end = height.length - 1;

            //Initial area
            w = Math.max ((end - start) * Math.min(lmax, rmax), w);;

            while(start < end) {
                //if left max is lesser try to find greater one
                if(lmax < rmax) {
                    if(lmax < height[start]) {
                        lmax = height[start];
                        //New greater lmax, calculate area at once
                        w = Math.max ((end - start) * Math.min(lmax, rmax), w);
                    }
                    //if current height is still less than lmax then continue
                    start++;
                } else {
                    // or try to find greater right max
                     if(rmax < height[end]) {
                        rmax = height[end];
                        //New greater rmax, calculate area at once
                        w = Math.max ((end - start) * Math.min(lmax, rmax), w);
                    }
                    //if current height is still less than rmax then continue
                    end--;
                }
            }
        }
        return w;
    }

}
