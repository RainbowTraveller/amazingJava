import java.lang.Math;
import java.util.Arrays;

/*
 * Longest increasing subsequence ( this is not continuous, hence not substring)
 *
 *
 *
 */
public class LongestIncreasingSequence {

    public static void longestIncreasingSequnceNoDyn(int[] arr) {
        System.out.println("No Dynamic");
        int max = 1;
        if (arr != null) {
            for (int i = 0; i < arr.length; ++i) {
                int currMaxLength = 1;
                int currMaxNumber = Integer.MIN_VALUE;
                for (int j = i + 1; j < arr.length; ++j) {
                    if (arr[j] > arr[i]) {
                        if (currMaxNumber < arr[j]) {
                            // 2, 6 ,4 : here 6 and 4 are greater than 2 but 6 which is currentMax > a[j]
                            // which is 4
                            // here we found smaller no. greater than candidate which is 2 so probability of
                            // getting
                            // longer sequence is more so make it currMax but that is not adding to our
                            // increase in the length
                            // so don't increment
                            currMaxLength++;
                        }
                        currMaxNumber = arr[j];
                    }
                }
                max = Math.max(max, currMaxLength);
                System.out.println(
                        "currMaxNumber : " + currMaxNumber + "  currMaxLength : " + currMaxLength + " Max : " + max);
            }
        }
        System.out.println("Longest increasing subsequence length : " + max);
    }

    /*
     * e.g. 2 1 4 0 9 5 11 1 1 2 1 3 3 4 Final result of the function is 4
     *
     * 2 >> 4 >> 9 >> 11 is longest increasing subsequence
     *
     */
    public static void longestIncreasingSequnce(int[] arr) {
        if (arr != null) {
            int[] tracker = new int[arr.length];
            for (int i = 0; i < arr.length; ++i) {
                tracker[i] = 1;
            }

            /*
             * starting with length 2, Calculate the length of the increasing sequence till
             * index i and store at T[i] Longest increasing subsequence at index 0 is the
             * char itself hence has length 1
             *
             */
            for (int i = 1; i < arr.length; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (arr[j] < arr[i] && tracker[i] < tracker[j] + 1) {
                        tracker[i] = tracker[j] + 1;
                    }
                }
            }

            int max = 0;
            for (int i = 0; i < arr.length; ++i) {
                // System.out.print(" " + tracker[ i ]);
                if (max < tracker[i]) {
                    max = tracker[i];
                }
            }

            System.out.println("Longest increasing subsequence length : " + max);
        }
    }

    /*
     * This is continuous sequence length finder (it is substring here)
     */
    public static void longestContinuousIncreasingSequnce(int[] arr) {
        if (arr != null) {
            int curr = 1;
            int max = 1;
            for (int i = 1; i < arr.length; ++i) {
                if (arr[i - 1] < arr[i]) {
                    curr++;
                    max = Math.max(curr, max);
                } else {
                    curr = 1;
                }
            }
            System.out.println("Longest increasing subsequence length : " + max);
        }
    }

    public static int LISRecursive(int[] arr) {
        return LISRecursiveHelper(arr, Integer.MIN_VALUE, 0);
    }

    /**
     * Consider taken and not taken scenario. We consider each elements and then
     * first take it and see if it is greater than previous one and modify seq.
     * length In not taken scene we don't care if it matches criteria or not At each
     * step we have 2 choices and there are n such cases to consider to we have 2^n
     * running time
     */
    public static int LISRecursiveHelper(int[] arr, int prevMax, int index) {
        if (index == arr.length) {
            return 0;
        }

        int taken = 0;
        if (prevMax < arr[index]) {
            taken = 1 + LISRecursiveHelper(arr, arr[index], index + 1);
        }
        int notTaken = LISRecursiveHelper(arr, prevMax, index + 1);
        return Math.max(notTaken, taken);
    }

    public static int LISRecursiveMemo(int[] arr) {
        int[][] memo = new int[arr.length + 1][arr.length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return LISRecursiveMemoHelper(arr, -1, 0, memo);
    }

    public static int LISRecursiveMemoHelper(int[] arr, int prevIndex, int currIndex, int[][] memo) {
        if (currIndex == arr.length) {
            return 0;
        }
        if (memo[prevIndex + 1][currIndex] > 0) {
            return memo[prevIndex + 1][currIndex];
        }

        int taken = 0;
        if (prevIndex == -1 || arr[prevIndex] < arr[currIndex]) {
            taken = 1 + LISRecursiveMemoHelper(arr, currIndex, currIndex + 1, memo);
        }
        int notTaken = LISRecursiveMemoHelper(arr, prevIndex, currIndex + 1, memo);
        memo[prevIndex + 1][currIndex] = Math.max(taken, notTaken);
        return memo[prevIndex + 1][currIndex];
    }

    public static void main(String[] args) {
        // int [] input = {10, 11, 0 , 0, 1 ,2, 3, -1, 2 ,3, 14, 5, 6, 7};
        int[] input = { 10, 22, 9, 33, 21, 50, 41, 60 };

        longestIncreasingSequnce(input);
        longestIncreasingSequnceNoDyn(input);
        longestContinuousIncreasingSequnce(input);
        System.out.println("Recursive : " + LISRecursive(input));
        System.out.println("Recursive Memo : " + LISRecursiveMemo(input));

    }
}
