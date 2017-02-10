public class BestSharePairs {

    public static void main (String[] args) {
        int[] stockPricesPerHour = {10, 20, 30 ,4 ,80, 10 ,2, 10, 60, 10 ,40, 70, 50};
        int least = 0;
        int max = 0;
        for(int a : stockPricesPerHour) {
            if(a < least) {
                least = a;
            }
            if(a > max) {
                max = a;
            }
        }

    }

}
