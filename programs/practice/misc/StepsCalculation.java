public class StepsCalculation {
/*
 * The key intuition to solve the problem is that given a number of stairs n, if we know the number ways to get to the points
 * [n-1] and [n-2] respectively, denoted as n1 and n2 , then the total ways to get to the point [n] is n1 + n2.
 * Because from the [n-1] point, we can take one single step to reach [n]. And from the [n-2] point, we could take two steps to get there.
 * There is NO overlapping between these two solution sets, because we differ in the final step.
 *
 *
 * So the "all_steps" corresponds to the number of solutions to get to the point [n].
 * And "one_step_before" refers to the number of solutions until the point [n-1],
 * "two_steps_before" refers to the number of solution until the point [n-2].
 *
 * From the point [n-1], we take one step to reach the point [n] hence same [n - 1] number of ways to reach n
 * From the point [n-2], we take a two-steps leap to reach the point [n]hence same [n - 2] number of ways to reach n.
 *
 * So it goes without saying that the total number of solution to reach the point [n] should be [n-1] + [n-2].
 */
    public static int steps(int step) {
		if(step <=0) {
			return 0;
		}
		if(step == 1) {
			return 1;
		}
		if(step == 2) {
			return 2;
		}

		int one_step_before = 1;
		int two_step_before = 2;
		int all_steps = 0;
		for(int i = 2; i < step; ++i) {
			 all_steps = one_step_before + two_step_before;
			 one_step_before = two_step_before;
			 two_step_before = all_steps;
		}
		return all_steps;
    }

    public static void main(String[] args) {
		int i = Integer.valueOf(args[0]);
        System.out.println(steps(i));

    }

}
