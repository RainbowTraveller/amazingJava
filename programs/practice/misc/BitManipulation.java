public class BitManipulation {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        int i = Integer.parseInt(args[2]);
        int j = Integer.parseInt(args[3]);

        System.out.println("N: " + Integer.toBinaryString(N));
        System.out.println("M: " + Integer.toBinaryString(M));
        int allOnes = ~0;
        System.out.println("All Ones: " + Integer.toBinaryString(allOnes));
        int left = (allOnes << j+1);
        System.out.println("Left Mask : " + Integer.toBinaryString(left));
        int right = ((1 << i) - 1);
        System.out.println("Right Mask : " + Integer.toBinaryString(right));
        int mask = left | right;
        System.out.println("Actual mask:" + Integer.toBinaryString(mask));
        int n_cleared  = N & mask;
        System.out.println("N cleared bits from i to j both inclusive:" + Integer.toBinaryString(n_cleared));
        int m_shifted = M << i;
        System.out.println("M shifted to adjust position between i and j: " + Integer.toBinaryString(m_shifted));
        System.out.println("FINAL: " + Integer.toBinaryString(n_cleared | m_shifted));

        binaryFraction(Double.parseDouble(args[4]));
    }

        public static void binaryFraction(double num) {
            if(num >= 1 || num <=0) {
                System.out.println("ERROR INVALID RANGE");
            }
            StringBuilder binary = new StringBuilder();
            binary.append(".");
            double fraction = 0.5;
            while(num !=0) {
                if(binary.length() > 32) {
                    System.out.println("ERROR");
                    break;
                }
                if(num > fraction) {
                    binary.append("1");
                    num -= fraction;
                } else {
                    binary.append("0");
                }
                fraction /= 2;
            }
            System.out.println("NUMBER : " + binary.toString());
        }

}
