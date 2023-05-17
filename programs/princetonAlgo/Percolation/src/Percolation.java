import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
public class Percolation {
    private int siteSize;
    private int bound;
    private int noOfOpenSites;
    private int virtualBottom, virtualTop;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean [][] tracker;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if( n <= 0 ) {
            throw new IllegalArgumentException("Insufficient range for a grid or percolation site");
        }
        bound = n;
        noOfOpenSites = 0;
        siteSize = n * n;
        weightedQuickUnionUF = new WeightedQuickUnionUF( siteSize + 2 );

        tracker = new boolean[n + 2][n + 2];
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < n + 1; j++ ) {
                tracker[i][j] = false;
            }
        }

        tracker[0][0] = true;
        tracker[n + 1][n + 1] = true;
        virtualTop = 0;
        virtualBottom = siteSize + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateBound(row, col);
        if(!isOpen(row, col)) {
            tracker[row][col] = true;
            noOfOpenSites++;
            int curr = getSiteId(row, col);
            //If the site is in the top row, connect it to the virtual top
            if(row == 1) {
                //System.out.print("Top Row : " + curr);
                weightedQuickUnionUF.union(virtualTop, curr);
                //System.out.println(" " + isConnected(0, curr));
            }
            //If the site is in the bottom row, connect it to the virtual bottom
            if(row == bound) {
                //System.out.print("Bottom Row :  " + curr);
                weightedQuickUnionUF.union(virtualBottom, curr);
                //System.out.println(" " + isConnected(bound + 1, curr));
            }
            //Check neighboring sites and connected with the current open site if they are open too
            if(row - 1 > 0 && isOpen(row - 1, col)) {
                int prevRow = getSiteId(row - 1, col);
                weightedQuickUnionUF.union(curr, prevRow);
                //System.out.println(" " + isConnected(prevRow, curr));
            }
            if(row + 1 <= bound && isOpen(row + 1, col)) {
                int nextRow =  getSiteId(row + 1, col);
                //System.out.print("Next Row :  " + nextRow);
                weightedQuickUnionUF.union(getSiteId(row, col), getSiteId(row + 1, col));
                //System.out.println(" " + isConnected(nextRow, curr));
            }
            if(col - 1 > 0 && isOpen(row, col - 1)) {
                int preCol = getSiteId(row, col - 1);
                //System.out.print("Prev Col :  " + preCol);
                weightedQuickUnionUF.union(curr, preCol);
                //System.out.println(" " + isConnected(preCol, curr));
            }
            if(col + 1 <= bound && isOpen(row, col + 1)) {
                int nextCol = getSiteId(row, col + 1);
                //System.out.print("Next Col :  " + nextCol);
                weightedQuickUnionUF.union(getSiteId(row, col), getSiteId(row, col + 1));
                //System.out.println(" " + isConnected(nextCol, curr));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateBound(row, col);
        return tracker[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if(isOpen(row, col)) {
           int siteId = getSiteId(row, col);
           return isConnected(0, siteId);
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return noOfOpenSites;
    }

    private boolean isConnected (int siteA, int siteB) {
        int siteAElement = weightedQuickUnionUF.find(siteA);
        int siteBElement = weightedQuickUnionUF.find(siteB);
        //System.out.println(" A : " +  siteAElement + " :: " + " B : " + siteBElement);
        return siteAElement == siteBElement;
    }

    // does the system percolate?
    public boolean percolates() {
        return isConnected(virtualTop, virtualBottom);
    }
    private int getSiteId(int row, int col) {
        return (row - 1) * bound + col;
    }
    private void validateBound(int x, int y) {
        if(x < 1 || x > bound || y < 1 || y > bound) {
            throw new IllegalArgumentException("Invalid bound parameters");
        }
    }
    private void printGrid() {
        System.out.println("Canonical Elements : " + weightedQuickUnionUF.count());
        System.out.println("Open Sites : " + noOfOpenSites);
        for(int i = 1; i < bound + 1; i++) {
            System.out.println(" ");
            for(int j = 1; j < bound + 1; j++ ) {
                System.out.print(" " + tracker[i][j]);
            }
        }
    }
    // test client (optional)
    public static void main(String[] args) {
        System.out.println("Enter Grid Size : ");
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        System.out.println("Enter number of attempts : ");
        int attempts = StdIn.readInt();

        for(int i = 0; i < attempts; ++i) {
            int row = StdRandom.uniformInt(1,percolation.bound + 1);
            int col = StdRandom.uniformInt(1,percolation.bound + 1);
            percolation.open(row, col);
            System.out.println(row + " : " +  col);
            System.out.println(" Open:  " + percolation.isOpen(row, col) + " Full : " + percolation.isFull(row, col));
            if(percolation.percolates()) {
                System.out.println("PercolationUsingMap successful ....! Attempts : " + i);
                break;
            }
        }
        percolation.printGrid();
    }
}
