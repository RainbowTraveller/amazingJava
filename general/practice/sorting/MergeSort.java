import java.util.Arrays;

public class MergeSort {

    public static void mergeSort (int [] arr, int [] helper, int low, int high) {
        if(low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, helper, low, mid);
            mergeSort(arr, helper, mid + 1, high);
            merge(arr, helper, low, mid, high);
        }
    }

    public static void merge(int [] arr, int [] helper, int low, int mid, int high) {
        for(int i = low; i <= high; ++i) {
            helper[i] = arr[i];
        }
        int helperlow = low;
        int helpermid = mid + 1;
        int current = low;
        while(helperlow <= mid && helpermid <= high) {
            if(helper[helperlow] <= helper[helpermid]) {
                arr[current++] = helper[helperlow];
                helperlow++;
            } else {
                arr[current++] = helper[helpermid];
                helpermid++;
            }
        }
        int remaining = mid - helperlow;
        for(int i = 0; i <= remaining; ++i) {
            arr[current + i] = helper[helperlow + i];
        }
    }

    public static void main(String [] args) {
        int [] input = {2, 4,7,1,3,5,8,6,34,563,1973, 12, 3, 45, 67,91, 10};
        //int [] input = {2, 4, 7, 1, 3, 5, 8, 6};
        int [] helper = new int [input.length];
        mergeSort(input, helper, 0, input.length - 1);
        System.out.println(Arrays.toString(input));
    }
}
