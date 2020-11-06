import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// N requests per M seconds 3 request per 3
// 1    2   3   4  5  6 7
// 1    2   3   0  0  3  1
//
// curr interval : 1 -> 1 , 2 -> 2
//  reqPorcessed 1 + 2 = 3
//  pending = 3
// reqRecv = [2, 4]

//

// List of times
// check first interval and current to calculte diff.
// if diff >= interval
// remove all occurrences of first elements from list
//check the size if less then requests add


//Modify it for a customer id or ip
public class RateLimit {

    public static void main(String[] args) {

        RateLimiter limiter = new RateLimiter(3, 3);
        System.out.println(limiter.rateLimit(1));
        System.out.println(limiter.timeTracker);
        System.out.println(limiter.rateLimit(1));
        System.out.println(limiter.timeTracker);
        System.out.println(limiter.rateLimit(2));
        System.out.println(limiter.timeTracker);
        System.out.println(limiter.rateLimit(4));
        System.out.println(limiter.timeTracker);
    }

    static class RateLimiter {

        public int requests; // 50 requests
        public int interval; // 3 secs
        public List<Integer> timeTracker;

        public RateLimiter(int requests, int interval) {
            this.requests = requests;
            this.interval = interval;
            this.timeTracker = new LinkedList<>();
        }

        public boolean rateLimit(int time) {
            if (timeTracker.size() > 0 && time - timeTracker.get(0) >= interval) {
                int oldestTime = timeTracker.get(0);
                List<Integer> revisedTimeTracker = new LinkedList<>();
                int index = 0;
                while (index < timeTracker.size() && timeTracker.get(index) != oldestTime) {
                    revisedTimeTracker.add(timeTracker.get(index));
                    index++;
                }
                timeTracker = revisedTimeTracker;
            }
            if (timeTracker.size() < interval) {
                timeTracker.add(time);
                return true;
            }

            return false;

        }

    }
}
