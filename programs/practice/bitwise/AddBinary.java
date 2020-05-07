public class AddBinary {
    public static void main(String[] args) {
        System.out.println(AddBinary.addBinary("1110", "10"));
    }

    public static String addBinary(String a, String b) {
        if(a != null && b != null) {
            int lenA = a.length();
            int lenB = b.length();
            if(lenA < lenB) {
                return addBinary(b, a);
            }
            int carry = 0;
            int j = lenB -1;
            StringBuilder sb = new StringBuilder();
            for(int i = lenA - 1; i >=0 ; --i) {
                if(a.charAt(i) == '1' ) {
                    carry++;
                }
                if(j >= 0 && b.charAt(j--) == '1') {
                    carry++;
                }
                //when both a and b bits are 1
                //if Carry is already 1 then now carry is 3 so sum = 1 and carry = 1 (carry /= 2)
                //if Carry is already 0 then now carry is 2 so sum = 0 and carry = 1 (carry /= 2)
                //when both a and b bits are 0
                //if Carry is already 1 then now carry is 1 so sum = 1 and carry = 0 (carry /= 2)
                //if Carry is already 0 then now carry is 0 so sum = 0 and carry = 0 (carry /= 2)
                //when either of a or b bits is 1
                //if Carry is already 1 then now carry is 2 so sum = 1 and carry = 1 (carry /= 2)
                //if Carry is already 0 then now carry is 1 so sum = 1 and carry = 0 (carry /= 2)
                if( carry % 2 == 1) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
                carry /= 2;
            }
            if (carry == 1) {
                sb.append('1');
            }
            sb.reverse();
            return sb.toString();
        }
        return null;
    }
}

