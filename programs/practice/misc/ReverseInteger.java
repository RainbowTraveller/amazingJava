class ReverseInteger {
    public static void main(String[] args) {
        int num = Integer.valueOf(args[0]);
        System.out.println(reverse(num));
    }
    public static int reverse(int x) {
        int remainder = 0;
        int num = 0;
        //System.out.println(Integer.MAX_VALUE);
        //MAX : 2147483647 ::: MIN : -2147483648
        //System.out.println(Integer.MIN_VALUE);

            while( x != 0) {
                remainder = x % 10;
                //if num is > MAX / 10 i.e. 214748364  then adding one more digit will definitely lead to overflow
                //similarly,if it is equal to 214748364 and reminder is > 7 then also it will lead to overflow
                if(num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && remainder > 7)) return 0 ;
                //if num is < MIN / 10 i.e. -214748364 then adding one more digit will definitely lead to overflow
                //and same again
                if(num < Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE / 10 && remainder < -8)) return 0 ;
                num =  num * 10 + remainder;
                x = x / 10;
            }

        return num;
    }
}
