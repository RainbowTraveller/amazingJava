/* A land mass consisting of land and water is represented by a matrix
 * The matrix is represented by 1 and 0 where 1 represents soil and 0 represents
 * water.
 * Find the greatest island contained in the given matrix
 */
import java.lang.Math;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConnectedAreas {

	private int [][] topology;
	private int noOfIslands = 0;
	private int biggestIslandSize = 0;

	public static void main( String[] args ) {
		Scanner scn = new Scanner( System.in );
		/*try {
			BufferedReader reader = new BufferedReader(new FileReader("input"));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {

			}


		} catch (IOException ioe) {

		}*/
		int rows = scn.nextInt();
		int cols = scn.nextInt();
		ConnectedAreas ca = new ConnectedAreas( rows, cols );
		ca.getIsland();
	}


	public ConnectedAreas( int r, int c ) {
		 topology = new int [ r ][ c ];
		 for( int i = 0; i < r ; i++ ) {
			 for( int j = 0; j < c; ++j ) {
				 this.topology[i][j] = (int)( (int) 2 * Math.random() );
				 System.out.print( topology [i][j] + " ");
			 }
				System.out.println();
		 }
	}

	public void getIsland() {
		for( int i = 0; i < topology.length; ++i ) {
			for ( int j = 0; j < topology[0].length; ++j ) {
				if( topology[ i ][ j  ] == 1 ) {
					noOfIslands++;
					int currentIslandSize  = getBiggestIsland( i, j );
					//System.out.println( " Current : "  + currentIogslandSize  + " Biggest : "  +  biggestIslandSize);
                    biggestIslandSize = Math.max(biggestIslandSize, currentIslandSize);
				}
			}
		}
		System.out.println("No of Islands :" + noOfIslands + " Biggest Island Available : " + biggestIslandSize );
	}

	private int getBiggestIsland( int i, int j ) {
		//i < 0 --> out of bound
		//i > row no. --> topology.length
		//j < 0 --> out of bound
		//j > col no. --> topology[].length
		//value at i,j = 0

		if( i < 0 || i >= topology.length || j < 0 || j >= topology[0].length || topology[i][j] == 0 ) {
			return 0;
		}
		this.topology[ i ][ j ] = 0;
		return (  1 + getBiggestIsland( i + 1, j )  + getBiggestIsland( i - 1, j )  + getBiggestIsland( i, j + 1 ) +  getBiggestIsland( i, j - 1 ) );
	}
}
