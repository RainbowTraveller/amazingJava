import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given 2 arrays of timeslots. Find common timeslots between two which are equal to the given time
 * duration Note : a time slot can be bigger enough to accommodate mutiple ts from other interval
 * array
 *
 * <p>Return only first such possible slot with start and end time
 */
public class FreeTimeDuration {
  public static void main(String[] args) {

    FreeTimeDuration ftd = new FreeTimeDuration();
    int[][] slot1 = {{10, 50}, {60, 120}, {140, 210}};
    int[][] slot2 = {{0, 15}, {60, 70}};
    System.out.println("Duration : " + ftd.minAvailableDuration(slot1, slot2, 8));
    System.out.println("Duration : " + ftd.minAvailableDuration(slot1, slot2, 12));
  }

  /**
   * This method finds the minimum available duration between two sets of time slots. The approach
   * uses sorting and a two-pointer technique to efficiently find the overlapping time slots. Then,
   * it checks if the overlap can accommodate the required duration.
   *
   * @param slots1 2D array representing the first set of time slots.
   * @param slots2 2D array representing the second set of time slots.
   * @param duration The required duration to find within the overlapping time slots.
   * @return A list containing the start and end time of the first available slot that meets the
   *     duration requirement, or an empty list if no such slot exists.
   */
  public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {

    if (slots1 == null
        || slots2 == null
        || slots1.length == 0
        || slots2.length == 0
        || duration <= 0) return new ArrayList<Integer>();
    int firstSlot = 0, secondSlot = 0;

    // sort both lists by start time
    Arrays.sort(slots1, (int[] a, int[] b) -> a[0] - b[0]);
    Arrays.sort(slots2, (int[] a, int[] b) -> a[0] - b[0]);

    // use two pointer method until we end up exceeding the max index of either
    // array
    while (firstSlot < slots1.length && secondSlot < slots2.length) {
      // take the latest Start and the earliest end, with these we are guaranteed to
      // have the optimal interval to check
      int latestStart = Math.max(slots1[firstSlot][0], slots2[secondSlot][0]);
      int earliestEnd = Math.min(slots1[firstSlot][1], slots2[secondSlot][1]);
      // we know that if the diff between latest start and earliest end is greater
      // than duration we need to return start, start + duration
      if (earliestEnd - latestStart >= duration)
        return Arrays.asList(latestStart, latestStart + duration);
      // if interval is invalid we need to move up whichever slot has the smallest
      // start time
      if (slots1[firstSlot][0] < slots2[secondSlot][0]) {
        firstSlot++;
      } else {
        secondSlot++;
      }
    }
    // if we never returned anything we know that we don't have a valid answer, so
    // return
    return new ArrayList<Integer>();
  }
}
