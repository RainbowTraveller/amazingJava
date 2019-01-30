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

	public static int maxProduct(List<Integer> numbers) {
		int maxSoFar = 1;
		int maxEndingHere = 1;
		int minEndingHere = 1;

		for( Integer current : numbers) {
			if( current > 0) {
				maxEndingHere *= current;
				minEndingHere = Math.min(minEndingHere * current, 1 );
			}
			if( current == 0) {
				maxEndingHere = 1;
				minEndingHere = 1;
			}

			if( current < 0 ) {
				int prevMin = minEndingHere;
				minEndingHere = maxEndingHere * current;
				maxEndingHere = Math.max( prevMin * current, 1);
			}
			maxSoFar = Math.max( maxSoFar, maxEndingHere);
		}

		return maxSoFar;
	}

	public static int maxProductWithZero( int[] nums ) {
		int max_ending_here = 0;
		int min_ending_here = 0;
		int max_so_far = 0;

		if(nums != null && nums.length > 0) {
			for(int i = 0; i < nums.length; ++i) {
				if(nums[i] > 0) {
					if(max_ending_here == 0) {
						max_ending_here = 1;
					}
					max_ending_here *= nums[i];
					min_ending_here  = Math.min( min_ending_here, min_ending_here * nums[i]);

				} else if (nums[i] == 0) {
					max_ending_here = 0;
					min_ending_here = 0;
				} else {
					min_ending_here = max_ending_here * nums[i];
					if( min_ending_here == 1) {
						max_ending_here = 1;
					} else {
						max_ending_here = Math.min(min_ending_here, min_ending_here * nums[i]);
					}
				}
				max_so_far = Math.max(max_so_far, max_ending_here);
			}
		}
		return max_so_far;
	}
}
