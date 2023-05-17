import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        if(args.length < 1) {
            throw new IllegalArgumentException("Insufficient arguments");
        }
        int count = Integer.parseInt(args[0]);
        String input;
        int index = 0;
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while(!StdIn.isEmpty()) {
            String word = StdIn.readString();
//            System.out.println(word);
            randomizedQueue.enqueue(word);
        }

        Iterator<String> iterator = randomizedQueue.iterator();
        for(int i = 0; i < count; ++i)  {
            if(iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

    }
}
