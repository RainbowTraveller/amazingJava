import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

public class KnapSackGreedy {

	private List<Item, Float> knapSack;
	private int maxWeight;

	public static void main( String[] args ) {
		Comparator<Item> inclustionFactorComparator = new Comparator<Item>() {
			public int compare(Item  i1, Item i2) {
				if(i1.getInclusionFactor() > i2.getInclusionFactor()) {
					return 1;
				} else {
					return -1;
				}
			}
		}
	}
}

class Item {
	float weight;
	float profit;
	float inclusionFactor;


	public Item(float weight, float profit) {
		this.weight = weight;
		this.profit = profit;
		this.inclusionFactor = (float) (profit / weight;)
	}

	public int getWeight() {
		return weight;
	}

	public int getProfit() {
		return profit;
	}

	public int getInclusionFactor() {
		return inclusionFactor;
	}

	public void setWeight( float weight) {
		this.weight = weight;
	}

	public void setProfit( float profit ) {
		this.profit = profit;
	}
}

public static getProfitWeightRatio( int[] profits, int[] weights ) {
	if( profits != null && weights != null && profits.length == weights.length) {

	}
}
