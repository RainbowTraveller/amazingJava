/*
Given an integer n, return the number of prime numbers that are strictly less than n.

Example 1:

Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
Example 2:

Input: n = 0
Output: 0
Example 3:

Input: n = 1
Output: 0
*/
public class CountPrime {

    public static void main(String[] args) {
        CountPrime countPrime = new CountPrime();
        System.out.println(countPrime.countPrimes(10));
    }

    public int countPrimes(int num) {
        //initializes to all false
        boolean [] tracker = new boolean[num];
        int count = 0;
        for(int i = 2; i < num; i++) {
            // start with 2 and consider numbers less than given input
            if(tracker[i] == false) {
                count++;// False mean it is prime no.
                for(int j = 2; i * j < num; j++) {
                    tracker[i * j] = true; // mark all the multiples as true so they won't be considered
                }
            }
        }
        return count;
    }
}
