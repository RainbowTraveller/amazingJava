/* 	You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 *	the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will
 *	automatically contact the police if two adjacent houses were broken into on the same night.
 *
 *	Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money
 *	you can rob tonight without alerting the police.
 *
 *   Example 1:
 *
 *   Input: [1,2,3,1]
 *   Output: 4
 *   Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *                Total amount you can rob = 1 + 3 = 4.
 *   Example 2:
 *
 *   Input: [2,7,9,3,1]
 *   Output: 12
 *   Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *                Total amount you can rob = 2 + 9 + 1 = 12.
 *   It could be overwhelming thinking of all possibilities on which houses to rob.
 *   A natural way to approach this problem is to work on the simplest case first.
 *
 *   Let us denote that:
 *
 *   f(k) = Largest amount that you can rob from the first k houses.
 *   Ai = Amount of money at the ith house.
 *
 *   Let us look at the case n = 1, clearly f(1) = A1.
 *   Now, let us look at n = 2, which f(2) = max(A1, A2).
 *   For n = 3, you have basically the following two options:
 *   	Rob the third house, and add its amount to the first house's amount.
 *   	Do not rob the third house, and stick with the maximum amount of the first two houses.
 *
 *   Clearly, you would want to choose the larger of the two options at each step.
 *   Therefore, we could summarize the formula as following:
 *   f(k) = max(f(k – 2) + Ak, f(k – 1))
 *   We choose the base case as f(–1) = f(0) = 0, which will greatly simplify our code as you can see.
 **/

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HouseRobber {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    // Decide the number of friends
    System.out.print("Enter how many houses: ");
    int numOfHouses = Integer.parseInt(scan.nextLine());

    // Create a string array to store the names of your friends
    int cashInHouses[] = new int[numOfHouses];
    for (int i = 0; i < cashInHouses.length; i++) {
      System.out.print("Enter the cash in House" + (i + 1) + " : ");
      cashInHouses[i] = Integer.parseInt(scan.nextLine());
    }

    System.out.println("Maximum cash that can be robbed : " + rob1(cashInHouses));
    System.out.println("Maximum cash that can be robbed : " + rob2(cashInHouses));
  }

  /** Dynamic Programming approach Time Complexity : O(n) Space Complexity : O(n) */
  public static int rob1(int[] nums) {
    int[] cash = new int[nums.length];
    if (nums != null && nums.length > 0) {
      if (nums.length < 2) {
        return nums[0];
      } else {

        cash[0] = nums[0];
        cash[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; ++i) {
          cash[i] = Math.max(cash[i - 2] + nums[i], cash[i - 1]);
          if (cash[i - 2] + nums[i] > cash[i - 1]) {
            System.out.println(i - 2 + " " + i);
          } else {
            System.out.println(i - 1);
          }
        }
      }
      return cash[nums.length - 1];
    }
    return 0;
  }

  /** Optimized Dynamic Programming approach Time Complexity : O(n) Space Complexity : O(1) */
  public static int rob2(int[] nums) {
    Set<Integer> houses = new HashSet<Integer>();
    int e = 0;
    int o = 0;
    for (int j = 0; j < nums.length; ++j) {
      if (j % 2 == 0) {
        e = Math.max(e + nums[j], o);
        if (e + nums[j] > o) {
          if (houses.contains(j - 1)) {
            houses.remove(j - 1);
          }
          houses.add(j);
        }
      } else {
        o = Math.max(o + nums[j], e);
        if (o + nums[j] > e) {
          if (houses.contains(j - 1)) {
            houses.remove(j - 1);
          }
          houses.add(j);
        }
      }
    }
    System.out.println("Houses to be robbed : " + houses);
    return Math.max(e, o);
  }
}
