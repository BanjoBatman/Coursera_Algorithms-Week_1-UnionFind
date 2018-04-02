import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PercolationStatsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPercolationStats() {
		PercolationStats ps = new PercolationStats(2,10000);
		assertNotNull(ps);
	}


	@Test
	void testMain() {
		//PercolationStats ps = new PercolationStats(200,100);
		//assertNotNull(ps);
		PercolationStats.main(new String[] {"2","10000"});
		PercolationStats.main(new String[] {"6","1"});
		PercolationStats.main(new String[] {"5","2"});
		PercolationStats.main(new String[] {"4","3"});
		PercolationStats.main(new String[] {"3","3"});
		PercolationStats.main(new String[] {"3","4"});
		PercolationStats.main(new String[] {"2","5"});
		PercolationStats.main(new String[] {"1","3"});
		
	}

}
