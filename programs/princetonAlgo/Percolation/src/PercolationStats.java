import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double [] openSiteFractions;
    private int size;
    private int trials;
    private double mean;
    private double stdDev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
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

        PercolationStats percolationStats = new PercolationStats(size, trials);
        double gridSize = size * size;
        for(int i = 0; i < trials; ++i) {
            Percolation percolation = new Percolation(size);
            do {
                int row = StdRandom.uniformInt(1,size +  1);
                int col = StdRandom.uniformInt(1,size + 1);
                percolation.open(row, col);
            } while(!percolation.percolates());
            percolationStats.openSiteFractions[i] = percolation.numberOfOpenSites() / gridSize;
        }
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("Stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}
