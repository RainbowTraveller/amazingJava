import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        int len = arr.length;
        for(int i = 1; i < len; ++i) {
            int key = arr[i];
            int j;
            for(j = i - 1;j >= 0; --j) {
                if(arr[j] > key) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = key;
        }
    System.out.println(Arrays.toString(arr));
    }

    public static void main(String [] arg) {
        int [] arr = {2, 6, 7, 12, 1, 34, 3, 21, 4, 234, 56};
        insertionSort(arr);
    }

}
