import java.lang.reflect.Array;
import java.util.*;

public class ChaseTest {
    public static void main (String[] args) {
        ChaseTest chaseTest = new ChaseTest();
        chaseTest.extractAllPalindromes("AABACBC");
        System.out.println(chaseTest.kthSmallestSorting(new int[]{5, 2, 10, 8, 1, 9}, 4));
        System.out.println(chaseTest.kthSmallestPQ(new int[]{5, 2, 10, 8, 1, 9}, 4));
    }

    public int kthSmallestSorting (int[] input, int k) {
        Arrays.sort(input);
//        for(int num : input) {
//            System.out.println(num);
//        }
        return input[k - 1];
    }

    public int kthSmallestPQ (int[] input, int k) {
        //Sort in reverse order
        var priorityQueue = new PriorityQueue<Integer>(Comparator.reverseOrder());
        for (int num : input) {
            priorityQueue.add(num);
            //remove all bigger numbers
            while (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
//        System.out.println(priorityQueue);
        return priorityQueue.peek();
    }

    public void extractAllPalindromes (String input) { // Refer to LongestPalindromeSubstring.java for searching around center method
        char[] arr = input.toCharArray();
        for (int i = 0; i < input.length(); ++i) {
            for (int j = i + 1; j < input.length(); j++) {
                if (checkPalindrome(arr, i, j)) {
                    System.out.println(input.substring(i, j + 1));
                }
            }
        }
    }

    public boolean checkPalindrome (char[] input, int i, int j) {
        while (i < j) {
            if (input[i] != input[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
