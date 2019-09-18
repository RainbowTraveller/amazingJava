/*
 * There is a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Find the minimum cost to paint all houses.
 *
 */

public class PaintHouse {
	public int minCost(int[][] costs) {
		if(costs == null) {
			return 0;
		}
		int prevR = 0 , prevG = 0, prevB = 0;
		int rCost = 0 , gCost = 0, bCost = 0;
		int noh = costs.length;
		for(int i = 0; i < noh; ++i) {
			rCost = costs[i][0] + Math.min(prevG, prevB);
			gCost = costs[i][1] + Math.min(prevR, prevB);
			bCost = costs[i][2] + Math.min(prevR, prevG);
			prevR = rCost;
			prevB = bCost;
			prevG = gCost;
		}
		return Math.min(rCost, Math.min(gCost, bCost));
	}

	public static void main(String [] args) {
		PaintHouse ph = new PaintHouse();
		int[][] rateMatrix = {
								{1, 2, 3},
								{5, 6, 9},
								{3, 7, 2},
								{9, 1, 6},
							};
		 System.out.println(ph.minCost(rateMatrix));
	}
}
