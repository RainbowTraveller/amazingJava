import java.util.Arrays;

public class QuickSort {

    public static void quickSort (int [] arr, int low, int high) {
        if(low < high) {
            int mid = partition(arr, low, high);
            System.out.println("PIVOT INDEX: " + mid);
            quickSort(arr, low, mid - 1);
            quickSort(arr, mid + 1, high);
        }
    }

    public static int partition(int [] arr, int low, int high) {
        int i = 0;
        int pivot = arr[high];
        for(int j = 0; j <= high; ++j) {
            if(arr[j] < pivot) {// Why not for <=
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                ++i;
            }
        }
        int temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;
        System.out.println(Arrays.toString(arr));
        return i;
    }

    public static void main(String [] args) {
        int [] input = {2, 4, 7, 1, 5, 8, 6, 34, 563, 1973, 12, 3, 45, 67, 91, 10};
        //int [] input = {2, 4, 7, 1, 3, 5, 8, 6};
        quickSort(input, 0, input.length - 1);
        System.out.println(Arrays.toString(input));
    }
}
