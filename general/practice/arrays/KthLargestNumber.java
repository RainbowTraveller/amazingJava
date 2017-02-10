import java.util.Arrays;
import java.lang.Math;

public class KthLargestNumber {

    public static int kthLargest (int [] arr, int low, int high, int index) {
        int number = 0;
        if(low <= high) {
            int pivot = partition(arr, low, high);

            /*System.out.println("PIVOT INDEX: " + pivot);
            System.out.println("HIGH : " + high);
            System.out.println("LOW : " + low);
            System.out.println("INDEX: " + index);*/
            if(pivot == index) {
                number = arr[index];
            }
            else if(pivot < index) {
                number = kthLargest(arr, pivot + 1, high, index);
            } else {
                number = kthLargest(arr, low, pivot - 1, index);
            }
        }
        return number;
    }

    public static int partition(int [] arr, int low, int high) {
        int i = low;
        int pivotIndex = (int)(low + Math.random() * (high - low + 1));
        int pivot = arr[pivotIndex];
        //Swap pivot with the last element in the given range
        int temp = arr[high];
        arr[high] = pivot;
        arr[pivotIndex] = temp;
        for(int j = low; j <= high; ++j) {
            if(arr[j] < pivot) {// Why not for <=
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                ++i;
            }
        }
        temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;
       // System.out.println(Arrays.toString(arr));
        return i;
    }

    public static void main(String [] args) {
        int index = Integer.valueOf(args[0]);
        int [] input = {2, 4, 7, 1, 5, 8, 6, 34, 563, 1973, 12, 3, 45, 67, 91, 10};
        //int [] input = {2, 4, 7, 1, 3, 5, 8, 6};
        int num = kthLargest(input, 0, input.length - 1, index - 1);
        System.out.println(index + "th Largest Number:" + num);
        System.out.println(Arrays.toString(input));
    }
}
