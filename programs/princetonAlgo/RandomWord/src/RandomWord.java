import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String survivor = null;
        double count = 0;
        while(!StdIn.isEmpty()) { //Tried with while(StdIn.hasNextLine()) as well in combination with StdIn.readLine()
                count++;
                String input = StdIn.readString();
                if(StdRandom.bernoulli(1/count)) {
                    survivor = input;
                }
            }
        //Was not able to stop program even when the input was not given
        System.out.println(survivor);
    }
}