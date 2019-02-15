/**
 *	An image containing various shapes. A pixel coordinates are given.
 *	if the pixel lies inside an image then paint only that shape black
 *	if the pixel lies outside an image then interior of shapes remains white
 *	and background remains black
 */

import java.util.Stack;

public class PaintRegion {

	public static void paintRegion(int[][]bitMap, int x, int y) {
		if(bitMap != null) {
			int rows = bitMap.length;
			int columns = bitMap[0].length;
			paintRegionHelper(bitMap, rows, columns, x, y);
		}
	}

	/**
	* if we have 0 and within the limit : mark it 1
	* or return
	*/
	public static void paintRegionHelper( int[][]bitMap, int rows, int columns, int x, int y) {

		if( x < 0 || x >= rows || y < 0 || y >= columns || bitMap[x][y] == 1) {
				return;
		}

		bitMap[x][y] = 1;
		//up
		paintRegionHelper(bitMap, rows, columns, x - 1 , y);
		//Down
		paintRegionHelper(bitMap, rows, columns, x + 1 , y);
		//Left
		paintRegionHelper(bitMap, rows, columns, x, y - 1);
		//Right
		paintRegionHelper(bitMap, rows, columns, x, y + 1);

	}

	public static void paintRegionHelperDFS( int[][]bitMap, int rows, int columns, int x, int y) {
		Stack<Pixel> tracker = new Stack<Pixel>();
		Pixel startingPoint = new Pixel(x ,y);
		tracker.push( startingPoint );

		while( !tracker.isEmpty() ) {

			Pixel currentPixel = tracker.pop();
			int currX = currentPixel.getX();
			int currY = currentPixel.getY();

			if( currX < 0 || currX >= rows || currY < 0 || currY >= columns || bitMap[currX][currY] == 1) {
					continue;
			} else {
				bitMap[x][y] = 1;

				Pixel up = new Pixel(x - 1 ,y);
				tracker.push( up );

				Pixel down = new Pixel(x + 1 ,y);
				tracker.push( down );

				Pixel left = new Pixel(x ,y - 1);
				tracker.push( left );

				Pixel right = new Pixel(x ,y + 1);
				tracker.push( right );
			}
		}
	}
}
	class Pixel {
		int x;
		int y;

		public Pixel( int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}


