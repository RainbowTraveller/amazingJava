public class IndexFromRotatedArray {
    /*
     * Find index of a number from a sorted and rotated array
     * e.g. [19, 21, 1, 4, 5, 7, 11]// min and max > mid
     * input : 1 index : 2
     *
     * e.g. [5, 7, 11, 19, 21, 1, 4]//min and max < mid
     * input : 1 index : 5
     *
     * **** Assume ascending sort
     */

    public static int findIndex(int[] arr, int min, int max, int input) {
        if(min > max) {
            //System.out.println("Number Not present");
            return -1;
        }

        int mid = (min + max) / 2;

        if(arr[mid] == input) {
            return mid;
        }

        //Important Step : Check mid and the beginning element
        if(arr[min] <= arr[mid]) {//start to mid is sorted
            if(input >= arr[min] && input <= arr[mid] ) {
                return findIndex(arr, min, mid - 1, input);//left
			}
            return findIndex(arr, mid + 1, max, input);//right
        }

		if(input >= arr[mid] && input <= arr[max]) {
			return findIndex(arr, mid + 1, max, input);//right
		}
		return findIndex(arr, min, mid - 1, input);//left
    }

    public static void main(String [] args) {

        //int [] leftRotation =  {5, 5, 5, 7, 11, 5};
        int [] leftRotation =  {5, 7, 11, 12, 19, 21, 1, 4};
        int [] rightRotation =  {19, 21, 1, 4, 5, 7, 11, 12};
		//For This case it is still unknown why it fails to search no. 1
        //int [] rightRotation =  {10, 9, 8,7, 6, 5, 4, 3, 1};
        int min = 0;
        int max = leftRotation.length - 1;
		System.out.println("--------------- : Left Rotated : ------------------- ");
        System.out.println("Index of 21 :" + findIndex(leftRotation, min, max, 21));
        System.out.println("Index of 1 :" + findIndex(leftRotation, min, max, 1));
        System.out.println("Index of 11 :" + findIndex(leftRotation, min, max, 11));

        min = 0;
        max = rightRotation.length - 1;
        System.out.println();
		System.out.println("--------------- : Right Rotated : ------------------- ");
        System.out.println("Index of 21 :" + findIndex(rightRotation, min, max, 21));
        System.out.println("Index of 1 :" + findIndex(rightRotation, min, max, 1));
        System.out.println("Index of 11 :" + findIndex(rightRotation, min, max, 11));
	}
}
