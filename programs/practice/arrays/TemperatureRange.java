public class TemperatureRange {
    public static void main (String[] args) {

        int[] tempratures = new int[]{30, 50, 60, 20, 10, 40, 60, 90};
        //1 2 3 1 1 3 7 8
        //int[] tempratures = new int[]{30, 50, 60, 20, 10, 40, 5, 60, 90, 30};
        //1 2 3 1 1 3 1 8 9 1
        int[] freq = C3AI.largestRunningValue(tempratures);
        for (int i : freq) {
            System.out.print(i + " ");
        }
    }

    public static int[] largestRunningValue (int[] tempratures) {
        //Output array
        int[] freq = null;
        if (tempratures != null || tempratures.length > 0) {
            // Get the length
            int tempraturesLength = tempratures.length;
            //initialize the output array
            freq = new int[tempraturesLength];
            // Initialize the two tracking variables
            // This tracks the highest temprature
            int maxSoFar = tempratures[0];
            // This tracks second highest after highest
            int maxUpToThis = tempratures[0];
            //Account for the first element
            freq[0] = 1;
            int index = 0;
            int cnt = 0;
            for (int i = 1; i < tempraturesLength; ++i) {
                // increased from previous one
                if (tempratures[i] > tempratures[i - 1]) {
                    // Is it highest so far ?
                    if (tempratures[i] >= maxSoFar) {
                        //Then it is greater than all so far so just update based on index
                        maxSoFar = tempratures[i];
                        freq[i] = i + 1; // it is greather than all
                    } else if (maxUpToThis < tempratures[i]) {
                        freq[i] = i - index + 1; // Get the gap from second highest
                        maxUpToThis = tempratures[i]; // update the second highest
                        index = i;
                    }
                } else {
                    freq[i] = 1;// First time this value so valid for one day only
                    if (tempratures[i - 1] == maxSoFar) {
                        //This is next dip from the highest one
                        maxUpToThis = tempratures[i];
                        index = i;
                    }
                }
            }
        }
        return freq;
    }
}
