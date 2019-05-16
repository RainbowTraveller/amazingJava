/*
 *   Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 *   Note:
 *
 *   The solution set must not contain duplicate triplets.
 *
 *   Example:
 *
 *   Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 *   A solution set is:
 *   [
 *     [-1, 0, 1],
 *     [-1, -1, 2]
 *   ]
 *   Trivial solution is O(n^3) where is just run 3 nested loops and check if addition of the 3 numbers at i,j and k indices
 *   leads to zero. In this approach we base our solution on two sum problem. We consider a number and check if we can find
 * 	 a pair of number whose addition is -ve of this number, so as the resultant sum is 0. We also have additional criteria
 * 	 that we need unique triplets. This is controlled at 3 places. Initially we check the starting number is not same as previously
 *	 considered number. Also within the sum calculation loop, we skip numbers if they are same as previous ones from both the ends.
 *	 The already sorted input array helps us in this case
 */
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class Triplet {

	public static void main(String[] args) {
		int [] input = { -1, 0, 1, 2, -1, -4 };
		System.out.println(threeSum(input));
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		//Sort
		Arrays.sort(nums);
		for(int i = 0; i < nums.length - 2; ++i) {
			//Check if the number already considered
			if(i > 0 && nums[i] == nums[i -1]) {
				continue;
			}
			//Target sum will be negative of element at i
			//then only the resultant sum will be 0
			int targetSum = -1 * nums[ i ];

			//Consider all elements till end
			int start = i + 1;
			int end = nums.length - 1;

			while(start < end ) {
				//Get current actual sum to compare with target sum
				int actualSum = nums[start] + nums[end];

				if(actualSum == targetSum) {
					List<Integer> triplet = new LinkedList<Integer>();
					triplet.add(nums[i]);
					triplet.add(nums[start]);
					triplet.add(nums[end]);
					result.add(triplet);
					start++;
					end--;
					//Skip same elements from both ends
					while(start < end && nums[start] == nums[start - 1]) {
						start++;
					}
					while(start < end && nums[end] == nums[end + 1]) {
						end--;
					}
				} else if (actualSum < targetSum) {
					//we need some more value, so get next bigger number
					start++;
				} else {
					//Too much of value, reduce by getting n next lower number
					end --;
				}
			}
		}
		return result;
	}
}
