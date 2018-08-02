import
class Solution {
  public static void main(String[] args) {
    int[][] parentChildPairs = new int[][] {
        {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7},
        {4, 5}, {4, 8}, {8, 9}
    };

    //Go over the input
    //Look at each child
    //and track the parents that gives us number

    Map<Integer, List<Integer>> tracking = Solution.getChildParentMapping( parentChildPairs );

    for(int key : tracking.keySet()) {

      List<Integer> nodes = tracking.get(key);
      System.out.println(nodes);
    }

  }

  public static Map<Integer, List<Integer>> getChildParentMapping( int [][] relations ) {
    Map<Integer, List<Integer>> tracking = new HashMap<Integer, List<Integer>>();

    if( relations != null ) {

        for(int i = 0; i < relations.length; i++) {
           for( int j = 0; j < relations[0].length; ++j) {
              //System.out.println(relations[i][j]);
              List<Integer> parents = new ArrayList<Integer>();
              if( j == 1) {
                if( tracking.containsKey(relations[i][j]) ) {
                  parents = tracking.get( relations[i][j]);
                }
                parents.add( relations[i][0]);
                tracking.put( relations[i][ 1 ], parents);
                //System.out.println("Child : " + relations[ i ][ 1 ] + " Parent :" + relations[i][0]);
              } else {
                tracking.put(relations[i][0], new ArrayList<Integer>());
                System.out.println("Child : " + relations[ i ][ 0 ] );
              }
           }
          //System.out.println("");

        }
    }

    return displayResult(tracking);
  }

public static Map<Integer, List<Integer>> displayResult( Map<Integer, List<Integer>> result) {

    Map<Integer, List<Integer>> desiredResult  = new  HashMap<Integer, List<Integer>>();
    List<Integer> oneParents = new ArrayList<Integer>();
    List<Integer> noParents = new ArrayList<Integer>();

    if(result != null) {

      for( int key : result.keySet() ) {

        List<Integer> parents = result.get( key);
        if( parents.size() == 0) {
          noParents.add(key);
        } else if ( parents.size() == ) {
          oneParents.add( key);
        }
      }


      desiredResult.put(0, noParents);
      desiredResult.put(1, oneParents);

    }
  return desiredResult;

}

}
