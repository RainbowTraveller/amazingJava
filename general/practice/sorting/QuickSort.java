import java.util.Arrays;

public class QuickSort {

    public static void quickSort (int [] arr, int low, int high) {
        if(low < high) {
			int pivot = arr[ (low + high) / 2 ];
            int index = partition(arr, low, high, pivot);
            System.out.println("PIVOT INDEX: " + index);
            quickSort(arr, low, index - 1);
            quickSort(arr, index, high);
        }
    }

    public static int partition(int [] arr, int low, int high, int pivot) {
		while( low < high ) {
			while( arr[low] < pivot ) {
				low++;
			}
			while( arr[high]  > pivot ) {
				high--;
			}
            System.out.println("Low: " + low + "  high: " + high);
			if(low <= high) {
				int temp = arr[low];
				arr[ low ] = arr[ high ];
				arr[ high ] = temp;
				low++;
				high--;
			}
		}
		return low;
    }

    public static void main(String [] args) {
        int [] input = {2, 4, 7, 1, 5, 8, 6, 34, 563, 1973, 12, 3, 45, 67, 91, 10};
        //int [] input = {2, 4, 7, 1, 3, 5, 8, 6};
        quickSort(input, 0, input.length - 1);
        System.out.println(Arrays.toString(input));
    }
}
