public class BestSharePairs {

  /*
   * 1stLower : Yes : 1stHigher: as long as we get higher than this assign it
   *
   * 1stLower : Yes : 1stHigher: Yes and lower one is encountered assign to
   * 2ndLower
   *
   *
   * if now anything > 1stHigher ==> 1stLower = 2ndLower and 1stHigher =
   * currentHigher
   *
   *
   *
   */
  public static void main(String[] args) {
    int[] stockPricesPerHour = {10, 20, 30, 4, 80, 10, 2, 10, 60, 10, 40, 70, 50};
    BestSharePairs bsp = new BestSharePairs();
    System.out.println(bsp.maxProfit_poly(stockPricesPerHour));
    System.out.println(bsp.maxProfit_linear(stockPricesPerHour));
  }

  public int maxProfit_poly(int[] input) {
    int diff = Integer.MIN_VALUE;
    int min = 0;
    int max = 0;
    if (input != null) {
      for (int i = 0; i < input.length; ++i) {
        for (int j = i + 1; j < input.length; ++j) {
          if (input[j] > input[i]) {
            int currDiff = input[j] - input[i];
            if (currDiff > diff) {
              min = input[i];
              max = input[j];
              diff = currDiff;
            }
          }
        }
      }
    }
    System.out.println("Min Share Price : " + min + " Max Share Price :  " + max);
    return diff;
  }

  public int maxProfit_linear(int[] input) {
    int finalMin = 0;
    int finalMax = 0;
    int profit = 0;
    // Initialize min and max to extreme values
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    // Collect the minimum and maximum values from the input array
    // as compared to the previous values. In case of a value greater than
    // previous maximum, we calculate the profit
    for (int value : input) {
      // If the current value is less than the minimum, update the minimum
      if (value < min) {
        min = value;
      }

      // If the current value is greater than the maximum, update the maximum
      if (value > max) {
        max = value;
        // Calculate the current profit
        int currDiff = max - min;
        // If the current profit is greater than the previous profit, update it
        if (currDiff > profit) {
          profit = currDiff;
          finalMin = min;
          finalMax = max;
        }
      }
    }
    System.out.println("Min Share Price : " + finalMin + " Max Share Price :  " + finalMax);
    return profit;
  }
}
