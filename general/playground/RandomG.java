import java.util.Random;

public class RandomG {

    public static void main(String args[]) {
        Random r = new Random();
        for (int idx = 1; idx <= 10; ++idx){
            int randomInt = r.nextInt(2);
            System.out.println("Generated " + randomInt);
        }
    }
}
