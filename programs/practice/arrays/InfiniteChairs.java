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

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Arrays;

public class InfiniteChairs {

  PriorityQueue<Integer> chairNo;
  PriorityQueue<int[]> duration;
  Map<Integer, Integer> tracker;
  Map<int[], Integer> index;
  int chairIndex;

  public InfiniteChairs() {
    chairNo = new PriorityQueue<>();
    duration = new PriorityQueue<>((i1, i2) -> (i1[1] - i2[1]));
    tracker = new HashMap<>();
    index = new HashMap<>();
    chairIndex = 0;
    chairNo.add(chairIndex);
  }

  public static void main(String[] args) {
    InfiniteChairs sol = new InfiniteChairs();
    //    int[][] intervals = {{1, 4}, {2, 3}, {4, 6}};
    int[][] intervals = {{3, 10}, {1, 5}, {2, 6}};
    //    System.out.println(sol.getChair(intervals, 1));
    System.out.println(sol.getChair(intervals, 0));
  }

  public int getChair(int[][] intervals, int no) {
    processIntervals(intervals);
    for (int i : tracker.keySet()) {
      //      System.out.println("Key : " + i + " : " + tracker.get(i));
    }
    return tracker.get(no);
  }

  public void processIntervals(int[][] intervals) {

    for (int i = 0; i < intervals.length; i++) {
      int[] currInterval = intervals[i];
      System.out.println(currInterval[0] + " :: " + currInterval[1]);
      index.put(currInterval, i);
    }

    Arrays.sort(intervals, (j1, j2) -> (j1[0] == j2[0] ? j1[1] - j2[1] : j1[0] - j2[0]));

    for (int i = 0; i < intervals.length; i++) {
      int[] currInterval = intervals[i];
      System.out.println(currInterval[0] + " :: " + currInterval[1]);
      if (!duration.isEmpty()) {
        while (!duration.isEmpty() && duration.peek()[1] <= currInterval[0]) {
          int[] polled = duration.poll();
          int polledChairNo = tracker.get(index.get(polled));
          chairNo.add(polledChairNo);
        }
      }
      chairNo.add(++chairIndex);
      System.out.println("Next Chair no : " + chairNo.peek());
      tracker.put(index.get(currInterval), Integer.valueOf(chairNo.poll()));
      duration.add(currInterval);
    }
  }
}
