/*  Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
*
*  Example 1:
*
*  Input:
*  [
*   [ 1, 2, 3 ],
*   [ 4, 5, 6 ],
*   [ 7, 8, 9 ]
*  ]
*  Output: [1,2,3,6,9,8,7,4,5]
*  Example 2:
*
*  Input:
*  [
*    [1, 2, 3, 4],
*    [5, 6, 7, 8],
*    [9,10,11,12]
*  ]
*  Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*
*
*
* Dividing the matrix into 4 components
* Top : rstart => cstart to cend
* right : cend => rstart + 1 to rend
* bottom : rend => cends - 1 to cstart + 1 (decrementing)
* left : cstart => rend to rstart + 1 (descending)
*
* As the layers converge, check if rstart <= rend an cstart <= cend
*/

import java.util.List;
import java.util.ArrayList;

public class SpiralMatrix {
    public static void main(String[] args) {

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> unspiral = new ArrayList<Integer>();
        if(matrix.length == 0) {
            return unspiral;
        }

        int rstart = 0, rend = matrix.length - 1;
        int cstart = 0, cend = matrix[0].length - 1;

        // Traverse matrix layer by layer
        //start with outer layer, manager row and column index there
        // once done move to next layer by reducing outer row and colum ends
        while(rstart <= rend && cstart <= cend) {
            for(int col = cstart; col <= cend; col++) {
                unspiral.add(matrix[rstart][col]);
            }
            for(int row = rstart + 1; row <= rend; ++row) {
                unspiral.add(matrix[row][cend]);
            }

            //if matrix layer has 4 side to cover
            if(rstart < rend && cstart < cend) {
                for(int col= cend - 1; col >= cstart + 1; col--) {
                    unspiral.add(matrix[rend][ col]);
                }

                for(int row = rend; row >= rstart + 1; row--) {
                    unspiral.add(matrix[row][cstart]);
                }
            }

            rstart++;
            cstart++;
            rend--;
            cend--;
        }
        return unspiral;
    }
}

