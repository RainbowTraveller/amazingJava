public class Percolation {
	private int [][] sites;

	public Percolation(int n) {                // create n-by-n grid, with all sites blocked
		if(n <=0) throw java.lang.IndexOutOfBoundsException;
		sites = new int [n][n];
		for(int i = 1; i <=n; ++i) {
			for(int j = 1; j <=n; ++j) {
				site[i][j] = 0;
			}
		}
	}

	public  void open(int row, int col) {    // open site (row, col) if it is not open already

	}
	public boolean isOpen(int row, int col) {  // is site (row, col) open?
	}
	public boolean isFull(int row, int col) { // is site (row, col) full?
	}
	public     int numberOfOpenSites() {      // number of open sites
	}
	public boolean percolates() {              // does the system percolate?
	}

	public static void main(String[] args) {   // test client (optional)i
	}
}

