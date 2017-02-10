public class BinarySearch {
    public static int binarySearchR(int[] arr, int min, int max, int num) {
        int range = max - min;
        if(range < 0) {
            System.out.println("Limits Reversed");
            return -1;
        } else if (range == 0 && arr[min] != num) {
            System.out.println("Number not found");
            return -1;
        }
        if(arr[min] > arr[max]) {
            System.out.println("Not sorted");
            return -1;
        }
        int mid = range / 2 + min;
        if(arr[mid] == num) {
            return mid;
        } else if (arr[mid] < num) {
            return binarySearch(arr, mid + 1, max, num);
        } else if (arr[mid] > num) {
            return binarySearch(arr, mid + 1, max, num);
        }
    return -1;
    }


    public static int binarySearchNR(int[] arr, int min, int max, int num) {
        int range = max - min;
        while(range > 0) {
            int mid = range / 2 + min;
            if(arr[mid] == num) {
                return mid;
            } else if (arr[mid] < num) {
                min = mid + 1;
            } else if (arr[mid] > num) {
                max = mid - 1;
            }
            range = max - min;

            if(range < 0) {
                System.out.println("Limits Reversed");
                return -1;
            } else if (range == 0 && arr[min] != num) {
                System.out.println("Number not found");
                return -1;
            }
        }

        return -1;
    }

    public static int binarySearch(int arr[], int min, int max, int num) {
            if(min > max || min < 0 || max > arr.length) {
                System.out.println("Number not found");
                return -1;
            }
            int mid = (min + max) /2 ;
            if(num == arr[mid]) {
                return mid;
            } else if (num > arr[mid]) {
                return binarySearch(arr, mid + 1, max, num);
            } else {
                return binarySearch(arr, min, mid - 1, num);
            }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        //System.out.println(binarySearchR(arr, 0, (arr.length - 1), 4));
        //System.out.println(binarySearchR(arr, 0, (arr.length - 1), 11));
        //System.out.println(binarySearchNR(arr, 0, (arr.length - 1), 4));
        //System.out.println(binarySearchNR(arr, 0, (arr.length - 1), 11));
        System.out.println(binarySearch(arr, 0, (arr.length - 1), 5));
        System.out.println(binarySearch(arr, 0, (arr.length - 1), 9));
        System.out.println(binarySearch(arr, 0, (arr.length - 1), 13));
        System.out.println(binarySearch(arr, 0, (arr.length - 1), 23));
        System.out.println(binarySearch(arr, 0, (arr.length - 1), -1));
        System.out.println(binarySearch(arr, 0, (arr.length - 1), 420));
    }
}
