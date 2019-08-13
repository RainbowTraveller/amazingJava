public class MinFromRotatedArray {
    public static void main(String[] args) {
        int [] a = {4,5,6,7,0,1,2};
        System.out.println("Min : " + findMin(a));
        int [] b = {6,7,0,1,2,4,5};
        System.out.println("Min : " + findMin(b));
        int [] c = {3,1,2};
        System.out.println("Min : " + findMin(c));
        int [] d = {5,4,3,2,1};
        System.out.println("Min : " + findMin(d));
        int [] e = {1,2,3,4,5};
        System.out.println("Min : " + findMin(e));
    }

    //4,5,6,7,0,1,2
    //6,7,0,1,2,4,5
    public static int findMin(int[] nums) {
        int min = Integer.MIN_VALUE;
        if(nums != null) {
            min = helper(nums, 0, nums.length - 1);
        }
        return min;
    }

    public static int helper(int [] nums, int s, int e) {
        if(s <= e) {
            if( e - s == 1) {
                return Math.min(nums[s], nums[e]);
            } else if ( e == s) {
                return nums[s];
            }

            int mid = (s + e) / 2;
            //System.out.println("Mid : " + mid + " S " + s + " E " + e);
            //Consider mid element as well, as it may be the one
            //don't ignore it with mid + 1 or mid - 1
            if(nums[mid] > nums[s]) {// middle greater than start
                if(nums[s] > nums[e]) { // start is greater than end
                    return helper(nums, mid, e);
                }
                return helper(nums, s, mid);
            } else if(nums[mid] < nums[s]) {
                if(nums[e] < nums[mid]) {
                    return helper(nums, mid, e);
                } else {
                     return helper(nums, s, mid);
                }
            }
        }
        return -1;
    }
}

