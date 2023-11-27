/*
Given a array of non negative numbers, find it there exist a consecutive subarray of at least 2 numbers
whose sum is equal to a given number

There can be zeros in the array
*/
public class CheckConsecutiveSumToNumber {
  public static boolean checkConsecutiveSumToNumber(int [] numbers, int k) {
      int start = 0;
      // initialize number with the first one
      int sum = numbers[start];
      //Maintain 2 indexes start and current
      int curr = start + 1;
      while(curr < numbers.length) {
          sum += numbers[curr];
          if(sum == k) {
                //Make sure there are alease 2 numbers
                if(curr - start >= 1) {
                  return true;
                }
          } else if (sum > k) {
              //Reduce the number at start
              sum -= numbers[start];
              start++;
              //This is important to check
              //We have already added the curr number
              // and start one.
              // This may add up to desired one so needs attention
              if(sum == k) {
                if(curr - start >= 1) {
                  return true;
                }
              }
          }
          //If sum is still less continue
          curr++;
      }
      return false;
  }

  /**

    */
  public static boolean checkConsecutiveSumToNumberNaive(int [] numbers, int k) {
    int length = numbers.length;
    //Scan the array
    for(int i = 0; i < length; ++i) {
      //Ignore the number greater than the target
      if(numbers[i] <= k) {
        // Sum starts with first number less than the target
        int sum = numbers[i];
        for(int j = i + 1; j < length; ++j) {
          //For outer number at i scan all numbers at index j
          sum+= numbers[j];
          if(sum == k) {
            //considerinf the fact that we have i and j ensures that
            // we have atleast 2 elements
            return true;
          } else if( sum > k) { // non negative or 0 hence guarateed to increase monotonically hence break
            break;
          }
        }
        // so attempt to find a consecutive subarray starting at index i failed
        // due to above break so consider next number at index i
        continue;
      }
    }
    return false;
  }

  public static void main (String[] args) {

    //int [] numbers = {23, 2, 4, 6, 7}; // Valid solution : true
    //int [] numbers = {23, 9, 8, 6, 7}; // false : single number
    //int [] numbers = {2, 23, 4, 6, 7}; // Non Consecutive
    //int [] numbers = {23, 2, 0, 6, 7};
    int [] numbers = {1, 1, 1, 1, 3};

    System.out.println(CheckConsecutiveSumToNumber.checkConsecutiveSumToNumberNaive(numbers,6)); // O(n^2)
    System.out.println(CheckConsecutiveSumToNumber.checkConsecutiveSumToNumber(numbers,6)); // O(n)

  }
}
