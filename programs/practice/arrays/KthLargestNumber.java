import java.util.Arrays;
import java.util.PriorityQueue;
import java.lang.Math;

public class KthLargestNumber {

    public static int kthLargest(int[] arr, int low, int high, int index) {
        int number = 0;
        //<= is important condition, few corner cases were not working without it
        if (low <= high) {
            // int pivot =arr[ (low + high) / 2 ];
            int pivotIndex = partition(arr, low, high);

            /*
             * System.out.println("PIVOT INDEX: " + pivot);
             * System.out.println("HIGH : " + high);
             * System.out.println("LOW : " + low);
             * System.out.println("INDEX: " + index);
             */

            // Partitioning around pivot does not necessarily sort the array
            // but if pivot is at desired index then it is Kth largest element

            // System.out.println("Pivot : " + arr[pivotIndex] );
            // System.out.println(Arrays.toString(arr));
            if (pivotIndex == index) {
                number = arr[index];
            } else if (pivotIndex < index) {
                number = kthLargest(arr, pivotIndex + 1, high, index);
            } else {
                number = kthLargest(arr, low, pivotIndex - 1, index);
            }
        }
        return number;
    }

    /*
     * Traditional more logical approach to partitioning
     * e.g. 2 4 5 6 1 3 ==> 4 5 6 3 1 2
     * after first partition with pivot index = 3
     * i.e. partitioning around pivot 3
     */
    public static int partition(int[] arr, int low, int high) {
        // Choose last element in the array as pivot
        int pivot = arr[high];
        // Index where the pivot will eventually be stored
        int partitionIndex = low;
        for (int i = low; i < high; ++i) {
            if (arr[i] >= pivot) {// This will change to <= of ascending sorting is needed
                // Pushing elements greater than pivot to one side
                int temp = arr[i];
                arr[i] = arr[partitionIndex];
                arr[partitionIndex] = temp;
                // Update partition index
                partitionIndex++;
            }
        }
        // finally adjust pivot to proper location
        arr[high] = arr[partitionIndex];
        arr[partitionIndex] = pivot;
        return partitionIndex;
    }

    public static int partition(int[] arr, int low, int high, int pivot) {
        while (low < high) {
            while (arr[low] < pivot) {
                low++;
            }
            while (arr[high] > pivot) {
                high--;
            }

            System.out.println("Low : " + low + " High : " + high);
            if (low <= high) {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        }
        return low;
    }

    /* Create a PQ and maintain the size to the K
     * then finally get first element which will be Kth largest element
     */
    public static int KthLargestPriority(int[] input, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        //For Kth smallest very small modification
        //PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int i = 0; i < input.length; ++i) {
            pq.offer(input[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        System.out.println("P Q : " + pq);
        return pq.poll();
    }

    public static void main(String[] args) {
        int index = Integer.valueOf(args[0]);
        int[] input = { 2, 4, 7, 1, 5, 8, 6, 34, 563, 1973, 12, 3, 45, 67, 91, 10 };
        // int [] input = {2, 4, 7, 1, 3, 5, 8, 6};
        System.out.println("Using PriorityQueue : " + KthLargestPriority(input, index));
        int num = kthLargest(input, 0, input.length - 1, index - 1);
        System.out.println(index + "th Largest Number:" + num);
        System.out.println(Arrays.toString(input));
    }
}
