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

    public static void mergeSortRegular(int [] arr, int low, int high) {
        if(low < high) {
            int mid = (low + high) / 2;
            mergeSortRegular(arr, low, mid);
            mergeSortRegular(arr, mid + 1, high);
            mergeRegular(arr, low, mid, high);
        }
    }

    /*
     * Added simpler method to merge. It keeps track of law and mid
     * and compares elements from these to places and increments appropriate
     * index when that element is copied
     *
     * lastly whichever is remaining gets copies as it is as those elements are
     * already sorted
     *
     * Then copy this temp array back to original array and continue
     */
    public static void mergeRegular(int [] arr, int low, int mid, int high) {
        int [] helper = new int[arr.length];
        int tracker = low;
        int helperMid = mid + 1;
        int helperLow = low;
        while(helperLow <= mid && helperMid <= high) {
            if(arr[helperLow] <= arr[helperMid]) {
                helper[tracker] = arr[helperLow];
                helperLow++;
            } else {
                helper[tracker] = arr[helperMid];
                helperMid++;
            }
            tracker++;
        }

        while(helperLow <= mid) {
            helper[tracker++] = arr[helperLow++];
        }

        while(helperMid <= high) {
            helper[tracker++] = arr[helperMid++];
        }

        for(int i = low; i <= high; ++i) {
           arr[i] = helper[i];
           //System.out.println(" " + arr[i]);
        }
        //System.out.println(Arrays.toString(helper));
        //System.out.println(Arrays.toString(arr));
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

        int [] inputRegular = {2,4,7,1,3,5,8,6,34,563,1973, 12, 3, 45, 67,91, 10};
        mergeSortRegular(inputRegular, 0, inputRegular.length - 1);
        System.out.println(Arrays.toString(inputRegular));
    }
}
