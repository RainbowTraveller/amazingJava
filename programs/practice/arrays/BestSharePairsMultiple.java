  /*Say you have an array for which the ith element is the price of a given stock on day i.
  *
  *Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
  *
  *Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
  *
  *Example 1:
  *
  *Input: [7,1,5,3,6,4]
  *Output: 7
  *Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
  *             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
  *Example 2:
  *
  *Input: [1,2,3,4,5]
  *Output: 4
  *Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
  *             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
  *             engaging multiple transactions at the same time. You must sell before buying again.
  *Example 3:
  *
  *Input: [7,6,4,3,1]
  *Output: 0
  *Explanation: In this case, no transaction is done, i.e. max profit = 0.
  */

public class BestSharePairsMultiple {
    public static void main(String[] args) {

        //int[] input = {1,2,3,4,5};
        int[] input = {7,1,5,3,6,4};
        System.out.println("Max profit : " + getMaxProfitMinMax(input));
        System.out.println("Max profit : " + getMaxProfit(input));
    }

    public static int getMaxProfitMinMax(int[] input) {
        int min = input[0];
        int max = input[0];
        int maxProfit = 0;
        int i = 0;
        while(i < input.length - 1) {
            //Find first value which is greater than previous one
            while(i < input.length - 1 && input[i + 1] < input[i]) {
                i++;
            }
            //Note this as min
            min = input[i];
            //Now check get the greatest value...if next one is less than current
            //this is the max value so far
            while(i < input.length - 1 && input[i + 1] > input[i]) {
                i++;
            }
            //This is max value so far
            max = input[i];
            //Add the diff to max profit
            maxProfit +=  max - min;
        }
        return maxProfit;
    }

    public static int getMaxProfit(int[] prices) {
        //This cumulative diff addition will be same as
        //first value and last value
        //e.g. [1, 2, 3, 4, 5]
        // 1 + 1 + 1 + 1 = 4
        // also 5 - 1 = 4 which is diff between max and min value
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}

