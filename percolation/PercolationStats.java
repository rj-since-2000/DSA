import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private int t;
    private double mean;
    private double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("arguement should be > 0");
        else {
            t = trials;
            int i = 0;
            double[] arr = new double[t];
            double d = n * n;

            while (trials > 0) {
                Percolation p = new Percolation(n);

                while (!p.percolates()) {
                    p.open((StdRandom.uniform(1, n + 1)),
                           (StdRandom.uniform(1, n + 1)));
                }
                arr[i] = p.numberOfOpenSites() / d;
                trials--;
                i++;
            }
            mean = StdStats.mean(arr);
            stddev = StdStats.stddev(arr);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean - (CONFIDENCE_95 * stddev / Math.sqrt(t)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean + (CONFIDENCE_95 * stddev / Math.sqrt(t)));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats p1 = new PercolationStats(200, 100);
        System.out.println("mean                    = " + p1.mean());
        System.out.println("stddev                  = " + p1.stddev());
        System.out.println(
                "95% confidence interval = [" + p1.confidenceLo() + ", " + p1.confidenceHi() + "]");
    }
}
