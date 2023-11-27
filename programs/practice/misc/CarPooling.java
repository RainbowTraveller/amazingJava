/*
*   You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)
*
*   Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.
*
*   Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
*
*
*
*   Example 1:
*
*   Input: trips = [[2,1,5],[3,3,7]], capacity = 4
*   Output: false
*   Example 2:
*
*   Input: trips = [[2,1,5],[3,3,7]], capacity = 5
*   Output: true
*   Example 3:
*
*   Input: trips = [[2,1,5],[3,5,7]], capacity = 3
*   Output: true
*   Example 4:
*
*   Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
*   Output: true
*/

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Map;

public class CarPooling {

    public static void main(String[] args) {
        int[][] trips = new int[][] { { 2, 1, 5 }, { 3, 3, 7 } };
        int capacity = 4;
        System.out.println("Is trip possible : " + carPooling(trips, capacity));
        System.out.println("Is trip possible Simple : " + carPoolingSimple(trips, capacity));
        trips = new int[][] {{2,1,5},{3,3,7}};
        capacity = 5;
        System.out.println("Is trip possible : " + carPooling(trips, capacity));
        System.out.println("Is trip possible Simple : " + carPoolingSimple(trips, capacity));
        trips = new int[][] {{2,1,5},{3,3,7}};
        capacity = 3;
        System.out.println("Is trip possible : " + carPooling(trips, capacity));
        System.out.println("Is trip possible Simple : " + carPoolingSimple(trips, capacity));
        trips = new int[][] {{2,1,5},{3,3,7}};
        capacity = 11;
        System.out.println("Is trip possible : " + carPooling(trips, capacity));
        System.out.println("Is trip possible Simple : " + carPoolingSimple(trips, capacity));
    }


    public static boolean carPoolingSimple(int[][] trips, int capacity) {
        //Create a map : using tree map to sort values by key
        Map<Integer, Integer> passengerTracker = new TreeMap<>();
        //Consider each trip
        for(int[] trip : trips) {

            //For starting time of the trip how much capacity is consumed
            int startPassengers = passengerTracker.getOrDefault(trip[1], 0) + trip[0];
            // Track agianst the start time of the trip
            passengerTracker.put(trip[1], startPassengers);

            //Similarly for end time of a trip store a negative value of capacity, indicating that is
            //restored
            int endPassengers = passengerTracker.getOrDefault(trip[2], 0) - trip[0];
            //Track this resored capacity as well against the end time in the map
            passengerTracker.put(trip[2], endPassengers);

        }

        // Now we take a look at the capacity demand at each time
        // This is irrespective of start of end time
        // This works because, we have sotred the values with sign indicating whether the capacity is
        // being added or demanded.
        // The overlapping start times will demand capacity and it one end time restores back capacity next starttime
        // can benefit from it as we have sorted the values in ascneding order based on ts encountered
        // We need to check at any point of time the cumulative capacity should not exceed the given total capacity constraint
        for(int key : passengerTracker.keySet()) {
            System.out.println("TS : " + key + " Capacity: " + passengerTracker.get(key));
        }
        int runnningCapacity = 0;
        for(int currCapacity : passengerTracker.values()) {
            runnningCapacity += currCapacity;
            if (runnningCapacity > capacity) {
                return false;
            }
        }
        return true;
    }
    public static boolean carPooling(int[][] trips, int capacity) {

        // List of trips
        List<Trip> tripList = new LinkedList<Trip>();

        // Populating the list of the trips converted to the class
        for (int i = 0; i < trips.length; ++i) {
            Trip t = new Trip(trips[i][0], trips[i][1], trips[i][2]);
            tripList.add(t);
        }

        // Sorting the trips based on starting locations, if same on ending locations
        Collections.sort(tripList, (a, b) -> (a.s == b.s ? a.e - b.e : a.s - b.s));

        // Priority Queue : first ending place gets more priority hence before others
        PriorityQueue<Trip> pt = new PriorityQueue<Trip>((a, b) -> (a.e - b.e));

        // Add only first trip to Priority Queue
        pt.add(tripList.get(0));

        int index = 1;
        // Keep track of current capacity value
        // this is the decisive factor
        // if it goes beyond specified capacity or below zero then trips are not
        // possible
        int currCap = capacity - tripList.get(0).p;

        while (index < tripList.size()) {
            Trip curr = tripList.get(index);
            // current trip is overlapping the one with nearest end
            // so check or calculate capacity
            if (curr.s < pt.peek().e) {
                if (curr.p > currCap) {
                    return false;
                }
                // if within the range update the value
                currCap = currCap - curr.p;
            } else {
                // Here current trip starts after the nearest ending one.
                // There can be more such trip which are ending before the current one
                // get them all out and restore the count of capacity

                int cc = 0;// tracking current count of capacity that is restored
                while (!pt.isEmpty() && curr.s >= pt.peek().e) {
                    cc += pt.peek().p;
                    pt.poll();
                }

                // Restore the capacity and reduce by the passenger count of
                // current one
                currCap = currCap + cc - curr.p;

                // check if overflow or underflow
                if (currCap > capacity || currCap < 0) {
                    return false;
                }
            }
            // after all capacity checks add current trip to priority queue
            pt.add(curr);
            index++;
        }
        return true;
    }

    /*
     * Class to capture trip details
     */
    static class Trip {
        int p;
        int s;
        int e;

        public Trip() {
            p = 0;
            s = 0;
            e = 0;
        }

        public Trip(int p, int s, int e) {
            this.p = p;
            this.s = s;
            this.e = e;
        }

        public String toString() {
            return " P : " + p + " S : " + s + " E : " + e;
        }

    }
}
