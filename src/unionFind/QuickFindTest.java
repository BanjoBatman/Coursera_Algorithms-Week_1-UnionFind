/**
 * 
 */
package unionFind;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Tomas
 * 
 */
class QuickFindTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link unionFind.QuickFind#QuickFindUnionFind(int)}.
	 */
	@Test
	void testQuickFindUnionFind() {
		//a quick test to make sure the constructor remains correct
		QuickFind qfu = new QuickFind(10);
		assertNotNull(qfu);
	}

	/**
	 * Test method for {@link unionFind.QuickFind#connected(int, int)}.
	 */
	@Test
	void testConnected() {
		QuickFind qfu = new QuickFind(11);
		qfu.union(5, 10);
		boolean expected = true;
		boolean actual = qfu.connected(5, 10);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link unionFind.QuickFind#union(int, int)}.
	 */
	@Test
	void testUnion() {
		QuickFind qfu = new QuickFind(11);
		qfu.union(5, 10);
		boolean expected = true;
		boolean actual = qfu.connected(5, 10);
		assertEquals(expected, actual);
		// make a new connection
		qfu.union(0,10);
		expected = true;
		actual = qfu.connected(0, 10);
		
	}

}
