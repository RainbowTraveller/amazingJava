public class StepsCalculation {

    public static int steps(int step) {

        if(step < 0) {
            System.out.println(0);
            return 0;
        }
        if(step == 0) {
            System.out.println(1);
            return 1;
        }

        System.out.println("Step no:" + step);
        System.out.println("Step nos:" + (step -1) + " " +  (step - 2) + " " +  (step -3));
        return steps(step - 1) + steps(step - 2) + steps(step - 3);
    }
    public static void main(String[] args) {
        System.out.println(steps(3));

    }

}
