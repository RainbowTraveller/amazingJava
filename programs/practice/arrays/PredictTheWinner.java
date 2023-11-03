import java.util.Arrays;

/**
 * You are given an integer array nums. Two players are playing a game with this array: player 1 and
 * player 2.
 *
 * <p>Player 1 and player 2 take turns, with player 1 starting first. Both players start the game
 * with a score of 0. At each turn, the player takes one of the numbers from either end of the array
 * (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. The player
 * adds the chosen number to their score. The game ends when there are no more elements in the
 * array.
 *
 * <p>Return true if Player 1 can win the game. If the scores of both players are equal, then player
 * 1 is still the winner, and you should also return true. You may assume that both players are
 * playing optimally.
 *
 * <p>Example 1:
 *
 * <p>Input: nums = [1,5,2] Output: false Explanation: Initially, player 1 can choose between 1 and
 * 2. If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5,
 * then player 1 will be left with 1 (or 2). So, final score of player 1 is 1 + 2 = 3, and player 2
 * is 5. Hence, player 1 will never be the winner and you need to return false. Example 2:
 *
 * <p>Input: nums = [1,5,233,7] Output: true Explanation: Player 1 first chooses 1. Then player 2
 * has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True
 * representing player1 can win.
 */
public class PredictTheWinner {
  public static void main(String[] main) {

    int[] values = new int[] {1, 5, 233, 7};
    System.out.println("Player 1 is winner :" + whoisTheWinner(values));
    System.out.println("Player 1 is winner :" + whoisTheWinnerMemoization(values));
  }

  /**
   * Caller funciton. This checks if the difference between the points of Player 1 and 2 is zero or
   * positive
   *
   * @param nums interger array of possible moves or points to be obtained
   * @return returns true if player 1 scores more or equal points than player2
   */
  public static boolean whoisTheWinner(int[] nums) {
    int len = nums.length;
    return play(nums, 0, len - 1) >= 0; // Naive
  }

  /**
   * Complexity O(2^n) At each stage there will be 2 options to explore The player plays optimally,
   * so we need to explore botth options and go till the very end of the game and then backtrack
   * That way we collect evidence at each stage which path gives us more score and propagate to
   * previous level
   *
   * <p>1. Left and right indicate the two extreme indexes from which a value can be picked up
   *
   * <p>2. So at this level 2.a : Value at left and call next with ( left + 1 and right ) which are
   * choices at next level 2.b : Value at right and call next with ( left and right - 1) which are
   * choices at next level
   *
   * <p>3. LeftValue and RightValue indicate the differece between current left / right value and
   * subsequent greater value that player 2 may select. We can choose the value which is greater of
   * the two and return
   *
   * @param nums
   * @param left
   * @param right
   * @return
   */
  public static int play(int[] nums, int left, int right) {
    if (left == right) {
      return nums[left];
    }
    int leftValue = nums[left] - play(nums, left + 1, right);
    int rightValue = nums[right] - play(nums, left, right - 1);
    //    System.out.println("LValue : " + leftValue + " RValue : " + rightValue);
    int maxVal = Math.max(leftValue, rightValue);
    //    System.out.println("Left : " + nums[left] + "  Right: " + nums[right] + " Max :" +
    // maxVal);
    return maxVal;
  }

  /**
   * Caller funciton. This checks if the difference between the points of Player 1 and 2 is zero or
   * positive
   *
   * @param nums interger array of possible moves or points to be obtained
   * @return returns true if player 1 scores more or equal points than player2
   */
  public static boolean whoisTheWinnerMemoization(int[] nums) {
    int len = nums.length;
    // track the max value for particular left and right
    int[][] memory = new int[len][len];
    for (int i = 0; i < len; ++i) {
      Arrays.fill(memory[i], -1);
    }
    return play(nums, 0, len - 1, memory) >= 0; // Naive
  }

  /**
   * The logic is same except tracking for left, right value. This avoids the recomputing and avoids
   * recomputing already present values.
   *
   * <p>Time complexity: O(n^2)
   *
   * <p>We use a cache memo to store the computed states. During the recursion, the cache makes sure
   * we don't calculate a state more than once. The number of states (left, right) is O(n^2) ).
   *
   * @param nums
   * @param left
   * @param right
   * @param memory tracks the value of left, right values in the form of double dimension integer
   *     array
   * @return
   */
  public static int play(int[] nums, int left, int right, int[][] memory) {
    // If present left, right value avoid recomputing
    if (memory[left][right] != -1) {
      return memory[left][right];
    }
    if (left == right) {
      return nums[left];
    }
    int leftValue = nums[left] - play(nums, left + 1, right, memory);
    int rightValue = nums[right] - play(nums, left, right - 1, memory);
    int maxVal = Math.max(leftValue, rightValue);
    // Store the value for left, right : do not recompute for already visited left, right
    memory[left][right] = maxVal;
    return maxVal;
  }
}
