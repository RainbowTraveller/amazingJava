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

    public static void main(String [] args) {
        int num = Integer.valueOf(args[0]);
        CountTwos ct = new CountTwos();
        System.out.println("Number of Two's:" + ct.countTwos(num));
    }
}
