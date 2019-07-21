// Java program to illustrate
// bitwise operators
public class Operators {
    public static void main(String[] args)
    {
        //Initial values
        int a = 10;
        int b = 7;

        // bitwise and
        // 0101 & 0111=0101 = 5
        System.out.println("a : " + a + " b : " + b);
        System.out.println("a&b = " + (a & b));

        // bitwise or
        // 0101 | 0111=0111 = 7
        System.out.println("a|b = " + (a | b));

        // bitwise xor
        // 0101 ^ 0111=0010 = 2
        System.out.println("a^b = " + (a ^ b));

        // bitwise and
        // ~0101=1010
        // will give 2's complement of 1010 = -6
        System.out.println("~a = " + ~a);

        // can also be combined with
        // assignment operator to provide shorthand
        // assignment
        // a=a&b
        a &= b;
        System.out.println("a= " + a);


        System.out.println("Syntax : number shift_op number_of_places_to_shift");
        // left shift operator
        // 0000 0101<<2 =0001 0100(20)
        // similar to 5*(2^2)
        // Shifts the bits of the number to the right and fills 0 on voids left as a result.
        // The leftmost bit depends on the sign of initial number.
        // Similar effect as of dividing the number with some power of two.
        System.out.println("a<<2 = " + (a << 2));

        // right shift operator
        // 0000 0101 >> 2 =0000 0001(1)
        // similar to 5/(2^2)
        System.out.println("b>>2 = " + (b >> 2));

        // unsigned right shift operator
        // Shifts the bits of the number to the right and fills 0 on voids left as a result.
        // The leftmost bit is set to 0.
        // (>>>) is unsigned-shift; it’ll insert 0. (>>) is signed, and will extend the sign bit.
        // Unlike unsigned Right Shift, there is no “<<<" operator in Java
        System.out.println("b>>>2 = " + (b >>> 2));
    }
}
