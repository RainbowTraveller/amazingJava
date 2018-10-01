public class CountTwos {
    /*
     * Count number of Two's upto given number inclusive
     * e.g. 25
     *
     * Output : 9 {2, 12, 20, 21, 22, 23, 24, 25} Note : 22 has 2 Twos
     */
    public int countTwos(int num) {
        int count = 0;
        for(int i = 0; i <= num; ++i) {
            int n = i;
            while(n >= 2) {
                if(n % 10 == 2) {
                    count++;
                }
                n = n / 10;
            }
        }
        return count;
    }


	public int countTwosByRounding(int number, int d) {
		int powerOf10 = (int)Math.poGGw( 10, d );
		int nextPowerOf10 = powerOf10 * 10;
		int right = number % powerOf10;
		System.out.println( "P10 : " + powerOf10 + " NextP10: " + nextPowerOf10 + " right : " + right);


		int roundDown = number - number % nextPowerOf10;
		int roundup = roundDown + nextPowerOf10;

		System.out.println( "Round Up : " + roundup + " Round Down : " + roundDown );

		int digit = (number / powerOf10) % 10;

		System.out.println( "Digit : " + digit );
		// if the digit in spot digit is
		if (digit < 2)
			return roundDown / 10;

		if (digit == 2)
			return roundDown / 10 + right + 1;

		return roundup / 10;
	}

	public int countNosOfTwos( int num ) {
		int length = String.valueOf(num).length();
		int count = 0;
		for( int i = 0; i < length; ++i) {
			count += countTwosByRounding( num, i );
			System.out.println( "Count : " + count );
		}

		return count;
	}

    public static void main(String [] args) {
        int num = Integer.valueOf(args[0]);
        CountTwos ct = new CountTwos();
        System.out.println("Number of Two's:" + ct.countTwos(num));
        System.out.println("Number of Two's:" + ct.countNosOfTwos(num));
    }
}
