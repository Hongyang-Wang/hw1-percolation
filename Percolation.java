import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private WeightedQuickUnionUF uf;
	private int N;
	private boolean[] open;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.N = N;
		int LEN = N * N + 1;
		this.uf = new WeightedQuickUnionUF(LEN);
		this.open = new boolean[LEN];
		this.open[0] = true;
	}
	
	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		if (i < 1 || j < 1 || i > N || j > N) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		int n = ij2n(i, j);
		if (this.open[n] == false) {
			open[n] = true;
			if (i == 1) {
				this.uf.union(0, n);
			}
			if (i - 1 > 0 && this.open[ij2n(i - 1, j)]) {
				this.uf.union(n, ij2n(i - 1, j));
			}
			if (i + 1 <= N && this.open[ij2n(i + 1, j)]) {
				this.uf.union(n, ij2n(i + 1, j));
			}
			if (j - 1 > 0 && this.open[ij2n(i, j - 1)]) {
				this.uf.union(n, ij2n(i, j - 1));
			}
			if (j + 1 <= N && this.open[ij2n(i, j + 1)]) {
				this.uf.union(n, ij2n(i, j + 1));
			}
		}
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		if (i < 1 || j < 1 || i > N || j > N) {
			throw new java.lang.IndexOutOfBoundsException();
		}		
		return this.open[ij2n(i, j)];
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		if (i < 1 || j < 1 || i > N || j > N) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		return this.uf.connected(0, ij2n(i, j));
	}
	
	// does the system percolate?
	public boolean percolates() {
		for (int j = 1; j <= N; j++) {
			if (this.open[ij2n(N, j)] && this.uf.connected(0, ij2n(N, j))) {
				return true;
			}
		}
		return false;
	}
	
	private int ij2n(int i, int j) {
		return (i - 1) * this.N + j;
	}
	
	
	// test client (optional)
	public static void main(String[] args) {
		
	}
	
}
