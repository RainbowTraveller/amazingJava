import java.util.LinkedList;
import java.util.LinkedHashSet;

public class BracketCombos {

    public String prefix(String combo) {
        return "()" + combo;

    }

    public String suffix(String combo) {
        return  combo + "()";
    }

    public String surround(String combo) {
        return "(" + combo + ")";

    }

    public void paranthesisCombo(LinkedList<LinkedHashSet<String>> al, int len) {
        if(len == 1) {
            String str = "()";
            LinkedHashSet<String> pc = new LinkedHashSet<String>();
            pc.add(str);
            al.add(pc);
        } else {
            paranthesisCombo(al, len - 1);
            LinkedHashSet<String> prev = al.getLast();
            LinkedHashSet<String> curr = new LinkedHashSet<String>();
            for(String combo : prev) {
                curr.add(prefix(combo));
                curr.add(suffix(combo));
                curr.add(surround(combo));
            }
            al.add(curr);
        }
    }

    public static void main(String[] args) {
        String input = args[0];
        BracketCombos bc = new BracketCombos();
        LinkedList<LinkedHashSet<String>> al = new LinkedList<LinkedHashSet<String>>();
        bc.paranthesisCombo(al,Integer.parseInt(input));
        LinkedHashSet<String> lhs = al.getLast();
        for(String s : lhs) {
            System.out.println(s);
        }
    }
}
