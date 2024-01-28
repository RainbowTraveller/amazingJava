/* You are given a 0-indexed array nums consisting of positive integers.

There are two types of operations that you can apply on the array any number of times:

Choose two elements with equal values and delete them from the array.
Choose three elements with equal values and delete them from the array.
Return the minimum number of operations required to make the array empty, or -1 if it is not possible.



Example 1:

Input: nums = [2,3,3,2,2,4,2,3,4]
Output: 4
Explanation: We can apply the following operations to make the array empty:
- Apply the first operation on the elements at indices 0 and 3. The resulting array is nums = [3,3,2,4,2,3,4].
- Apply the first operation on the elements at indices 2 and 4. The resulting array is nums = [3,3,4,3,4].
- Apply the second operation on the elements at indices 0, 1, and 3. The resulting array is nums = [4,4].
- Apply the first operation on the elements at indices 0 and 1. The resulting array is nums = [].
It can be shown that we cannot make the array empty in less than 4 operations.
Example 2:

Input: nums = [2,1,2,2,3,3]
Output: -1
Explanation: It is impossible to empty the array.
*/

import java.util.HashMap;

public class MinArrayEmpty {
  /*
      * count = 3
          3 - 3 = 0
          operations required = 1
      * count = 6
          6 - 3 - 3  = 0
          operations required = 2
      * count = 9
          9 - 3 - 3 - 3  = 0
          operations required = 3
      * count = 12
          12 - 3 - 3 - 3 - 3  = 0
          operations required = 4
      * count = 3
          3 - 3 = 0
          operations required = 1
      * count = 6
          6 - 3 - 3  = 0
          operations required = 2
      * count = 9
          9 - 3 - 3 - 3  = 0
          operations required = 3
      * count = 12
          12 - 3 - 3 - 3 - 3  = 0
          operations required = 4
      * count = 4
          4 - 2 - 2 = 0 -> eliminate two pairs
          operations required = 2
      * count = 7
          7 - 2 - 2 = 3 -> eliminate two pairs
          3 - 3 = 0 -> eliminate remaining triplets
          operations required = 3
      * count = 10
          10 - 2 - 2 = 6 -> eliminate two pairs
          6 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 4
      * count = 13
          13 - 2 - 2 = 9 -> eliminate two pairs
          9 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 5
      * count = 5
          5 - 2 = 3 -> eliminate one pair
          3 - 3 = 0 -> eliminate remaining triplets
          operations required = 2
      * count = 8
          8 - 2 = 6 -> eliminate one pair
          6 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 3
      * count = 11
          11 - 2 = 9 -> eliminate one pair
          9 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 4
      * count = 14
          14 - 2 = 12 -> eliminate one pair
          12 - 3 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 5
      * count = 3
          3 - 3 = 0
          operations required = 1
      * count = 6
          6 - 3 - 3  = 0
          operations required = 2
      * count = 9
          9 - 3 - 3 - 3  = 0
          operations required = 3
      * count = 12
          12 - 3 - 3 - 3 - 3  = 0
          operations required = 4
      * count = 4
          4 - 2 - 2 = 0 -> eliminate two pairs
          operations required = 2
      * count = 7
          7 - 2 - 2 = 3 -> eliminate two pairs
          3 - 3 = 0 -> eliminate remaining triplets
          operations required = 3
      * count = 10
          10 - 2 - 2 = 6 -> eliminate two pairs
          6 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 4
      * count = 13
          13 - 2 - 2 = 9 -> eliminate two pairs
          9 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 5
      * count = 5
          5 - 2 = 3 -> eliminate one pair
          3 - 3 = 0 -> eliminate remaining triplets
          operations required = 2
      * count = 8
          8 - 2 = 6 -> eliminate one pair
          6 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 3
      * count = 11
          11 - 2 = 9 -> eliminate one pair
          9 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 4
      * count = 14
          14 - 2 = 12 -> eliminate one pair
          12 - 3 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 5
      * count = 3
          3 - 3 = 0
          operations required = 1
      * count = 6
          6 - 3 - 3  = 0
          operations required = 2
      * count = 9
          9 - 3 - 3 - 3  = 0
          operations required = 3
      * count = 12
          12 - 3 - 3 - 3 - 3  = 0
          operations required = 4
      * count = 3
          3 - 3 = 0
          operations required = 1
      * count = 6
          6 - 3 - 3  = 0
          operations required = 2
      * count = 9
          9 - 3 - 3 - 3  = 0
          operations required = 3
      * count = 12
          12 - 3 - 3 - 3 - 3  = 0
          operations required = 4
      * count = 4
          4 - 2 - 2 = 0 -> eliminate two pairs
          operations required = 2
      * count = 7
          7 - 2 - 2 = 3 -> eliminate two pairs
          3 - 3 = 0 -> eliminate remaining triplets
          operations required = 3
      * count = 10
          10 - 2 - 2 = 6 -> eliminate two pairs
          6 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 4
      * count = 13
          13 - 2 - 2 = 9 -> eliminate two pairs
          9 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 5
      * count = 5
          5 - 2 = 3 -> eliminate one pair
          3 - 3 = 0 -> eliminate remaining triplets
          operations required = 2
      * count = 8
          8 - 2 = 6 -> eliminate one pair
          6 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 3
      * count = 11
          11 - 2 = 9 -> eliminate one pair
          9 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 4
      * count = 14
          14 - 2 = 12 -> eliminate one pair
          12 - 3 - 3 - 3 - 3 = 0 -> eliminate remaining triplets
          operations required = 5
      * count = 3
          3 - 3 = 0
          operations required = 1

     Formula : ceil( num / 3 ) : make sure the devision is double to that the ceil is calculated properly. In case
     of int division, increment result by 1 if not using ceil
  */
  public static void main(String[] args) {
    MinArrayEmpty minArrayEmpty = new MinArrayEmpty();
    System.out.println(
        "Min operations to make array empty : "
            + minArrayEmpty.minOperations(new int[] {2, 3, 3, 2, 2, 4, 2, 3, 4}));
    System.out.println(
        "Min operations to make array empty : "
            + minArrayEmpty.minOperations(new int[] {2, 1, 2, 2, 3, 3}));
    System.out.println(
        "Min operations to make array empty : "
            + minArrayEmpty.minOperations(
                new int[] {
                  14, 12, 14, 14, 12, 14, 14, 12, 12, 12, 12, 14, 14, 12, 14, 14, 14, 12, 12
                }));
  }

  public int minOperations(int[] nums) {
    var counter = new HashMap<Integer, Integer>();
    for (int num : nums) {
      counter.put(num, counter.getOrDefault(num, 0) + 1);
    }
    int ops = 0;

    for (int count : counter.values()) {
      if (count == 1) {
        return -1;
      }
      ops += Math.ceil((double) count / 3);
    }
    return ops;
  }
}
