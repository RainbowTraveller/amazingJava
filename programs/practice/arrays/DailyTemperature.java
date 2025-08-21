/**
 * PART A : Given a set of temperatures on consecutive days, determine for how many consecutive days
 * the temprature was less than or equal to the temperature of the given day. e.g. if temperatues[i]
 * >= temperatues[ i - 1] and temperatues[i - 2] then freq[i] = 3 indicating that for 3 days (
 * including the ith day the temperature was <= temperature[i])
 *
 * <p>PART B : Given an array of integers temperatures represents the daily temperatures, return an
 * array answer such that answer[i] is the number of days you have to wait after the ith day to get
 * a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0
 * instead.
 */
import java.util.Arrays;

public class DailyTemperature {
  public static void main(String[] args) {

    int[] temperatures = null;
    int[] freq = null;

    // PART A : INPUT
    //    temperatures = new int[] {30, 50, 60, 20, 10, 40, 60, 90};
    // 1 2 3 1 1 3 7 8
    temperatures = new int[] {30, 50, 60, 20, 10, 40, 5, 60, 90, 30};
    // 1 2 3 1 1 3 1 8 9 1
    // temperatures = new int[]{30, 50, 60, 20, 45, 10, 40, 60, 90};

    // PART B : INPUT
    // temperatures = new int[] {73,74,75,71,69,72,76,73}; // 1 1 4 2 1 1 0 0
    // temperatures = new int[] {30,40,50,60}; // 1 1 1 0
    //    temperatures = new int[] {30, 60, 90}; // 1 1 0

    //    freq = DailyTemperature.largestRunningValueNaive(temperatures);
    freq = DailyTemperature.largestRunningValue(temperatures);

    for (int i : freq) {
      System.out.print(i + " ");
    }
  }

  /**
   * Naive approach with complexity O(n^2)
   *
   * @param temperatures
   * @return
   */
  public static int[] largestRunningValueNaive(int[] temperatures) {
    // Output array
    int[] range = null;
    // Basic Sanity check
    if (temperatures != null || temperatures.length > 0) {
      int len = temperatures.length;
      // Allocate output array
      range = new int[len];
      // Scan backwards from end to beginning
      for (int i = len - 1; i >= 0; --i) {
        // Default value 1
        int cnt = 1;
        // Start from immediate previous index
        int j = i - 1;
        // Check all previous values and then increase count
        // for all values less than current temperature
        // obviously it will stop for first value greater thatn current temp
        // which occurred in the past
        while (j >= 0 && temperatures[i] >= temperatures[j]) {
          cnt++;
          j--;
        }
        // We got the count for days on which the temp was less than current day
        range[i] = cnt;
      }
    }
    return range;
  }

  /**
   * Method with efficient approach towards the finding range. This uses previously computed range
   * for previous days and then uses it to determine the current ones
   *
   * <p>Complexity : O( N )
   *
   * @param temperatures
   * @return
   */
  public static int[] largestRunningValue(int[] temperatures) {
    // Output array
    int[] range = null;
    // Basic Sanity check
    if (temperatures != null || temperatures.length > 0) {
      int len = temperatures.length;
      // Allocate output array
      range = new int[len];
      // Initialize output array with value 1
      Arrays.fill(range, 1);
      // Assing minimum temprature as first one
      int minTemp = temperatures[0];
      for (int currDay = 1; currDay < len - 1; ++currDay) {
        int currTemp = temperatures[currDay];
        // For temperature less than the minimum one we do not care
        // We already have marked value 1 in the output array.
        // Keep ignoring
        if (currTemp < minTemp) {
          minTemp = currTemp;
          continue;
        }
        // We found that current temperature is more than minimum one
        // Go backwards using the value at previous day temperature
        // It will have no. of days for which it was higher than previous ones
        // So check that temperature, as long as it is less than current day temperature
        // keep adding days from the range array
        int day = 1; // Start by checking immidiate previous neighbor day
        while (currDay - day >= 0 && temperatures[currDay - day] <= currTemp) {
          day += range[currDay - day]; // Previous one was less and it has range calculated
          // so it it guarrenteed that it will be applicable to current Day as well
          // But keep on checking further till the criteria is valid
        }
        // We have valid no. of days for which temp was less than current Day
        range[currDay] = day;
      }
    }
    return range;
  }

  // =================================================================================================================================================================================================================================

  /**
   * Method following similar approach where it checkes in a nested loop all subswquent days to find
   * when is the next warmer day
   *
   * @param temperatures
   * @return
   */
  public static int[] largestWaitNaive(int[] temperatures) {
    int[] range = null;
    if (temperatures != null || temperatures.length > 0) {
      int len = temperatures.length;
      range = new int[len];
      for (int i = 0; i < temperatures.length; ++i) {
        for (int j = i; j < temperatures.length; ++j) {
          // Check all the subsequent temperatures,
          // and when greater check the index differences
          if (temperatures[i] < temperatures[j]) {
            range[i] = j - i;
            break;
          }
        }
      }
    }
    return range;
  }

  /**
   * Method with efficient approach towards the finding range. This uses previously computed range
   * for next days and then uses it to determine the current ones
   *
   * <p>Complexity : O( N )
   *
   * @param temperatures
   * @return
   */
  public static int[] largestWait(int[] temperatures) {
    int[] range = null;
    if (temperatures != null || temperatures.length > 0) {
      int len = temperatures.length;
      range = new int[len];
      Arrays.fill(range, 0);
      int max = 0;
      // Scan array backwards
      for (int currDay = len - 1; currDay >= 0; currDay--) {
        int currTemp = temperatures[currDay];
        // If temperature is greater than max, just records it
        // This means the past temperature is greater so no need to modify its range
        // as there is no warmer day after this day as we are moving bacakwards
        if (currTemp >= max) {
          max = currTemp;
          continue;
        }
        // At this point we found the temperature which is lesser than previously
        // encountered. So we need to update for this the range to tell
        // after how many days we will have a warmer day.
        int day = 1;
        while (currDay + day < len && temperatures[currDay + day] <= currTemp) {
          // Capitalize on the subsuquent days who already have figured out the range
          day += range[currDay + day];
        }
        range[currDay] = day;
      }
    }
    return range;
  }
}
