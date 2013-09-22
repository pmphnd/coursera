public class PercolationStats {
    private double[] arr;
    private int T, N;
    private int count;

    public PercolationStats(int N, int T) // perform T independent computational
                                          // experiments on an N-by-N grid
    {
        if (N <= 0 || T <= 0)
            throw new java.lang.IllegalArgumentException();

        arr = new double[T];
        this.T = T;
        this.N = N;
        this.count = 0;

        run();

    }

    private void run() {
        for (int i = 0; i < T; i++) {
            Percolation perc = new Percolation(N);
            count = 0;
            while (!perc.percolates()) {
                int randI = StdRandom.uniform(N) + 1;
                int randJ = StdRandom.uniform(N) + 1;

                while (perc.isOpen(randI, randJ)) {
                    randI = StdRandom.uniform(N) + 1;
                    randJ = StdRandom.uniform(N) + 1;
                }
                count++;
                perc.open(randI, randJ);
            }
            perc = null;
            arr[i] = ((double) count) / ((double) (N * N));
        }
    }

    public double mean() // sample mean of percolation threshold
    {
        return StdStats.mean(arr);
    }

    public double stddev() // sample standard deviation of percolation threshold
    {
        if (arr.length <= 1)
            return Double.NaN;
        return StdStats.stddev(arr);
    }

    public double confidenceLo() // returns lower bound of the 95% confidence
                                 // interval
    {
        double mean = mean();
        return (mean - 1.96 * stddev() / Math.sqrt(T));
    }

    public double confidenceHi() // returns upper bound of the 95% confidence
                                 // interval
    {
        double mean = mean();
        return (mean + 1.96 * stddev() / Math.sqrt(T));

    }

    public static void main(String[] args) // test client, described below
    {

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo()
                + ", " + stats.confidenceHi());
    }
}
