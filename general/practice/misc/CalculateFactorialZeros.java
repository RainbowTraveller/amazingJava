public class CalculateFactorialZeros {

    /*
     * Calculates trailing zeros in a factorial
     * 2 occurs many times in a factorial calculation, as every alternate number is even, so only
     * deciding factor is 5 which combines with 2 to produce 10 : additional zero
     */

    public static int factorsFive(int num) {
        /*
         * Calculate how many 5s if a number is multiple of 5
         * 5 -> 1
         * 10 -> 5....5*2 -> 2
         * 15 -> 5...5*2....5*3 -> 3
         * 20 -> 5...5*2....5*3...5*4->4
         * 25-> 5...5*2....5*3...5*4...5*5 ->6
         */
        int count = 0;
        while(num % 5 == 0){
            count++;
            num /= 5;
        }
        return count;
    }

    public static int factorialTrailingZeros(int num) {
        int count = 0;
        if(num >= 5) {
            for(int i = 5; i <= num; ++i){
                count += factorsFive(i);
            }
        }
        return count;
    }

    public static int factorialTrailingZerosPowersOf5(int num) {
        int count = 0;
        if(num >= 5) {
            for(int i = 5; num/i > 0; i*=5){
                count += num/i;
            }
        }
        return count;
    }

    public static double factorial(int num) {
        double fact = 1;
        for(int i = 1; i <= num; ++i){
            fact *= (double)i;
        }
        return fact;
    }

    public static void main(String [] args) {
        Integer input = Integer.parseInt(args[0]);
        System.out.println("Factorial : " +  factorial(input));
        System.out.println("Trailing Zeros: " +  factorialTrailingZeros(input));
        System.out.println("Trailing Zeros: " +  factorialTrailingZerosPowersOf5(input));
    }

}
