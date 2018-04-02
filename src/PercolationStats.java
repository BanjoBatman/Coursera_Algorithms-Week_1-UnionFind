import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private static final double CONFIDANCE_CONSTANT = 1.96;
	private final double[] percentageOpenWhenPerculated; // this holds the results of each trail run, and is used for
															// the stats.

	public PercolationStats(int n, int trials) {
		percentageOpenWhenPerculated = new double[trials];
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("Both n and trials values must be greater than 0.");
		}

		// run the simulation trials
		for (int i = 0; i < trials; i++) {
			final Percolation p = new Percolation(n);
			while (!p.percolates()) {
				p.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));

			}
			percentageOpenWhenPerculated[i] = (double) p.numberOfOpenSites() / (double) (n * n);
		}

	}

	public static void main(String[] args) {
		if (args.length < 2) {

			throw new IllegalArgumentException("You must include 2 arguments.");
		}

		final int n = Integer.parseInt(args[0]);
		final int t = Integer.parseInt(args[1]);

		if (n <= 0 || t <= 0) {
			throw new IllegalArgumentException(
					"You must include 2 arguments, and they must both be integers greater than 0.");
		}

		final PercolationStats ps = new PercolationStats(n, t);
		ps.print();

	}

	public double confidenceHi() {
		return mean() + ((CONFIDANCE_CONSTANT + this.stddev()) / Math.sqrt(percentageOpenWhenPerculated.length));
	}

	public double confidenceLo() {
		return mean() - ((CONFIDANCE_CONSTANT * this.stddev()) / Math.sqrt(percentageOpenWhenPerculated.length));

	}

	public double mean() {

		return StdStats.mean(percentageOpenWhenPerculated);
	}

	public double stddev() {
		return StdStats.stddev(percentageOpenWhenPerculated);

	}

	private void print() {
		System.out.println("mean\t\t\t= " + this.mean());
		System.out.println("stddev\t\t\t= " + this.stddev());
		System.out.println("95% confidence interval = [" + this.confidenceLo() + ", " + this.confidenceHi() + "]");
	}

}
