/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double percentile95n;
    private final double percentile95p;

    public PercolationStats(int size, int trialsCount) {
        if (size < 1 || trialsCount < 1) {
            throw new IllegalArgumentException();
        }

        double[] rates = new double[trialsCount];
        for (int i = 0; i < trialsCount; i++) {
            rates[i] = getPercolationRate(size);
        }
        mean = StdStats.mean(rates);
        stddev = StdStats.stddev(rates);
        double percentile5 = 1.96 * stddev / Math.sqrt(trialsCount);
        percentile95n = mean - percentile5;
        percentile95p = mean + percentile5;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return percentile95n;
    }

    public double confidenceHi() {
        return percentile95p;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Two required arguments: n, T");
        }
        int size = Integer.parseInt(args[0]);
        if (size < 1) {
            throw new IllegalArgumentException("n cannot be less than 1");
        }
        int trialsCount = Integer.parseInt(args[1]);
        if (trialsCount < 1) {
            throw new IllegalArgumentException("T cannot be less than 1");
        }

        Stopwatch w = new Stopwatch();
        PercolationStats stats = new PercolationStats(size, trialsCount);

        print("mean", Double.toString(stats.mean));
        print("stddev", Double.toString(stats.stddev));
        print("95% confidence interval", "[" + stats.percentile95n + ", " + stats.percentile95p + "]");
        print("time", Double.toString(w.elapsedTime()));
    }

    private static void print(String name, String value) {
        StdOut.println(String.format("%-25s", name) + " = " + value);
    }

    private static double getPercolationRate(int size) {
        Percolation percolation = new Percolation(size);
        int count = 0;
        while (!percolation.percolates()) {
            int row = 1 + StdRandom.uniform(size);
            int col = 1 + StdRandom.uniform(size);
            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
                count++;
            }
        }
        return count * 1.0 / (size * size);
    }
}
