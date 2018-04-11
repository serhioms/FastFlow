package fastflow;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.fastflow.FwHighOrder;
import fastflow.impl.TestContext;
import fastflow.impl.TestFlows;
import test.TestBase;

public class TestFastFlowN extends TestBase {
	
	@Test
	public void testTFlowRunnables() throws InterruptedException {
		
		FwFlow<TestContext> wkf = TestFlows.zampleRunnables(sequential, parallel);

		for(int i=0; i<1; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals("12345", TestBase.getConcat());
			assertEquals(15, TestBase.getSum());
		}
	}
	
	@Test
	public void testTFlowConsumers() throws InterruptedException {
		
		FwFlow<TestContext> wkf = TestFlows.zampleConsumers(sequential, parallel);

		for(int i=0; i<1; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals("12345", TestBase.getConcat());
			assertEquals(15, TestBase.getSum());
		}
	}
	
	@Test
	public void testTFlowZample32() throws InterruptedException {
		
		FwFlow<TestContext> wkf = TestFlows.zample32(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals("231114511167", TestBase.getConcat());
			assertEquals(33, TestBase.getSum());
		}
	}

	@Test
	public void testTFlowZample62() throws InterruptedException {

		FwFlow<TestContext> wkf = TestFlows.zample62(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(34, TestBase.getSum());
			assertEquals("12222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testTFlowZample8() throws InterruptedException {

		FwFlow<TestContext> wkf = TestFlows.zample8(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(58, TestBase.getSum());
			assertEquals("12222222222222222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testTFlowZample7() throws InterruptedException {

		FwFlow<TestContext> wkf = TestFlows.zample7(sequential, parallel);
				
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(46, TestBase.getSum());
			assertEquals("12222222222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testTFlowZample6() throws InterruptedException {

		FwFlow<TestContext> wkf = TestFlows.zample6(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(34, TestBase.getSum());
			assertEquals("12222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testTFlowZample5() throws InterruptedException {

		FwFlow<TestContext> wkf = TestFlows.zample5(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(22, TestBase.getSum());
			assertEquals("12222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testTFlowZample4() throws InterruptedException {
		
		FwFlow<TestContext> wkf = TestFlows.zample4(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(22, TestBase.getSum());
			assertEquals("12222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testTFlowZample3() throws InterruptedException {
		
		FwFlow<TestContext> wkf = TestFlows.zample3(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(17, TestBase.getSum());
			assertEquals("2311145", TestBase.getConcat());
		}
	}
	
	
	@Test
	public void testTFlowZample2() throws InterruptedException {

		FwFlow<TestContext> wkf = TestFlows.zample2(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(5, TestBase.getSum());
			assertEquals("11111", TestBase.getConcat());
		}
	}
	
	@Test
	public void testTFlowZample1() throws InterruptedException {

		FwFlow<TestContext> wkf = TestFlows.zample1(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.start(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(15, TestBase.getSum());
			assertEquals("12345", TestBase.getConcat());
		}
	}

	/*
	 * Initialize 
	 */
	static public FwHighOrder<TestContext> sequential;
	static public FwHighOrder<TestContext> parallel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestBase.MAX_THREADS = Runtime.getRuntime().availableProcessors(); // minimal amount of threads for the test case is 1 !!!
		TestBase.setUpBeforeClass();
		sequential = FwHighOrder.sequential(executor);
		parallel = FwHighOrder.parallel(executor);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		if( MAX_ITERATION == 1) {
			System.out.println(TestBase.getTrace());
		}
		
	}
}
