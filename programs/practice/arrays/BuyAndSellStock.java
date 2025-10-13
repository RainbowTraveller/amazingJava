/** https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/ */
public class BuyAndSellStock {

  public static void main(String[] args) {
    int[] stocks = {7, 1, 5, 3, 6, 4};
    System.out.println(MaxStockProfit.getMaxProfit(stocks));
    stocks = new int[] {7, 6, 4, 3, 1};
    System.out.println(MaxStockProfit.getMaxProfit(stocks));
  }

  public static int getMaxProfit(int[] prices) {
    int profit = 0;
    int purchase = prices[0];
    for (int i = 1; i < prices.length; ++i) {
      if (purchase >= prices[i]) {
        purchase = prices[i];
      } else {
        profit = Math.max(profit, prices[i] - purchase);
      }
    }
    return profit;
  }
}
