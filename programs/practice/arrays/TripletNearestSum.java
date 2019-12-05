import java.util.Arrays;

public class TripletNearestSum {
/*
 * 	sort
 * 	decide start and end
 * 	check for sum and calculate the diff
 * 	if it is less than original diff store the triple
 *
 */
	public static void main(String[] args) {
		/*
		int[] nums = {  -1, 2, 1, -4 };
		System.out.println("Nearest Sum to Target 1 is : " + getTripletwWithNearestSum(nums, 1));
		int[] nums = {1,1,-1,-1,3};
		System.out.println("Nearest Sum to Target -1 is : " + getTripletwWithNearestSum(nums, -1));
		int[] nums = {1,2,4,8,16,32,64,128};
		System.out.println("Nearest Sum to Target 82 is : " + getTripletwWithNearestSum(nums, 82));
		*/
		int[] nums = {0, 1, 2};
		System.out.println("Nearest Sum to Target 0 is : " + getTripletwWithNearestSum(nums, 0));
	}
	public static int getTripletwWithNearestSum (int [] nums, int target) {
		int closestSum = 0;
		if(nums != null) {
			int len = nums.length;
			if(len >= 3) {
        		int closestDiff = Integer.MAX_VALUE;
				Arrays.sort(nums);
				for(int i = 0; i < len - 2; ++i) {// only check till third last elements, no triplet after that
					int start = i + 1;// start with next element
					int end = len - 1;// end with last element in the array
					while(start < end ) {
						int sum = nums[start] + nums[end] + nums[i];
						int diff = Math.abs(target - sum);// Critical : check always absolute diff
 						if(diff == 0) { // if diff is zero then we found target sum, so return it
							return target;
						} else if( diff < closestDiff ) { //if smaller diff update desired value
							closestDiff = diff;
							closestSum = sum;
						} else if ( sum > target ) {//Critical : check sum with target and adjust the direction to go
							end--;
						} else {
							start++;
						}
					}
				}
			}
		}
		return closestSum;
	}
}
