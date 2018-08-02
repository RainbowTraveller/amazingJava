public class Solution {
    public int rob(int[] nums) {
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
   public int rob(int[] nums) {
        int e = 0;
        int o = 0;
        for(int j = 0; j < nums.length; ++j) {
            if(j % 2 == 0) {
                e = Math.max(e + nums[j], o);
                if(e + nums[j] > o) {
                    System.out.println(j);
                }
            } else {
                o = Math.max(o + nums[j], e);
                if(o + nums[j] > e) {
                    System.out.println(j);
                }
            }
         }
        return Math.max(e, o);
    }
}
