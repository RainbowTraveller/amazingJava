/*
 *
 * You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.

 * Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip. Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.
 * 
 * You are also given an integer totalTrips, which denotes the number of trips all buses should make in total. Return the minimum time required for all buses to complete at least totalTrips trips.
 * 
 *  
 * 
 * Example 1:
 *
 * Input: time = [1,2,3], totalTrips = 5
 * Output: 3
 * 
 * Explanation:
 *
 * - At time t = 1, the number of trips completed by each bus are [1,0,0]. 
 *   The total number of trips completed is 1 + 0 + 0 = 1.
 * - At time t = 2, the number of trips completed by each bus are [2,1,0]. 
 *   The total number of trips completed is 2 + 1 + 0 = 3.
 * - At time t = 3, the number of trips completed by each bus are [3,1,1]. 
 *   The total number of trips completed is 3 + 1 + 1 = 5.
 * So the minimum time needed for all buses to complete at least 5 trips is 3.
 *
 * Example 2:
 * 
 * Input: time = [2], totalTrips = 1
 * Output: 2
 *
 * Explanation:
 * There is only one bus, and it will complete its first trip at t = 2.
 * So the minimum time needed to complete 1 trip is 2.
 *
 */
public class MinimumTimeToCompleteTheTrip {

    /**
     * Leveraging the binary search
     * The minimum time to consider is 1 time unit
     * The maximum time is (maximum Time taken to complete 1 trip * totalTrips)
     * These are two extremes. So now here are 2 things
     *
     * if trips are completed in time t units then it can be done in t + 1
     * if trips can not be completed in time t + 1 the it can not be in t
     *
     *  x  x x x o o o o
     *
     *  So we need to find the flipping point the min time in which the trips can be completed
     *  So we search between 1 time unit to max time unit and as time is monotonically increasing it is in sorted order
     *  hence it makes sense to use Binary search
     */
  public long minimumTime(int[] time, int totalTrips) {
        long minInterval = 1; // min time interval is 1 unit time
        if(time != null && time.length != 0) {
            //Find the max time taken for one trip
            int max = Integer.MIN_VALUE;
            for(int perTrip : time) {
                if(max < perTrip) {
                    max = perTrip;
                }
            }
            long maxInterval = (long)max * totalTrips; // This is max time taken by a bus which takes max amount of time to complete a trip
            // The actual time lies between 1 unit time and maxInterval unit time.
            while(minInterval < maxInterval) {
                long mid = (minInterval + maxInterval) / 2;
                if(isTimeEnough(mid, time, totalTrips)) {
                    maxInterval = mid; // Actual trips with mid are more so move left to reduce the time
                } else {
                    minInterval = mid + 1;// We need more no. of trips to move to right and increase unit time.
                }
            }
        }
        return minInterval;
    }

    /**
     * Here we take a time unit and how many trips are completed by each bus in that time
     * interval
     *
     * Then we add all the trip numbers and check against desired no.
     * We return true if no. is >= desired one.
     */
    public boolean isTimeEnough(long mid, int[] time, int totalTrips) {
        long actualTrips = 0;
        for(int timePerTrip : time) {
            actualTrips += (mid / timePerTrip);
        }
        return actualTrips >= totalTrips;
    }   
}

