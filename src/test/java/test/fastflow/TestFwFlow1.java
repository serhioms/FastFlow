package test.fastflow;

import org.junit.BeforeClass;

import ca.rdmss.fastflow.FwHighOrder;
import test.impl.TestBase;

public class TestFwFlow1 extends TestFwFlow {
	
	/*
	 * Initialize 
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestBase.MAX_THREADS = 1; // minimal amount of threads for the test case is 1 !!!
		TestBase.setUpBeforeClass();
		sequential = FwHighOrder.sequential(executor);
		parallel = FwHighOrder.parallel(executor);
	}
}
