/*
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *  Input:  [1,2,3,4]
 *  Output: [24,12,8,6]
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        int[] arr = { 1,2,3,4 };
		printArray(arr);
        getResult( arr );
    }

    public static void getResult( int[] nums) {
        if( nums != null ){
			int length = nums.length;
			int [] result = new int[length];
			int key = 1;
			//Go left to right,each index contains product of all its
			//previous numbers
			for(int i=0; i < length; ++i) {
				result[i] = key;
				key *= nums[i];
			}

			//last elements is perfectly calculated as in
			//it contains product of all elements previous to
			//it but not itself
			key = 1;
			//Go right to left, modify result array using elements from
			//original num array R to L
			for(int i = length - 1; i >= 0; --i) {
				result[i] *= key;
				key *= nums[i];
			}
			printArray(result);
        }
    }

	public static void printArray( int[] arr) {
		for(Integer i : arr) {
			System.out.print( i + " " );
		}
		System.out.println();
	}
}
