/*
LC :  The Number of the Smallest Unoccupied Chair
There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of chairs in this party that are numbered from 0 to infinity. When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.

For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives at that same moment, they can sit in that chair.

You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.

Return the chair number that the friend numbered targetFriend will sit on.

Example 1:

Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
Output: 1
Explanation:
- Friend 0 arrives at time 1 and sits on chair 0.
- Friend 1 arrives at time 2 and sits on chair 1.
- Friend 1 leaves at time 3 and chair 1 becomes empty.
- Friend 0 leaves at time 4 and chair 0 becomes empty.
- Friend 2 arrives at time 4 and sits on chair 0.
Since friend 1 sat on chair 1, we return 1.

Example 2:

Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
Output: 2
Explanation:
- Friend 1 arrives at time 1 and sits on chair 0.
- Friend 2 arrives at time 2 and sits on chair 1.
- Friend 0 arrives at time 3 and sits on chair 2.
- Friend 1 leaves at time 5 and chair 0 becomes empty.
- Friend 2 leaves at time 6 and chair 1 becomes empty.
- Friend 0 leaves at time 10 and chair 2 becomes empty.
Since friend 0 sat on chair 2, we return 2.

Algorithm
Sort Input array by arrival time
PQ : chairNo : chairNo (0)
PQ : endTime : Empty

if(!PQ empty)
while( currInterval.start > peekEnd )
  remove from PQ
  add corr. chair no to ChairPQ

add Chair no.
get chair from ChairPQ and assign
add element to PQ
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SmallestUnoccupiedChair {

  /*Tracks the next available chair number*/
  PriorityQueue<Integer> chairNo;
  /*Maintains active sitting intervals ( currently valid ) sorted in increasing order of leaving time*/
  PriorityQueue<int[]> duration;
  /*Original Index to chair no. map*/
  Map<Integer, Integer> tracker;
  /*Original index of the friend interval*/
  Map<int[], Integer> index;
  int chairIndex;

  public SmallestUnoccupiedChair() {
    chairNo = new PriorityQueue<>();
    duration = new PriorityQueue<>((i1, i2) -> (i1[1] - i2[1])); // Sorted on leaving time
    tracker = new HashMap<>();
    index = new HashMap<>();
    chairIndex = 0;
    chairNo.add(chairIndex);
  }

  public static void main(String[] args) {
    SmallestUnoccupiedChair sol = new SmallestUnoccupiedChair();
    //    int[][] intervals = {{1, 4}, {2, 3}, {4, 6}};
    int[][] intervals = {{3, 10}, {1, 5}, {2, 6}};
    //    System.out.println(sol.getChair(intervals, 1));
    System.out.println(sol.getChair(intervals, 0));
  }

  public int getChair(int[][] intervals, int no) {
    processIntervals(intervals);
    //    for (int i : tracker.keySet()) {
    //      System.out.println("Key : " + i + " : " + tracker.get(i));
    //    }
    return tracker.get(no);
  }

  public void processIntervals(int[][] intervals) {

    // Store the original index of the interval
    // Answer is based on this original index
    // These are the indexes of the friends
    for (int i = 0; i < intervals.length; i++) {
      int[] currInterval = intervals[i];
      //      System.out.println(currInterval[0] + " :: " + currInterval[1]);
      index.put(currInterval, i);
    }

    // Sort the orignal interval array based on arrival time
    Arrays.sort(intervals, (j1, j2) -> (j1[0] == j2[0] ? j1[1] - j2[1] : j1[0] - j2[0]));

    for (int i = 0; i < intervals.length; i++) {
      int[] currInterval = intervals[i];
      //      System.out.println(currInterval[0] + " :: " + currInterval[1]);
      if (!duration.isEmpty()) { // initially queue is empty
        while (!duration.isEmpty() && duration.peek()[1] <= currInterval[0]) {
          // Check for current arrival time are there any intervals in
          // the duration queue which have end time before or at this arrival
          // if so they are over so remove them and also add their chairs into
          // chair no queue marking them available
          int[] polled = duration.poll();
          int polledChairNo = tracker.get(index.get(polled));
          chairNo.add(polledChairNo);
        }
      }
      // Monotonically increasing chair no., in case no intervals has expired
      // meaning all chairs are occupied so we may need a new chair
      chairNo.add(++chairIndex);
      //      System.out.println("Next Chair no : " + chairNo.peek());
      // Now we have all the chairs possible to take in the queue
      // We need to pick the smallest indexed one
      tracker.put(index.get(currInterval), Integer.valueOf(chairNo.poll()));
      duration.add(currInterval);
    }
  }
}
