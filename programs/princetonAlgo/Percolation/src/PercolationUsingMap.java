import java.util.HashMap;
import java.util.Map;

public class PercolationUsingMap {
    /**
     * Key Value pair storing the information of the sites
     */
    Map<Integer,Site> grid;
    /**
     * These are virtual sites, not part of the actual percolation system.
     * These mark as start and end of the percolation system, which are used to decide if a system percolates
     * If start and end are connected then the system percolates
     */
    Site virtualTop, virtualBottom;
    int bound, noOfOpenSites;
    // creates n-by-n grid, with all sites initially blocked

    /**
     * Initializing the percolation system, accepts the grid dimensions. The grid is symmetric as the same value
     * is used for rows and column
     * @param n grid dimension used as rows and columns number
     */
    public PercolationUsingMap(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("Invalid bound for the grid");
        }
        bound = n;
        noOfOpenSites = 0;
        grid = new HashMap<>();
        /* Initialize each site
        1. Set size of the connected component ( tree ) to 1
        2. Set id to the no. of node from the first one
       */
        for(int i = 1; i <= n ; ++i) {
            for(int j = 1; j <= n; j++) {
                Site curr = new Site(i, j);
                curr.setSize(1);
                int id = getSiteId(i, j);
                curr.setId(id);
                grid.put(id, curr);
            }
        }

        //Initializing Virtual Top
        virtualTop = new Site(0, 0);
        virtualTop.setId(0);
        virtualTop.setConnection(virtualTop);
        virtualTop.setSize(1);
        grid.put(getSiteId(0,0), virtualTop);

        //Initializing Virtual Bottom
        virtualBottom = new Site(n + 1, n + 1);
        virtualBottom.setId(bound + 1);
        virtualBottom.setConnection(virtualBottom);
        virtualBottom.setSize(1);
        grid.put(getSiteId(bound + 1, bound + 1), virtualBottom);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateBound(row, col);
        if(isFull(row, col)) {
            Site toBeOpened = getSite(row, col);
            if(toBeOpened != null) {
                toBeOpened.setConnection(toBeOpened);
                noOfOpenSites++;
                //If the site is in the top row, connect it to the virtual top
                if(row == 1) {
                    union(0,0, row, col);
                }
                //If the site is in the bottom row, connect it to the virtual bottom
                if(row == bound) {
                    union(bound + 1,bound + 1, row, col);
                }
                //Check neighboring sites and connected with the current open site if they are open too
                if(row - 1 > 0) {
                    union(row, col, row - 1, col);
                }
                if(row + 1 > 0 && row + 1 <= bound) {
                    union(row, col, row + 1, col);
                }
                if(col - 1 > 0) {
                    union(row, col, row, col - 1);
                }
                if(col + 1 > 0 &&  col + 1 <= bound) {
                    union(row, col, row, col + 1);
                }
            }
        }
        /* else {
            System.out.println("The site is already open");
        }*/
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateBound(row, col);
        Site currSite = getSite(row, col);
        return currSite != null && currSite.getConnection() != null;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateBound(row, col);
        Site currSite = getSite(row, col);
        return currSite!= null && currSite.getConnection() == null;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return noOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        //System.out.println(virtualTop + " and " + virtualBottom);
        return this.isConnected(virtualTop.getRow(), virtualTop.getCol(), virtualBottom.getRow(), virtualBottom.getCol());
    }

    private Site getRoot(int row, int col) {
        validateBound(row, col);
        Site curr = getSite(row, col);
        while(curr != null && !(curr.getConnection().equals(curr))) {
            curr = curr.getConnection();
        }
        return curr;
    }

    public void union(int row1, int col1, int row2, int col2) {
        if(isOpen(row1, col1) && isOpen(row2, col2)) {
            /*if(row1 == 0 && col1 == 0) {
                System.out.println("Top : " + isOpen(row1, col1));
            }
            if(row1 == bound + 1 && col1 == bound + 1) {
                System.out.println("Bottom : " + isOpen(row1, col1));
            }*/

            Site pRoot = getRoot(row1, col1);
            Site qRoot = getRoot(row2, col2);
            //pRoot.setConnection(qRoot);

            if(pRoot.equals(qRoot))return;

            if(pRoot.getSize() > qRoot.getSize()) {
                pRoot.setConnection(qRoot);
                grid.put(pRoot.getId(), pRoot);
                pRoot.setSize(pRoot.getSize() + qRoot.getSize());
            } else {
                qRoot.setConnection(pRoot);
                grid.put(qRoot.getId(), qRoot);
                qRoot.setSize(qRoot.getSize() + pRoot.getSize());
            }
        }
    }
    private void validateBound(int x, int y) {
        //System.out.println("Validation : " + x + " :: " + y);
        if(!(x == 0 && y == 0) && !(x == bound + 1 && y == bound + 1) && (x < 1 || x > bound  || y < 1 || y > bound)) {
            throw new IllegalArgumentException("Invalid bound parameters");
        }
    }

    private Site getSite(int row, int col) {
        return grid.getOrDefault(getSiteId(row, col), null);
    }

    private int getSiteId(int row, int col) {
        return (row - 1) * bound + col;
    }

    private boolean isConnected(int row1, int col1, int row2, int col2) {
        Site pRoot = getRoot(row1, col1);
        Site qRoot = getRoot(row2, col2);
        //System.out.println(pRoot + " ::: " + qRoot);
        return pRoot == qRoot;
    }

    static void printGrid(PercolationUsingMap percolationUsingMap) {
        for(int i = 1; i <= percolationUsingMap.bound; ++i) {
            for(int j = 1; j <= percolationUsingMap.bound; ++j) {
                int key =  (i - 1) * percolationUsingMap.bound + j;
                Site node = percolationUsingMap.grid.get(key);
                System.out.println("Key : " + key + " : " + node + node.getConnection());
            }
        }
    }

    public static void main(String[] args) {
        /*System.out.println("Enter Grid Size : ");
        int n = StdIn.readInt();
        PercolationUsingMap percolation = new PercolationUsingMap(n);
        System.out.println("Enter number of attempts : ");
        int attempts = StdIn.readInt();
        for(int i = 0; i < attempts; ++i) {
            int row = StdRandom.uniformInt(1,percolation.bound + 1);
            int col = StdRandom.uniformInt(1,percolation.bound + 1);
            //System.out.println(row + " : " +  col);
            percolation.open(row, col);
            if(percolation.percolates()) {
                System.out.println("PercolationUsingMap successful ....! Attempts : " + i);
                break;
            }
        }
         */
        //printGrid(percolation);
    }
}