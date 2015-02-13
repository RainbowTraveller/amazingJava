import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class Animation {
    /*
     * Computes combination of particles
     */
    public String[] animate(int speed, String init) {
        List<String> mappings = new LinkedList<String>();
        int length = init.length();
        char[] current = init.toCharArray();//Stores current mapping to base next positions on
        while(true) { // continue while all particles are exhausted
            char[] next = getEmptyMapping(length);// start with empty mapping string to decide next status
            String mapping = getMapping(current);
            mappings.add(mapping);

            if(mapping.indexOf('X') < 0) {//Quit if not particle present
                break;
            }

            //Process depending of travel direction
            for(int i = 0; i < length; ++i) {
                if(current[i] == 'L') {
                    insertL(next, i, speed);
                } else if (current[i] == 'R') {
                    insertR(next, i, speed, length);
                } else if(current[i] == 'O') {
                    //Accounting for particle of both types R and L
                    insertL(next, i, speed);
                    insertR(next, i, speed, length);
                }
            }
            current = next;
        }
        return mappings.toArray(new String[mappings.size()]);
    }

    /*
     * Returns empty mapping string
     */
    private char[] getEmptyMapping(int length) {
        char[] empty = new char[length];
        for(int j = 0; j < length; ++j) {
            empty[j] = '.';
        }
        return empty;
    }

    /*
     * Accounts for movement of particle with direction L
     * Insert 'O' if already occupied by 'R'
     */
    private void insertL(char[] next, int index, int speed) {
        int leftIndex = index - speed;
        if(leftIndex >= 0) {
            if(next[leftIndex] == '.') {
                next[leftIndex] = 'L';
            } else {
                next[leftIndex] = 'O';
            }
        }
    }

    /*
     * Accounts for movement of particle with direction R
     * Insert 'O' if already occupied by 'L'
     */
    private void insertR(char[] next, int index, int speed, int length) {
        int rightIndex = index + speed;
        if(rightIndex <= (length - 1)) {
            if(next[rightIndex] == '.') {
                next[rightIndex] = 'R';
            } else {
                next[rightIndex] = 'O';
            }
        }
    }
    /*
     * Return mapping string in terms of X
     *
     */
    private String getMapping(char[] status) {
        StringBuffer sb = new StringBuffer();
        for(Character c : status) {
            if(c != '.') {
                sb.append('X');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Animation anm = new Animation();
        //String [] progress = anm.animate(2, "..R....");
        //String [] progress = anm.animate(3, "RR..LRL");
        //String [] progress = anm.animate(2, "LRLR.LRLR");
        //String [] progress = anm.animate(10, "RLRLRLRLRL");
        //String [] progress = anm.animate(1, "...");
        String [] progress = anm.animate(1, "LRRL.LR.LRR.R.LRRL.");
        for(String s : progress) {
            System.out.println(s);
        }
    }
}
