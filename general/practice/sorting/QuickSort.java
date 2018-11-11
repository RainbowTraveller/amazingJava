import java.util.Arrays;

public class QuickSort {

    public static void quickSort (int [] arr, int low, int high) {
        if(low < high) {
			//int chosenPivot =  arr[(low + high) / 2] ;
			//int index = partition(arr, low, high, chosenPivot);
			int index = partition(arr, low, high);
			System.out.println("PIVOT INDEX: " + index);
			quickSort(arr, low, index - 1);
			quickSort(arr, index, high);
        }
    }

    public static int partition(int [] arr, int low, int high) {
		int pivot = arr[high];
		int partitionIndex = low;
		for( int i = low; i < high; ++i ) {
			if( arr[i] <= pivot) {
				int temp = arr[i];
				arr[ i ] = arr[ partitionIndex ];
				arr[ partitionIndex ] = temp;
				partitionIndex++;
			}
		}
		arr[ high ] = arr[ partitionIndex ];
		arr[ partitionIndex ] = pivot;
		return partitionIndex;
	}

    public static int partition(int [] arr, int low, int high, int pivot) {
		while( low <= high ) {
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
        System.out.println(Arrays.toString(arr));
		return low;
    }

    public static void main(String [] args) {
        //int [] input = {2, 4, 7, 1, 5, 8, 6, 34, 563, 1973, 12, 3, 45, 67, 91, 10};
        //int [] input = {2, 4, 7, 1, 3, 5, 8, 6};
        int [] input = { 10, 7, 5, 1, 3, 4, 6 ,2, 9, 8 };
        quickSort(input, 0, input.length - 1);
        System.out.println(Arrays.toString(input));
    }
}
