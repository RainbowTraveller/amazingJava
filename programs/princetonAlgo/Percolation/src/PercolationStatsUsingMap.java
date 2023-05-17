import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStatsUsingMap {

    double [] openSiteFractions;
    int size;
    int trials;
    double mean;
    double stdDev;

    // perform independent trials on an n-by-n grid
    public PercolationStatsUsingMap(int n, int trials) {
        size = n;
        this.trials = trials;
        openSiteFractions = new double[trials];
    }

    // sample mean of percolation threshold
    public double mean() {
        for(double val : openSiteFractions) {
            mean += val;
        }
        mean = mean / trials;
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        for(double val : openSiteFractions) {
            stdDev += (val - mean) * (val - mean);
        }
        stdDev /= (trials - 1);
        stdDev = Math.sqrt(stdDev);
        return stdDev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - ((1.96 * stdDev) / Math.sqrt(trials));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + ((1.96 * stdDev) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        if(args.length < 2) {
            throw new IllegalArgumentException("Insufficient no. of arguments");
        }
        int size = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);

        if( size <=0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        PercolationStatsUsingMap percolationStatsUsingMap = new PercolationStatsUsingMap(size, trials);
        double gridSize = size * size;
        for(int i = 0; i < trials; ++i) {
            PercolationUsingMap percolationUsingMap = new PercolationUsingMap(size);
            do {
                int row = StdRandom.uniformInt(1,size +  1);
                int col = StdRandom.uniformInt(1,size + 1);
                percolationUsingMap.open(row, col);
            } while(!percolationUsingMap.percolates());
            percolationStatsUsingMap.openSiteFractions[i] = percolationUsingMap.numberOfOpenSites() / gridSize;
        }
        StdOut.println("mean                    = " + percolationStatsUsingMap.mean());
        StdOut.println("Stddev                  = " + percolationStatsUsingMap.stddev());
        StdOut.println("95% confidence interval = " + percolationStatsUsingMap.confidenceLo() + ", " + percolationStatsUsingMap.confidenceHi());
    }
}
