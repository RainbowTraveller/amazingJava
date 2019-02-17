import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class KEqualSumSeparation {
	/**
	*
	*/
	private boolean separationHelper(List<Integer> input, int perSetSum, int[] subsetSumTracker, boolean[] used, int index, int len, int noOfPartitions) {
		if(subsetSumTracker[index] == perSetSum) {
			if(index == noOfPartitions - 2) {
				//We already checked if the k partitions are possible if index is (k - 2) which means (k - 1)
				//are possible, so will be kth, no need to check for that explicitly
				return true;
			}
			//check for more partions
			return separationHelper(input, perSetSum, subsetSumTracker, used, index + 1, len, noOfPartitions);
		}
		//check for desired sum in the list
		for(int i = 0; i < len; ++i) {
			if(!used[i]) {
				int currNum = input.get(i);
				int sum = subsetSumTracker[index];
				if(sum < perSetSum) {
					used[i] = true;
					subsetSumTracker[index] += currNum;
                    if( separationHelper(input, perSetSum, subsetSumTracker, used, index, len, noOfPartitions) ) {
                        System.out.println("CURRNUM " + currNum);
                        return true;
                    } else {
						used[i] = false;
						subsetSumTracker[index] -= currNum;
					}
				}
			}
		}
		return false;
	}

	public boolean separate(List<Integer> list, int k) {
		if(list == null) {
			return false;
		}
		if(k == 1) {
			return true;
		}

		int len = list.size();
		if(len < k) {
			return false;
		}

        Collections.sort(list);
		int sum = 0;

		for(int num : list) {
			sum += num;
		}
		if(sum % k != 0) {
			return false;
		}

		int perSetSum = sum / k;
        //track the sum
		int[] subsetSumTracker = new int [k];
        //track used elements
		boolean[] used =  new boolean[len];

        //Initialise
		for (int i = 0; i < k; i++)
			subsetSumTracker[i] = 0;
		for (int i = 0; i < len; i++)
			used[i] = false;

		return separationHelper(list, perSetSum, subsetSumTracker, used, 0, len, k);
	}

    public boolean canPartitionKSubsets(List<Integer> list, int k) {

        if(list != null && list.size() > k ) {//size should be > than K and not null
            if(k == 1) {// always possible to divide in 1 group
                return true;
            }
            Collections.sort(list);//Sorting for reducing compute time
            int sum = 0;
            for(int num : list) {
                sum += num;
            }

            //Trying to reduce cyclomatic complexity ( independent paths in the program like return true or false every now and then )
            if(sum % k == 0) { // sum should be evenly distributed into k parts
                int targetSum = sum / k;
                int targetSize = list.size() - 1;
                if(list.get(targetSize) <= targetSum) {//if greatest element after sorting is > targetSum, division is not possible
                    while(targetSize > 0 && list.get(targetSize) == targetSum) {//reduce the work by no considering elements equal to targetSum
                        targetSize--;//reduce elements to consider
                        k--;// and of course less no of partitions accordingly
                    }
                }
                //Function to be called recursively
                //K partitions required
                //list : original list
                //targetSize : where to start to consider elements from list, greatest less than targetSum
                //targetSum  : actual sum to reach
                return search( new int[k], list, targetSize, targetSum );
            }
        }
        return false;
    }

    //Starting from greatest no. less than target Sum and going down considering other lesser
    //nos. one by one. So slots are filled by nos if it is zero and search continues to see
    //if any no. added satisfies the targetSum criteria. if not the no. if removed from slot sum
    //and next lesser nos. are tried
    public boolean search(int[] sumGroups, List<Integer> list, int targetSize, int targetSum) {
        if(targetSize < 0) {
            //This is not reached unless everything is fine and equals sums have achieved
            return true;
        }

        int currentNum = list.get(targetSize--);
        for(int i = 0; i < sumGroups.length; ++i) {
            if(sumGroups[i] + currentNum <= targetSum) {
                sumGroups[i] += currentNum;
                System.out.println("SUM  " + sumGroups[i]);
                if(search(sumGroups, list, targetSize, targetSum)) {
                    return true;
                }
                //Not working with current no. added to this slot for meet
                //target sum, so let's subtract it from the current slot
                //and check if any subsequent no. satisfies our criteria
                sumGroups[i] -= currentNum;
            }
            //Current no. was the only once remaining in this slot
            //No other matching no. was found for this no. which means
            //result is false. No need to check for other slots etc.
            if(sumGroups[i] == 0) {
                break;
            }
        }
        //Current number could not fit into any slot available
        //meaning no sum equal to target sum is possible for this no.
        //hence overall result is false
        return false;
    }

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<Integer>();
        //False
        //[2,2,2,2,3,4,5]
        //4
        //True
        //[129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22]
        //3
		input.add(129);
		input.add(17);
		input.add(74);
		input.add(57);
		input.add(1421);
		input.add(99);
		input.add(92);
		input.add(285);
		input.add(1276);
		input.add(218);
		input.add(1588);
		input.add(215);
		input.add(369);
		input.add(117);
		input.add(153);
		input.add(22);
		KEqualSumSeparation findkseparations = new KEqualSumSeparation();
		//System.out.println(findkseparations.separate(input, 3));
		System.out.println(findkseparations.canPartitionKSubsets(input, 3));
	}
}
