package unionFind;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonteCarloSimulationTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMonteCarloSimulation() {
		MonteCarloSimulation mcs = new MonteCarloSimulation(5);
		assertNotNull(mcs);
	}

	@Test
	void testRoot() {
		MonteCarloSimulation mcs = new MonteCarloSimulation(5);
		assertNotNull(mcs);
		int expected = 0;
		int actual= mcs.root(1);
		assertEquals(expected, actual);
	}

	@Test
	void testUnion() {
		MonteCarloSimulation mcs = new MonteCarloSimulation(5);
		assertNotNull(mcs);
		int expected = 0;
		int actual= mcs.root(1);
		assertEquals(expected, actual);
	}

	@Test
	void testConnected() {
		MonteCarloSimulation mcs = new MonteCarloSimulation(5);
		assertNotNull(mcs);
		mcs.union(5, 15);
		boolean expected = true;
		boolean actual= mcs.connected(5, 15);
		assertEquals(expected, actual);
	}
	
	@Test
	void testMapCreate() {
		MonteCarloSimulation mcs = new MonteCarloSimulation(10);
		assertNotNull(mcs);
		mcs.generateRandomGraph();
		mcs.mapGraph();
		mcs.printGraph();
		System.out.println();
		System.out.println("Can Perculate:" + mcs.canPerculate());
		
	}

}
