public class IslandPerimeter {


	private int [][] topology = {
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
		};
	private int perimeter = 0;

	public static void main( String[] args ) {
		IslandPerimeter ip = new IslandPerimeter();
		ip.getIslandPerimeter();
	}

	public void getIslandPerimeter() {
		for( int i = 0; i < topology.length; ++i ) {
			for ( int j = 0; j < topology[0].length; ++j ) {
				if( topology[ i ][ j  ] == 1 ) {
					int curr = 0;
					// i + 1, j
					if( i + 1 >= topology.length || topology[ i + 1 ][ j ] == 0) {
						curr++;
					}
					// i - 1, j
					if( i -1 < 0 || topology[ i - 1 ][ j ] == 0) {
						curr++;
					}
					// i, j + 1
					if( j + 1 >= topology[0].length || topology[ i ][ j + 1 ] == 0) {
						curr++;
					}
					// i, j - 1
					if( j - 1 < 0 || topology[ i ][ j - 1 ] == 0) {
						curr++;
					}
					perimeter += curr;
				}
			}
		}
		System.out.println(" Perimiter " + perimeter);
	}
}
