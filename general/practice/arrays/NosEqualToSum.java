import java.util.Arrays;

public class NosEqualToSum {

    public static void main(String[] args) {
        int[] arr = {2, 10, 0, 25, 17, 20, 41, 23, 30, 5, 13, 39, 7};
        int sum = 30;
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length - 1;

        while(start < end) {
            if(arr[start] + arr[end] == sum) {
                System.out.println("PAIR: " + arr[start] + " " + arr[end]);
                start++;
                end--;
            } else if(arr[start] + arr[end] < sum) {
                start++;
            } else {
                end--;
            }
        }
    }
}
