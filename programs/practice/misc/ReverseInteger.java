class ReverseInteger {
    public static void main(String[] args) {
        int num = Integer.valueOf(args[0]);
        System.out.println(reverse(num));
    }
    public static int reverse(int x) {
        int remainder = 0;
        int num = 0;
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(Integer.MIN_VALUE);

            while( x != 0) {
                remainder = x % 10;
                x = x / 10;
                if(num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE && remainder > 7)) return 0 ;
                if(num < Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE && remainder < -8)) return 0 ;
                num =  num * 10 + remainder;
            }

        return num;
    }
}
