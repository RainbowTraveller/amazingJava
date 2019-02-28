public class NumWithHigestFreq {

    /*
     * To check if a number occurs more than half the times array size
     * aka : Majority Number
     *
     */
    public static int mostOccurringNum(int [] arr) {
        int ref = 0;
        int count = 0;
        if(arr != null) {
            ref = arr[0];
            count = 1;
            for(int i = 1; i < arr.length; ++i) {
                if(ref == arr[i]) {
                    count++;//Number is same increment count
                } else if(ref != arr[i]) {
                    --count;//if not same number decrement count
                    if (count == 0) {
                        ref = arr[i];// if count is 0,replace ref by current
                        count = 1;// set count to 1
                    }
                }
                //System.out.println("Count : " + count);
                //System.out.println("REF:"  + ref);
            }
        }
        return ref;
    }


    public static void main(String[] args) {

        int[] arr = {1,4,3,4,4,4,5,4,4,4,3,3};
        System.out.println(mostOccurringNum(arr));
    }
}
