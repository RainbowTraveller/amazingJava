public class Addition {
    public int recursiveBitwiseAddition(int a , int b) {
        if(b == 0) {
            return a;
        }
        int sum = a ^ b;
        int carry =  (a & b) << 1;
        return recursiveBitwiseAddition(sum, carry);
    }

    public int bitwiseAddition(int a, int b) {
        int mask = 1;
        for(int i = 0; i < b; ++i) {
            while((a & mask) != 0) {
                a &= (~mask);
                System.out.println("a : inside : " + a);
                mask = mask << 1;
                System.out.println("====> Mask : inside : " + mask);
            }
            a |= mask;
            System.out.println("a : outside : " + a);
            mask = 1;
            System.out.println("******** >> Mask : outside : " + mask);
        }
        return a;
    }

    public static void main(String [] args) {
        Addition add = new Addition();
        System.out.println(add.recursiveBitwiseAddition(4,7));
        System.out.println(add.bitwiseAddition(4,7));
    }
}
