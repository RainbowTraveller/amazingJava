/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
*/

import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Arrays;

public class MeetingRooms {
    /*
     * Interval class
     */
    static class MeetingInterval {
        private int start;
        private int end;

        public MeetingInterval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public String toString() {
            return "Start : " + this.start + " End: " + this.end + "::";

        }
    }

    public static void main(String[] arr) {

        // System.out.println("Please enter the number of meetings");
        // Scanner s = new Scanner(System.in);
        // int meetings = s.nextInt();
        List<MeetingInterval> today = new LinkedList<MeetingInterval>();
        MeetingInterval m1 = new MeetingInterval(1, 10);
        today.add(m1);
        MeetingInterval m2 = new MeetingInterval(3, 19);
        today.add(m2);
        MeetingInterval m3 = new MeetingInterval(2, 7);
        today.add(m3);
        MeetingInterval m4 = new MeetingInterval(10, 20);
        today.add(m4);
        MeetingInterval m5 = new MeetingInterval(8, 12);
        today.add(m5);
        MeetingInterval m6 = new MeetingInterval(11, 30);
        today.add(m6);
        /*
         * for(int i = 0; i < meetings; ++i) {
         * System.out.println("Please enter meeting duration for meeting  : " + (i +
         * 1)); System.out.println("Start : " ); int start = s.nextInt();
         * System.out.println("End : " ); int end = s.nextInt(); MeetingInterval m = new
         * MeetingInterval(start, end); today.add(m); }
         */
        // For sorting based on start time
        Collections.sort(today, (mi1, mi2) -> (mi1.getStart() - mi2.getStart()));
        System.out.println("Sorted Meetings  " + today);

        // For priority queue based on end time
        PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(today.size());
        // Add first meeting with earliest start time
        allocator.add(today.get(0).getEnd());

        // Now consider remaining meetings based on the starting time
        // The priority queue contains end times, so first priority will be the one
        // having
        // earliest end time. If this earliest end time is still greater than current
        // meeting start time,
        // then we have to allocated a new room. If start time is greater than the first
        // priority end time
        // then this is the meeting that is ending and the same room can be used.
        for (int i = 1; i < today.size(); ++i) {
            MeetingInterval mi = today.get(i);
            System.out.println("Interval : " + mi);
            if (mi.getStart() >= allocator.peek()) {
                allocator.poll();
            }
            allocator.add(mi.getEnd());
        }
        // Finally size of the allocator gives rooms required
        System.out.println("The rooms required for today's meetings are : " + allocator.size());
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        //All starts
        int[] starts = new int[intervals.length];
        //All Ends
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; ++i) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        //Sort both starts and ends
        Arrays.sort(starts);
        Arrays.sort(ends);

        //Check from second meeting start time and first meeting end time
        //if end > start then we need a separate room
        //else consider next meeting start time
        int endPtr = 0, rooms = 1;
        for (int i = 1; i < starts.length; ++i) {
            if (ends[endPtr] > starts[i]) {
                rooms++;
            } else {
                endPtr++;
            }
        }
        return rooms;
    }

}
