import java.util.LinkedList;

public class Combinations2 {

    public static void powerSet(String s) {
        int len = s.length();
        char[] arr = s.toCharArray();
        LinkedList<String> ps = new LinkedList<String>();
        powerSetHelper(arr,ps,len - 1);
        for(String str : ps) {
            System.out.println(str);
        }
    }

    public static void powerSetHelper(char[] arr, LinkedList<String> ps, int len) {
        if(len == -1) {
            ps.add(new String());
            return;
        } else {
            powerSetHelper(arr, ps, len - 1);
            String str = "";
            LinkedList<String> templist = new LinkedList<String>();
            for(String s : ps) {
                if(s.equals("")) {
                    str = str + arr[len];
                } else {
                    str = s + arr[len];
                }
                templist.add(str);
            }
            ps.addAll(templist);
        }
    }

    public static void main(String[] args) {
        String input = args[0];
        powerSet(input);
    }
}
