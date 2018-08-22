import java.util.LinkedList;
import java.util.LinkedHashSet;



/*
 * Print all the valid combinations of a pair of braces
 * take input at no. of pairs of braces
 *
 * LinkedHashSet is used as we need the last set of strings hence order needs to be maintained
 *
 */
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


	public LinkedList<LinkedHashSet<String>> paranthesisComboNR( int count ) {

		LinkedList<LinkedHashSet<String>> combos = new LinkedList<LinkedHashSet<String>>();
		for(int i = 0; i < count; ++i) {
			if( i ==0 ) {
				LinkedHashSet<String> curr = new LinkedHashSet<String>();
				curr.add( "()" );
				combos.add(curr);
			} else {
				LinkedHashSet<String> curr = new LinkedHashSet<String>();
				LinkedHashSet<String> prev = combos.getLast();
				for(String s : prev) {
					curr.add( prefix( s ) );
					curr.add( suffix( s ) );
					curr.add( surround( s  ) );
				}
				combos.add( curr );
			}
		}
		return combos;
	}

    public static void main(String[] args) {
        String input = args[0];
        BracketCombos bc = new BracketCombos();
        /*LinkedList<LinkedHashSet<String>> al = new LinkedList<LinkedHashSet<String>>();
        bc.paranthesisCombo(al,Integer.parseInt(input));
        LinkedHashSet<String> lhs = al.getLast();
        for(String s : lhs) {
            System.out.println(s);
        }*/

		//---------------------------------------------------

		System.out.println(" Non Recursive ");
        LinkedList<LinkedHashSet<String>> combosNR = bc.paranthesisComboNR( Integer.parseInt( input ) );
        LinkedHashSet<String> actual = combosNR.getLast();
        for(String s : actual) {
            System.out.println(s);
        }
    }
}
