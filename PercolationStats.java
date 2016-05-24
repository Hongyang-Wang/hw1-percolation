import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private double[] thr;
	private int T;
	
	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new java.lang.IllegalArgumentException();
		}	
		thr = new double[T];	
		this.T = T;
		int totalBlocks = N * N;
		for (int t = 0; t < T; t++) {
			Percolation perc = new Percolation(N);
			int numOpen = 0;
			while (perc.percolates() == false) {
				int pos = StdRandom.uniform(0, totalBlocks);
				int i = pos / N + 1, j = pos % N + 1;
				if (perc.isOpen(i, j) == false) {
					perc.open(i, j);
					numOpen++;
				}
			}
			this.thr[t] = numOpen * 1.0 / totalBlocks;
		}
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(this.thr);
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(this.thr);
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(this.T);
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(this.T);
	}
	
	// test client (described below)
	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println("mean\t= " + ps.mean());
		System.out.println("stddev\t= " + ps.stddev());
		System.out.println("95% confidence interval\t= " + ps.confidenceLo() + ", " + ps.confidenceHi());
	}
}
