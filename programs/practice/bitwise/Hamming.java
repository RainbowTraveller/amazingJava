public class Hamming {
    // it is the no. of positions where the 2 numbers differ in bit
    /*
     * a = 3 = 0011
     * b = 4 = 0100
     * answer = 3
     */
    public int hammingDistance(int x, int y) {
        int mask = x ^ y;
        int count = 0;
        while (mask > 0) {
            count++;
            mask &= mask - 1;
        }
        return count;
    }

    public static void main(String args[]) {
        Hamming ham = new Hamming();
        System.out.println(ham.hammingDistance(3, 4));
    }
}
