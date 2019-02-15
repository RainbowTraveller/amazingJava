package com.interview;

import java.util.List;
import java.util.ArrayList;

public class KEqualSumSeparation {
	/**
	*
	*/
	private boolean separationHelper(List<Integer> input, int perSetSum, int[] subsetSumTracker, boolean[] used, int index, int len, int noOfPartitions) {
		if(subsetSumTracker[index] == perSetSum) {
			if(index == noOfPartitions - 2) {
				//We already checked if the k partitions are possible if index is (k - 2) which means (k - 1)
				//are possible, so will be kth, no need to check for that explicitely
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
				if(sum <= perSetSum) {
					used[i] = true;
					subsetSumTracker[index] += currNum;
					boolean currResult = separationHelper(input, perSetSum, subsetSumTracker, used, index, i + 1, noOfPartitions);
					if(!currResult) {
						used[i] = false;
						subsetSumTracker[index] -= currNum;
					} else {
						return currResult;
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

		int sum = 0;

		for(int num : list) {
			sum += num;
		}
		if(sum % k != 0) {
			return false;
		}

		int perSetSum = sum / k;
		int[] subsetSumTracker = new int [k];
		boolean[] used =  new boolean[len];

		for (int i = 0; i < k; i++)
			subsetSumTracker[i] = 0;

		for (int i = 0; i < len; i++)
			used[i] = false;

		return separationHelper(list, perSetSum, subsetSumTracker, used, 0, len, k);
	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<Integer>();
		input.add(2);
		input.add(1);
		input.add(4);
		input.add(5);
		input.add(7);
		KEqualSumSeparation findkseparations = new KEqualSumSeparation();
		System.out.println(findkseparations.separate(input, 3));
	}
}
