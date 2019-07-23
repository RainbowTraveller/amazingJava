/*
 *       You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount.
 *       If that amount of money cannot be made up by any combination of the coins, return -1.
 *       Example 1:
 *       Input: coins = [1, 2, 5], amount = 11
 *       Output: 3
 *       Explanation: 11 = 5 + 5 + 1
 *       Example 2:
 *       Input: coins = [2], amount = 3
 *       Output: -1
 *
 *
 *       Sample Run to Test Running Time
 *
 *       Enter the coin denominations (, separated)
 *       1,5,10,20,70
 *       Please enter the amount :
 *       2100
 *       Minimum Coins Required : 30
 *       Total Time to heavy recursion : 44
 *       Minimum Coins Required : 30
 *       Total Time to heavy top down : 0
*/
import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the coin denominations (, separated)");
        String input = sc.nextLine();
        String[] values = input.split(",");
        int [] coins = Arrays.stream(values)
                        .mapToInt(Integer::parseInt)
                        .toArray();

        //Arrays.stream(coins)
        //    .forEach(e -> System.out.println(e));
        System.out.println("Please enter the amount : ");
        int amount = sc.nextInt();
        if(amount < 1) {
            System.out.println("Invalid amount");
        } else {
            long  start = System.currentTimeMillis();
            /*
            System.out.println("Highly Recursive and exhaustive : ");
            System.out.println("Minimum Coins Required : " + getMinCoins(coins, amount, 0));
            System.out.println("Total Time to heavy recursion : " + (System.currentTimeMillis() - start ) / 1000);
            */

            System.out.println("Highly Recursive and exhaustive : ");
            System.out.println("Minimum Coins Required : " + getMinCoinsRecursion(coins, amount, 0));
            System.out.println("Total Time to heavy recursion : " + (System.currentTimeMillis() - start ) / 1000);

            /*
            System.out.println("Top Down approach : Starting with amount");
            start = System.currentTimeMillis();
            System.out.println("Minimum Coins Required : " + getMinCoins(coins, amount, new int[amount]));
            System.out.println("Total Time to top down : " + (System.currentTimeMillis() - start ) / 1000);
            */

            /*System.out.println("Top Down approach : Starting with first 1");
            start = System.currentTimeMillis();
            System.out.println("Minimum Coins Required : " + getMinCoins(coins,amount));
            System.out.println("Total Time to bottom up :: " + (System.currentTimeMillis() - start ) / 1000);*/
        }
    }


    /* The recursion goes like this. For each coin denomination we try all possible values ( remember we have supply of infinite coins ) starting from 0
     * for each value of one denomination we try all possible values from 0 of anther coin and so and so forth. Each time we consider a denomination we
     * initialize a min coin coin to MAX_VALUE. So for a particular coin we calculate min number of coins required to form amount using all possible
     * variations of subsequent coins and then choose least, and pass it to previous iteration.
     */
    public static int getMinCoins(int[] coins, int amount, int index) {
        if(amount == 0) {
            //This means no. of coins found in the previous operation
            //can be one possible candidate.
            return 0;
        } else {
            //Infinite supply of coins is governed by this
            int i = 0;
            // for this particular denomination let's find min no. of coins
            int min = Integer.MAX_VALUE;
            //Each time we take 0, 1, 2...coins of current denomination
            //reduce the amount by total value and proceed with next values
            while(index < coins.length && amount >= i * coins[index]) {
                int currMinCoins = getMinCoins( coins, amount - (i * coins[index]), index + 1);
                if( currMinCoins != -1 )
                    //Get appropriate min value
                    min = Math.min(currMinCoins + i, min);
                i++;
            }
            //Sometimes the coins will not sum to amount hence
            //return -1 for failed attempt
            return (min == Integer.MAX_VALUE ? -1 : min);
        }
    }


    /*
     * This is by far simplest approach but with depth first heavy recursion
     * We consider 1 coin of a denomination and immediately increase count by 1. Then
     * we go 1 level deeper with amount reduced by that much denomination.
     *      If we hit 0 that means we can get finite no. of coins : We return count obtained so far
     *      If we hit a negative no. which mean no solution possible through this path
     * When we reduce amount by certain denomination, we try all possible denominations and count
     * no. of coins required. We gather at each stage min. number and pass on to previous iteration.
     * Hence the final answer
     */
    //With this method signature uncomment the inline code to have memoization version
    //public static int getMinCoinsRecusion(int[] coins, int amount, int count, int[] minCoins) {
    public static int getMinCoinsRecursion(int[] coins, int amount, int count) {
        //Tracking min value at this stage
        int min = Integer.MAX_VALUE;
        if(amount == 0) {
            //Count so far is correct so return
            return count;
        }

        if(amount < 0) {
            //No solution with this path
            return -1;
        }

        //it the amount was previously encountered and we know the no. of coins for it
        //then return the value
        //if(minCoins[amount - 1] != 0) {
        //    return minCoins[amount - 1];
        //}


        //Consider all the coins
        for(int i = 0; i < coins.length; ++i) {
            //For each try deducing the amount and next possible options
            //increment the no. of coins when considered
            int curr = getMinCoinsRecursion(coins, amount - coins[i], count + 1);
            if(curr != -1) {
                //If valid value is returned then get min out of them
                min = Math.min(min, curr);
            }
        }
        //In some cases all values may lead to dead end
        //hence the original min var value is not changed
        return min == Integer.MAX_VALUE ? -1 : min;

        //0 based index so we have amount - 1 as index
        //minCoins[ amount - 1 ] = (min == Integer.MAX_VALUE ? -1 : min);
        //return minCoins[ amount - 1 ];
    }

    /*
     * This is top down approach. Here we can clearly see that the function is of the type F(S) = F( S - c ) + 1.
     * F(S) is min. number of coins required for amount S
     * F( S - c ) indicates the last step where we need only 1 coin of amount c as we have already figured out how many coins are
     * needed for amount S - c. c is one of the denomination that is available. Now we don't know which denomination is this and there can be
     * various possibilities so we check for each. So basically for each amount (S - c) we try to find minimum coins required. We keep track of
     * coins in an array and if such a amount if encountered along the way which is already present we just return it instead  recomputing
     * so that saves efforts using memoization technique
     * if Certain combination of coins is not leading to proper answer, we simply put -1
     */
    public static int getMinCoins(int [] coins, int amount, int minCoins[]) {
        //Desired amount is reached so we found one possible combination
        if(amount == 0) {
            return 0;
        }
        //Current choice of denominations is not working, so let it go
        if(amount < 0) {
            return -1;
        }
        //it the amount was previously encountered and we know the no. of coins for it
        //then return the value
        if(minCoins[amount - 1] != 0) {
            return minCoins[amount - 1];
        }

        //Looks like we need to count the value after all
        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            //Reduce the each denomination from current amount and then
            //Call with new amount and reconsider all possible denominations
            //for the new amount as we have infinite supply of coins
            int currMinCoins = getMinCoins(coins, amount - coin, minCoins);
            if(currMinCoins != -1) {
                //So we found valid number of coins for sum S - c
                //that is amount - coin value. So just add 1 to it
                //for coin value so we will get number of coins for
                //amount value
                min = Math.min( min, currMinCoins + 1 );
            }
        }
        //0 based index so we have amount - 1 as index
        minCoins[ amount - 1 ] = (min == Integer.MAX_VALUE ? -1 : min);
        return minCoins[ amount - 1 ];
    }

    public static int getMinCoins(int [] coins, int amount) {
        //adjust array length
        int max = amount + 1;

        //tracking
        int[] dp = new int[amount + 1];
        //Filling array with max amount + !
        Arrays.fill(dp, max);

        //initialise first place to 0
        dp[0] = 0;
        //System.out.println("Array : " + Arrays.toString(dp));
        //Consider all amounts from 1 to actual amount
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                //Check if coin denomination is less than or equal to amount
                //if it is greater then we can not choose this coin for our solution
                if (coins[j] <= i) {
                    //Get this difference current amount ( i ) and current coin
                    //denomination ( coin[j] )
                    //Check if we have a good value at this position meaning if this denomination of
                    //difference is present and has figured out it's coins. If so it will require 1 more coins
                    //of current denomination to reach the sum
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        System.out.println("Array : " + Arrays.toString(dp));
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
