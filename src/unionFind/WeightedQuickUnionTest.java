package unionFind;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeightedQuickUnionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testWeightedQuickUnion() {
		WeightedQuickUnion qu = new WeightedQuickUnion(11);
		assertNotNull(qu);
	}

	@Test
	void testUnion() {
		testConnected();
	}

	@Test
	void testConnected() {
		WeightedQuickUnion qu = new WeightedQuickUnion(11);
		assertNotNull(qu);
		qu.union(5, 10);
		boolean expected = true;
		boolean actual = qu.connected(5, 10);
		assertEquals(expected, actual);
		// join 0, and 10.
		// at this point, 0,5, and 10 should all show connected.
		qu.union(0,10);
		expected = true;
		actual = qu.connected(5, 10);
		assertEquals(expected, actual);
		actual = qu.connected(0, 10);
		assertEquals(expected, actual);
		actual = qu.connected(0, 5);
		assertEquals(expected, actual);
		
	}

}
