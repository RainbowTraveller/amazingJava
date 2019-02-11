/*
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
 */
class SumOfSquareNos {
    public boolean judgeSquareSum(int c) {
        for(int a = 1; a*a < c; ++a) {
            int bsqr = c - (int) a * a;
            if( binarySearch(0, (int)(bsqr/2), bsqr) ) {
				System.out.println("Is " + c + " sum of 2 squares : " + true);
				System.out.println("No. 1 : " + a + "  No. 2 : " + (int) Math.sqrt(bsqr));
                return true;
            }
        }
		System.out.println("Is " + c + " sum of 2 squares : " + false);
        return false;
    }

    public boolean binarySearch(int start, int end, int num) {
        if(start > end) {
            return false;
        }
        int mid = start + (end - start) / 2;
        if(mid * mid > num) {
            return binarySearch(0, mid - 1, num);
        } else if (mid * mid < num) {
            return binarySearch(mid + 1, end, num);
        }

        return (mid * mid == num);
    }


	public static void main(String[] args) {
		SumOfSquareNos son = new SumOfSquareNos();
		son.judgeSquareSum(5);
		son.judgeSquareSum(65);
		son.judgeSquareSum(77);
		son.judgeSquareSum(90);
		son.judgeSquareSum(99);
		son.judgeSquareSum(34);
	}
}
