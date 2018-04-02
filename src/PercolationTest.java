import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PercolationTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructor() {
		Percolation p = new Percolation(10);
		assertNotNull(p);
		p = new Percolation(1);
		assertNotNull(p);
		p = new Percolation(1000);
		assertNotNull(p);

	}

	@Test
	void testIsOpen() {
		Percolation p = new Percolation(10);
		p.open(1, 1);
		boolean expected = true;
		boolean actual = p.isOpen(1, 1);
		assertEquals(expected, actual);
		p = new Percolation(2);
		p.open(1, 2);
		p.open(1, 2);
		p.open(1, 1);
		p.open(2, 1);
		actual = p.isOpen(1, 1);
		expected = true;
		assertEquals(expected, actual);

		// make sure we throw the correct error.
		expected = true;
		actual = false;
		try {
			p.open(20, 20);
		} catch (java.lang.IllegalArgumentException es) {
			actual = true;
		}
		assertEquals(expected, actual);

		expected = true;
		actual = false;
		try {
			p.open(-20, 20);
		} catch (java.lang.IllegalArgumentException es) {
			actual = true;
		}
		assertEquals(expected, actual);

	}

	@Test
	void testIsFull() {
		Percolation p = new Percolation(10);
		p.open(1, 2);
		boolean expected = true;
		boolean actual = p.isFull(1, 2);
		assertEquals(expected, actual);
		expected = false;
		actual = p.isFull(2, 2);
		assertEquals(expected, actual);

		p = new Percolation(2);
		p.open(1, 1);
		actual = p.isFull(1, 1);
		expected = true;
		assertEquals(expected, actual);
		expected = false;
		actual = p.isFull(1, 2);
		assertEquals(expected, actual);

	}

	@Test
	void testNumberOfOpenSites() {
		Percolation p = new Percolation(10);
		p.open(1, 1);
		int expected = 1;
		int actual = p.numberOfOpenSites();
		assertEquals(expected, actual);
		p.open(1, 2);
		expected = 2;
		actual = p.numberOfOpenSites();
		assertEquals(expected, actual);

	}

	@Test
	void testPercolates() {
		Percolation p = new Percolation(10);
		for (int i = 1; i <= 10; i++) {
			p.open(i, 1);
		}
		boolean expected = true;
		boolean actual = p.percolates();
		assertEquals(expected, actual);

		// xx
		// xx
		p = new Percolation(2);
		expected = false;
		actual = p.percolates();
		assertEquals(expected, actual);

		// 1x
		// xx
		p.open(1, 1);
		expected = false;
		actual = p.percolates();
		assertEquals(expected, actual);

		// 1x
		// x1
		p.open(2, 2);
		expected = false;
		actual = p.percolates();
		assertEquals(expected, actual);

		// 1x
		// 11
		p.open(2, 1);
		expected = true;
		actual = p.percolates();
		assertEquals(expected, actual);

		p = new Percolation(1);
		expected = false;
		actual = p.percolates();
		assertEquals(expected, actual);

	}

}
