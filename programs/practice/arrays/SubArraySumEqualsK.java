/**
 *  Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 */

public class SubArraySumEqualsK {
    public static void main(String[] args) {
        int[] input = { 1, 1, 1};
        int sum = 2;
        System.out.println("No. of sub arrays adding to sum : " + sum + " : " + getNumofArrays(input, sum));
    }

    public static int getNumofArrays( int[] nums, int k) {
        if( nums == null && nums.length == 0) {
            return 0;
        }

        int sum = 0;
        int arrs = 0;

        for(int i = 0; i < nums.length; ++i) {
            sum = 0;

            for(int j = i; j < nums.length; ++j) {
                sum += nums[j];
                if(sum == k) {
                    arrs++;
                }
            }
        }
        return arrs;
    }


/**
 *
The idea behind this approach is as follows: If the cumulative sum(represented by sum[i] for sum upto ith
index) upto two indices is the same, the sum of the elements lying in between those indices is zero. Extending the same thought further, if the cumulative sum upto two indices, say i and j is at a difference of k
i.e. if sum[i] - sum[j] = k, the sum of elements lying between indices i and j is k.

Based on these thoughts, we make use of a hashmap which is used to store the cumulative sum upto all the indices possible along with the number of times the same sum occurs. We store the data in the form:
(sum_i, no. of occurrences of sum_i). We traverse over the array nums and keep on finding the cumulative sum. Every time we encounter a new sum, we make a new entry in the hashmap corresponding to that sum.
If the same sum occurs again, we increment the count corresponding to that sum in the hashmap. Further, for every sum encountered, we also determine the number of times the sum sum-ksum−k has occured already,
since it will determine the number of times a subarray with sum kk has occured upto the current index. We increment the count by the same amount.

After the complete array has been traversed, the count gives the required result.
*/
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
		//(sum_i, no. of occurences of sum_i)
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
