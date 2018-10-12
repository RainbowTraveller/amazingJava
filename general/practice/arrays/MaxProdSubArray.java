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
}
