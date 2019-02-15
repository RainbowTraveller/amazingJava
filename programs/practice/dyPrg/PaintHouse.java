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
