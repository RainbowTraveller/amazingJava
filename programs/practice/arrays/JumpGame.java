/*
Program is step by step explanation of how to approach a problem from brute force to optimized solution
JumpGame is good program to study and understand the method

You are given an integer array nums. You are initially positioned at the array's first index, and each
element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

 */
public class JumpGame {
  enum State {
    UNKNOWN,
    GOOD,
    BAD
  }

  State[] tracker;

  public static void main(String[] args) {
    JumpGame jumpGame = new JumpGame();
    System.out.println(jumpGame.canJump(new int[] {2, 3, 1, 1, 4}));
    System.out.println(jumpGame.canJump(new int[] {3, 2, 1, 0, 4}));

    System.out.println(jumpGame.canJumpNR(new int[] {2, 3, 1, 1, 4}));
    System.out.println(jumpGame.canJumpNR(new int[] {3, 2, 1, 0, 4}));

    System.out.println(jumpGame.canJumpLinear(new int[] {2, 3, 1, 1, 4}));
    System.out.println(jumpGame.canJumpLinear(new int[] {3, 2, 1, 0, 4}));
  }

  /**
   * Main function which calls the brute force or memoised function
   *
   * @param nums input array of integers
   * @return returns true if last index is reachable from first index false otherwise
   */
  public boolean canJump(int[] nums) {
    //    return canJumpBrute(0, nums);
    tracker = new State[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      tracker[i] = State.UNKNOWN;
    }
    tracker[nums.length - 1] = State.GOOD;
    return canJumpMemo(0, nums);
  }

  /**
   * Brute force solution for a position i --> a b c ( say ) are possible reachable positions so
   * start with a --> p q r p --> s so for each of the position calculate the reachable and so and
   * so forth check if any of these reachable position is a final one Running complexity : O(2^n)
   *
   * @param position current index in the array to be considered
   * @param nums given input array of integers
   * @return returns true if position leads to the final end index position false otherwise
   */
  public boolean canJumpBrute(int position, int[] nums) {
    if (position == nums.length - 1) {
      return true;
    }
    // For each position, consider the farthest jump location
    // now for every position between curr to farthest
    int farthest = Math.min(position + nums[position], nums.length - 1);
    // Already considered position so start with position + 1
    for (int i = position + 1; i <= farthest; i++) {
      if (canJumpBrute(i, nums)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Introducing memoisation to avoid recomputing of some of the position operations Running
   * complexity : O(n^2) : once for all positions possibilities are calculated then we need not
   * explore them again so for each position subsequently just visit all other positions
   *
   * @param position
   * @param nums
   * @return
   */
  public boolean canJumpMemo(int position, int[] nums) {
    if (tracker[position] != State.UNKNOWN) {
      return tracker[position] == State.GOOD ? true : false;
    }
    int farthest = Math.min(position + nums[position], nums.length - 1);
    for (int i = position + 1; i <= farthest; i++) {
      if (canJumpBrute(i, nums)) {
        tracker[position] = State.GOOD;
        return true;
      }
    }
    tracker[position] = State.BAD;
    return false;
  }

  /**
   * As we saw the running time for above method is n^2 we can remove recursion and make it a loop
   *
   * @param nums
   * @return
   */
  public boolean canJumpNR(int[] nums) {

    enum Status {
      UNKNOWN,
      GOOD,
      BAD
    };
    Status[] tracker = new Status[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      tracker[i] = Status.UNKNOWN;
    }
    tracker[nums.length - 1] = Status.GOOD; // destination point so it has to be GOOD

    for (int i = nums.length - 2; i >= 0; i--) { // Start from last but one index
      int farthest = Math.min(i + nums[i], nums.length - 1);
      for (int j = i + 1; j <= farthest; j++) {
        // j indicates all the reachable indexes from the i
        // so it j is good, which can be reached from i then i is GOOD too !
        if (tracker[j] == Status.GOOD) {
          tracker[i] = Status.GOOD;
          break; // Don't check any more, there is a way to reach destination from i thru j
        }
      }
    }
    return tracker[0] == Status.GOOD;
  }

  /**
   * Now as we understand, we can scan the array from the end as that's the destination now we start
   * from a position before the last index and check if the last position is rechable from there if
   * yes then change destination to this current index and check if it is rechable from the previous
   * one so and so forth till we reach the start i.e. index 0
   *
   * @param nums
   * @return
   */
  public boolean canJumpLinear(int[] nums) {
    int lastPosition = nums.length - 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (i + nums[i] >= lastPosition) {
        lastPosition = i; // change the destination
      }
    }
    return lastPosition == 0;
  }
}
