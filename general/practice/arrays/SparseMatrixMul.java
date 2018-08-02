import java.util.Map;
import java.util.HashMap;

public class SparseMatrixMul {
    public int[][] multiply(int[][] A, int[][] B) {
        int rows1 = A.length;
        int cols1 = A[0].length;
        int rows2 = B.length;
        int cols2 = B[0].length;
        int[][] result = new int [rows1][cols2];
        Map<Integer, HashMap<Integer, Integer>> aMap = new HashMap<Integer, HashMap<Integer, Integer>>();
		//Create kinda adjacency list from first matrix. This denotes map of row to hashmap, where the internal hashmap
		//contains column and non-zero value
		/*
	1 0 0      {0 --> < (0, 1) >, 1 --> (1, 2)}
    0 2 0
	*/
        for(int i = 0; i < rows1; ++i) {
            for(int j = 0; j < cols1; ++j) {
                HashMap<Integer, Integer> columnTracker = null;
                if(A[i][j] != 0) {
                    if(!aMap.containsKey(i)) {
                        columnTracker = new HashMap<Integer,Integer>();
                    } else {
                        columnTracker = aMap.get(i);
                    }
                    columnTracker.put(j, A[i][j]);
                    aMap.put(i, columnTracker);
                }
            }
        }
		//parsing second matix as rows first then columns
        for(int i = 0; i < cols2; ++i) {//columns
            for(int j = 0; j < rows2; ++j) {//rows
                if(B[j][i] != 0) {
                    for( Map.Entry<Integer, HashMap<Integer, Integer>> entry : aMap.entrySet()) {
                        int resultRow = entry.getKey();//index of row in result matrix
                        //System.out.println("result row : " + resultRow );
                        for(Map.Entry<Integer, Integer> vals : entry.getValue().entrySet()) {
                            //System.out.println(" Value : " + vals.getValue() );
                            if(vals.getKey() == j) { // if current row index matches the column index in the first matrix
                                result[resultRow][i] += (B[j][i] * vals.getValue());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
