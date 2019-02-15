public class CheckRotated {
    /*
     *  Checks if one string is rotation of other
     *  e.g. waterbottle is rotation of erbottlewat
     *  assume isSubString method  available
     *
     */

    public static void main(String[] args) {
        String o = args[0];
        String r = args[1];
        System.out.println(isRotation(o, r));

    }

    public static boolean isRotation(String o, String r) {
        int l = o.length();
        if(l == r.length() && l > 0) {
            String rr = r + r;
            return rr.indexOf(o) != -1;
            //return tt.isSubString(o);
        }
            return false;
    }
}
