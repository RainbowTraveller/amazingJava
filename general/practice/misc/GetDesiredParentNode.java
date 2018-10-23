/**
 * Identify nodes with 0 parents and 1 parent
 * Given an array of parent-child pairs
 *
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GetDesiredParentNode {
	public static void main(String[] args) {
		int[][] parentChildPairs = new int[][] {
			{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7},
			{4, 5}, {4, 8}, {8, 9}
		};
		//Go over the input
		//Look at each child
		//and track the parents that gives us number
		Map<Integer, List<Integer>> tracking = GetDesiredParentNode.getChildParentMapping( parentChildPairs );
		for(int key : tracking.keySet()) {
			List<Integer> nodes = tracking.get(key);
			System.out.println(key + " : ");
			System.out.println(nodes);
		}
	}

	/**
	 *  index 0 : a parent node : create a key and empty arraylist of its parents if key is not already present
	 *  index 1 : it is a child : create a key and get arraylist if present or create one if not and add index 0 i.e. its
	 *  parent in this list
	 */
	public static Map<Integer, List<Integer>> getChildParentMapping( int [][] relations ) {
		Map<Integer, List<Integer>> tracking = new HashMap<Integer, List<Integer>>();

		if( relations != null ) {
			for(int i = 0; i < relations.length; i++) {
				for( int j = 0; j < relations[0].length; ++j) {
					//System.out.println(relations[i][j]);
					List<Integer> parents = null;
					if( j == 1) {
						if( tracking.containsKey(relations[i][j]) ) {
							parents = tracking.get( relations[i][j]);
						} else {
							parents = new ArrayList<Integer>();
						}
						parents.add( relations[i][0]);
						tracking.put( relations[i][ 1 ], parents);
						System.out.println("Child : " + relations[ i ][ 1 ] + " Parent :" + relations[i][0]);
					} else if( !tracking.containsKey(relations[i][0]) ) {
						System.out.println("TRACKER : " + tracking);
						tracking.put(relations[i][0], new ArrayList<Integer>());
						//System.out.println("Parent : " + relations[ i ][ 0 ] );
					}
				}
			}
		}
		return displayResult(tracking);
	}

	/**
	 * Separate Nodes with 0 parents and 1 parent
	 * Parent list size it 0 = no parent to this node
	 * Parent list size it 1 = 1 parent to this node
	 */
	public static Map<Integer, List<Integer>> displayResult( Map<Integer, List<Integer>> result) {
		Map<Integer, List<Integer>> desiredResult  = new  HashMap<Integer, List<Integer>>();
		List<Integer> oneParents = new ArrayList<Integer>();
		List<Integer> noParents = new ArrayList<Integer>();

		if(result != null) {
			for( int key : result.keySet() ) {
				List<Integer> parents = result.get( key);
				if( parents.size() == 0) {
					noParents.add(key);
				} else if ( parents.size() == 1 ) {
					oneParents.add( key);
				}
			}
			desiredResult.put(0, noParents);
			desiredResult.put(1, oneParents);
		}
		return desiredResult;
	}
}
