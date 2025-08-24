/*  In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 *
 *  There is at least one empty seat, and at least one person sitting.
 *
 *  Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 *  Return that maximum distance to closest person.
 *
 *  Example 1:
 *
 *  Input: [1,0,0,0,1,0,1]
 *  Output: 2
 *  Explanation:
 *  If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 *  If Alex sits in any other open seat, the closest person has distance 1.
 *  Thus, the maximum distance to the closest person is 2.
 *  Example 2:
 *
 *  Input: [1,0,0,0]
 *  Output: 3
 *  Explanation:
 *  If Alex sits in the last seat, the closest person is 3 seats away.
 *  This is the maximum distance possible, so the answer is 3.
 */

public class MaxClosestPerson {
  public static void main(String[] args) {
    int[] input = {1, 0, 0, 0, 1, 0, 1};
    System.out.println(
        "Max distance between closest people : "
            + maxDistToClosest(input)
            + " Simple : "
            + maxDistToClosestSimple(input));
    int[] input1 = {1, 0, 0, 0};
    System.out.println(
        "Max distance between closest people : "
            + maxDistToClosest(input1)
            + " Simple : "
            + maxDistToClosestSimple(input1));
    // int[] input2 = { 1, 0, 0, 0, 0 , 0 ,0 ,0 ,0 ,0, 1, 0 , 1 };
    int[] input2 = {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    System.out.println(
        "Max distance between closest people : "
            + maxDistToClosest(input2)
            + " Simple : "
            + maxDistToClosestSimple(input2));
    int[] input3 = {0, 0, 0, 1};
    System.out.println(
        "Max distance between closest people : "
            + maxDistToClosest(input3)
            + " Simple : "
            + maxDistToClosestSimple(input3));
  }

  /**
   * Brute force approach - O(n^2) Simple approach - For each 0, calculate the distance to each 1
   * and take the minimum of those distances. Then take the maximum of those minimum distances.
   *
   * @param seats array of seats
   */
  public static int maxDistToClosestBrute(int[] seats) {
    int max_distance = Integer.MIN_VALUE;
    if (seats != null && seats.length > 0) {
      for (int i = 0; i < seats.length; ++i) {
        if (seats[i] == 0) {
          int min_distance = Integer.MAX_VALUE;
          for (int j = 0; j < seats.length; ++j) {
            if (i != j && seats[i] == 1) {
              // Calculate distance to each 1 and take the minimum
              // of those distances. This is because we are interested
              // in the closest person
              min_distance = Math.min(min_distance, Math.abs(i - j));
            }
          }
          // Now take the maximum of those minimum distances
          max_distance = Math.max(max_distance, min_distance);
        }
      }
    }
    return max_distance;
  }

  /**
   * Optimal approach - O(n) Single pass through the array. Keep track of the first and last 1
   * positions. For each 1 encountered, calculate the distance from the last 1 position and update
   * the max distance if needed. At the end, also consider the distance from the first 1 to the
   * start of the array and from the last 1 to the end of the array.
   *
   * @param seats array of seats
   */
  public static int maxDistToClosest(int[] seats) {
    int max_distance = Integer.MIN_VALUE;
    if (seats != null && seats.length > 0) {
      int first = -1, last = 0;

      for (int i = 0; i < seats.length; ++i) {
        if (seats[i] == 1) {
          if (first < 0) {
            // First occurrence of 1
            first = i;
          }
          // Calculate distance from last 1 position
          max_distance = Math.max(max_distance, Math.abs(i - last));
          // Update last 1 position
          last = i;
        }
      }
      // Now here we have the first and last 1 positions so far
      if (first == last) {
        // Only one person in the array, consider distance from start and end
        max_distance = Math.max(Math.abs(first - 0), Math.abs(seats.length - first - 1));
      } else {
        // System.out.println(max_distance);
        // now consider the distance from first 1 to start of array
        max_distance = Math.max(max_distance / 2, first);
        // and from last 1 to end of array
        max_distance = Math.max(max_distance, seats.length - 1 - last);
      }
    }
    return max_distance;
  }

  /**
   * Another simple approach - O(n) Single pass through the array. Keep track of the last 1
   * position. For each 1 encountered, calculate the distance from the last 1 position and update
   * the max distance if needed. At the end, also consider the distance from the last 1 to the end
   * of the array.
   *
   * @param seats array of seats
   */
  public static int maxDistToClosestSimple(int[] seats) {
    int max_distance = Integer.MIN_VALUE;
    if (seats != null && seats.length > 0) {
      int start = -1;
      for (int i = 0; i < seats.length; ++i) {
        if (seats[i] == 1) {
          if (start < 0) {
            // First occurrence of 1
            max_distance = i;
          } else {
            // For subsequence occurrences the distance will be half the distance between
            // 2, 1s.
            max_distance = Math.max(max_distance, Math.abs(i - start) / 2);
          }
          // Record the current 1 location as starting point
          // for calculating the distance
          start = i;
          // System.out.println("Start : " + start);
        }
      }

      // At last, calculate the distance between last 1 and end of the array
      // and compare it with max distance observed so far
      max_distance = Math.max(max_distance, Math.abs(seats.length - start - 1));
      // System.out.println("Max : " + max_distance);
    }
    return max_distance;
  }
}
