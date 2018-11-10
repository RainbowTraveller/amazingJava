import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class HouseRobber {
	public static void main( String[] args ) {
		Scanner scan = new Scanner(System.in);

		//Decide the number of friends
		System.out.print("Enter how many houses: ");
		int numOfHouses = Integer.parseInt(scan.nextLine());

		//Create a string array to store the names of your friends
		int cashInHouses[] = new int[numOfHouses];
		for (int i = 0; i < cashInHouses.length; i++) {
			System.out.print("Enter the cash in House" + (i+1) + " : ");
				cashInHouses[i] = Integer.parseInt(scan.nextLine());
		}

		System.out.println("Maximum cash that can be robbed : " + rob1( cashInHouses ));
		System.out.println("Maximum cash that can be robbed : " + rob2( cashInHouses ));
	}

    public static int rob1(int[] nums) {
        int [] cash = new int[nums.length];
        if(nums != null && nums.length > 0) {
            if(nums.length < 2) {
                return nums[0];
            } else {

                cash[0] = nums[0];
                cash[1] = Math.max(nums[0], nums[1]);
                for(int i = 2; i < nums.length; ++i) {
                    cash[i] = Math.max(cash[i - 2] + nums[i], cash[i - 1]);
                    if(cash[i - 2] + nums[i] > cash[i - 1]) {
                        System.out.println(i - 2 + " " + i);
                    } else {
                        System.out.println(i - 1);
                    }
                }
            }
            return cash[nums.length - 1];

        }
        return 0;
    }
   public static int rob2(int[] nums) {
	    Set<Integer> houses = new HashSet<Integer>();
        int e = 0;
        int o = 0;
        for(int j = 0; j < nums.length; ++j) {
            if(j % 2 == 0) {
                e = Math.max(e + nums[j], o);
                if(e + nums[j] > o ) {
					if( houses.contains( j - 1 ) ) {
						houses.remove( j - 1 );
					}
					houses.add( j  );
                }
            } else {
                o = Math.max(o + nums[j], e);
                if(o + nums[j] > e) {
					if( houses.contains( j - 1 ) ) {
						houses.remove( j - 1 );
					}
					houses.add( j  );
                }
            }
         }
		System.out.println("Houses to be robbed : " + houses);
        return Math.max(e, o);
    }
}
