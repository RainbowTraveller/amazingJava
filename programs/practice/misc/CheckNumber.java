class CheckNumber {
	public static void main ( String[] args ) {
		System.out.println( CheckNumber.isNumber(""));
		System.out.println( CheckNumber.isNumber("e"));
		System.out.println( CheckNumber.isNumber("."));
		System.out.println( CheckNumber.isNumber(" "));
		System.out.println( CheckNumber.isNumber("123"));
		System.out.println( CheckNumber.isNumber("123e134"));
		System.out.println( CheckNumber.isNumber("123e134e"));
		System.out.println( CheckNumber.isNumber("e123e134"));
		System.out.println( CheckNumber.isNumber("123.3532"));
		System.out.println( CheckNumber.isNumber("123.3532."));
		System.out.println( CheckNumber.isNumber(".123.3532"));
		System.out.println( CheckNumber.isNumber(" 123.3532 "));
		System.out.println( CheckNumber.isNumber(" "));
		System.out.println( CheckNumber.isNumber("0.1"));
		System.out.println( CheckNumber.isNumber(".1"));
		System.out.println( CheckNumber.isNumber(".01"));
		System.out.println( CheckNumber.isNumber(".e1"));
	}


	public static boolean isNumber(String s) {
        if( s != null && !s.isEmpty() ) {
            s = s.trim();
            if( s.isEmpty() ) {
                return false;
            }
            char [] elements = s.toCharArray();
            int count = 0;
            boolean eFound = false;
            boolean dotFound = false;
			boolean isNumberFound = false;

            for( char c : elements ) {
                if ( c == 'e' ) {
                    if ( !isNumberFound || eFound || ( count == 0 || count == s.length() - 1 ) ) {
                        return false;
                    }
                    eFound = true;
                } else if ( c == '.' ) {
                   if ( eFound || dotFound ) {
                        return false;
                    }
                    if( count == 0 && s.length() == 1 ) {
                        return false;
                    }
                    dotFound = true;
                } else if ( c == ' ' || ( c - '0' > 9 || c - '0' < 0 ) ) {
                    return false;
                }

				if( (c - '0' >= 0 && c - '0' <=9 ) && !isNumberFound ) {
					isNumberFound = true;
				}
                count++;
            }
            return true;
        }
        return false;
    }
}
