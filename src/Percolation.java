import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private static final boolean CLOSED = false;
	private static final boolean OPEN = true;
	private int currentNumberSitesOpen = 0;
	private final boolean[] map; // the map shows which cells are open, and which cells are closed.
	private final int n; // the number of rows, and the number of columns since it's always a square.
	private final int virtualEnd; // represents the bottom of the graph
	private final int virtualRoot; // represents the top of the graph
	private final WeightedQuickUnionUF wqu;

	public Percolation(int n) {

		if (n <= 0) {
			throw new IllegalArgumentException();
		}

		this.n = n; // keep track of the original n parameter, so we can do things like print the
					// map more easily

		// create an n x n grid, with all sites blocked
		this.map = new boolean[n * n]; // I like flat arrays :)

		this.virtualRoot = map.length; // the second to last element of the ID array is'virtual'
		this.virtualEnd = map.length + 1; // the last element of the ID array is 'virtual'

		this.wqu = new WeightedQuickUnionUF(this.map.length + 2);

		// initialize the arrays.
		for (int i = 0; i < n * n; i++) {
			this.map[i] = CLOSED; // all sites are closed
		}

		// link the first and last rows of the graph to their corresponding virtual
		// elements.
		for (int i = 0; i < n; i++) {
			this.wqu.union(i, this.virtualRoot);
			this.wqu.union(map.length - n + i, this.virtualEnd);
		}

	}

	public boolean isFull(int row, int col) {
		this.checkArgs(row, col);
		// return true if the row,col is full.
		// A full site is an open site that can be connected to an open site in the top
		// row via a chain of neighboring (left, right, up, down) open sites
		return isOpen(row, col) && connected(this.virtualRoot, this.flatten(row, col));
	}

	public boolean isOpen(int row, int col) {
		this.checkArgs(row, col);
		// return true if this row,col is open
		if (this.checkBounds(row, col)) {
			return this.map[this.flatten(row, col)] == OPEN;
		} else {
			return CLOSED;
		}

	}

	public int numberOfOpenSites() {
		// returns the number of sites in the grid that are open.

		return this.currentNumberSitesOpen;
	}

	public void open(int row, int col) {
		this.checkArgs(row, col);
		// open site row, col if it's not open already
		if (this.checkBounds(row, col)) {
			if (!isOpen(row, col)) {
				this.map[this.flatten(row, col)] = OPEN;
				this.currentNumberSitesOpen++; // keep track of the number of open sites.
				this.onOpenChanged(row, col);
			}
		}
	}

	public boolean percolates() {
		// returns true if the system percolates
		if (n == 1) {
			// since there is only one element, it also has to be opened
			if (!this.isOpen(1, 1)) {
				return false;
			}
		}
		return this.root(this.virtualRoot) == this.root(this.virtualEnd);
	}

	private boolean connected(int p, int q) {
		// return true if the two points have the same root
		return this.wqu.connected(p, q);

	}

	private void checkArgs(int row, int col) {
		if (row < 1 || col < 1) {
			throw new IllegalArgumentException();
		}

		if (flatten(row, col) < 0 || flatten(row, col) > this.map.length) {
			throw new IllegalArgumentException();
		}
	}

	private int flatten(int row, int col) {
		return (row - 1) * n + col - 1;
	}

	private void onOpenChanged(int row, int col) {
		// since the open changed, I need to update the ids to make sure that the newly
		// open site points
		// to the correct roots
		final int mapOffset = this.flatten(row, col);

		if (mapOffset > 0 && col > 1 && this.isOpen(row, col - 1)) {
			// the cell to the left of this cell is open.
			// connect these two
			this.union(mapOffset, mapOffset - 1);
		}
		if (mapOffset < this.map.length - 1 && col < n && this.isOpen(row, col + 1)) {
			// the cell to the right of this one is open
			// connect these cells
			this.union(mapOffset, this.flatten(row, col + 1));
		}
		if (row > 1 && isOpen(row - 1, col)) {
			// the cell above this one is open.
			// connect these cells
			this.union(mapOffset, this.flatten(row - 1, col));
		}
		if (row < n && isOpen(row + 1, col)) {
			// the cell below this one is open.
			// connect these cells
			this.union(mapOffset, this.flatten(row + 1, col));
		}

	}

	private boolean checkBounds(int row, int col) {
		return this.flatten(row, col) <= (this.map.length - 1);
	}

	private int root(int i) {
		// return the root of the node
		return this.wqu.find(i);
	}

	private void union(int p, int q) {
		// set the root of p to the root of q
		this.wqu.union(p, q);
	}

}
