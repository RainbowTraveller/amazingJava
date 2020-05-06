public class AddStringsAsNumbers {
    public static void main(String[] args) {

        System.out.println(AddStringsAsNumbers.addStrings("123", "123"));
        System.out.println(AddStringsAsNumbers.addStrings("1231282", "123"));
        System.out.println(AddStringsAsNumbers.addStrings("123","1231282"));
        System.out.println(AddStringsAsNumbers.addStrings("123","987"));
    }

    public static String addStrings(String num1, String num2) {
        String ans = null;
        if(num1 != null && num2 != null) {
            int len1 = num1.length();
            int len2 = num2.length();
            StringBuffer sbNum1 = new StringBuffer(num1);
            StringBuffer sbNum2 = new StringBuffer(num2);
            StringBuffer sbResult = new StringBuffer();
            sbNum1 = sbNum1.reverse();
            sbNum2 = sbNum2.reverse();
            int index = 0;
            int carry = 0;
            int sum = 0;
            while(index < sbNum1.length() && index < sbNum2.length()) {
                char n1 = sbNum1.charAt(index);
                char n2 = sbNum2.charAt(index);
                int d1 = n1 - '0';
                int d2 = n2 - '0';
                sum = d1 + d2 + carry;
                carry = sum / 10;
                sum = sum % 10;
                sbResult.append(sum);
                index++;
            }
            sbNum1 = index < sbNum1.length() ? sbNum1 : sbNum2;
            for(int i = index; i < sbNum1.length(); ++i) {
                sum = sbNum1.charAt(i) - '0' + carry;
                carry = sum / 10;
                sum = sum % 10;
                //System.out.println(sum);
                sbResult.append(sum);
            }
            if(carry != 0) {
                sbResult.append(carry);
            }
            ans = sbResult.reverse().toString();
        }

        return ans;
    }
}

