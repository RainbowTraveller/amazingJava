import java.util.List;
import java.util.ArrayList;

public class MissingNumbers {
	public static void main(String[] args) {
		int [] input = {4,3,2,7,8,2,3,1};
		System.out.println(MissingNumbers.findDisappearedNumbers(input));
		input = new int []{4,3,4,7,8,7,3,1};
		System.out.println(MissingNumbers.findDisappearedNumbers(input));
		input = new int []{4,3,5,7,8,6,3,1};
		System.out.println(MissingNumbers.findDisappearedNumbers(input));
		input = new int []{4,3,4,7,1,2,3,1};
		System.out.println(MissingNumbers.findDisappearedNumbers(input));
	}

  	public static List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i = 0 ; i < nums.length; ++i) {
            //get each value
            int availableNo = Math.abs(nums[i]) - 1;
            //Mark value at this index as -ve don't care what that value is
            if(nums[availableNo] > 0) {
                nums[availableNo] = (-1 * nums[availableNo]);
            }
        }
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] >= 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
