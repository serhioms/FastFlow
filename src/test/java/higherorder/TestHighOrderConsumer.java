package higherorder;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;

import higherorder.impl.HigherOrderConsumer;
import higherorder.impl.TestFlowsHighOrder;
import fastflow.impl.TestContext;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import test.impl.TestBase;

public class TestHighOrderConsumer extends TestBase {

	@Test
	public void testConsumerZample32() throws InterruptedException {
		
		Consumer<TestContext> wkf = TestFlowsHighOrder.zample32(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals("231114511167", TestBase.getConcat());
			assertEquals(33, TestBase.getSum());
		}
	}

	@Test
	public void testConsumerZample62() throws InterruptedException {

		Consumer<TestContext> wkf = TestFlowsHighOrder.zample62(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(34, TestBase.getSum());
			assertEquals("12222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testConsumerZample8() throws InterruptedException {

		Consumer<TestContext> wkf = TestFlowsHighOrder.zample8(sequential, parallel);

		for(int i=1; i<=MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(58, TestBase.getSum());
			assertEquals("12222222222222222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testConsumerZample7() throws InterruptedException {

		Consumer<TestContext> wkf = TestFlowsHighOrder.zample7(sequential, parallel);
				
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(46, TestBase.getSum());
			assertEquals("12222222222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testConsumerZample6() throws InterruptedException {

		Consumer<TestContext> wkf = TestFlowsHighOrder.zample6(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(34, TestBase.getSum());
			assertEquals("12222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testConsumerZample5() throws InterruptedException {

		Consumer<TestContext> wkf = TestFlowsHighOrder.zample5(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(22, TestBase.getSum());
			assertEquals("12222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testConsumerZample4() throws InterruptedException {
		
		Consumer<TestContext> wkf = TestFlowsHighOrder.zample4(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(22, TestBase.getSum());
			assertEquals("12222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testConsumerZample3() throws InterruptedException {
		
		Consumer<TestContext> wkf = TestFlowsHighOrder.zample3(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(17, TestBase.getSum());
			assertEquals("2311145", TestBase.getConcat());
		}
	}
	
	@Test
	public void testConsumerZample2() throws InterruptedException {

		Consumer<TestContext> wkf = TestFlowsHighOrder.zample2(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(5, TestBase.getSum());
			assertEquals("11111", TestBase.getConcat());
		}
	}
	
	@Test
	public void testConsumerZample1() throws InterruptedException {

		Consumer<TestContext> wkf = TestFlowsHighOrder.zample1(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(15, TestBase.getSum());
			assertEquals("12345", TestBase.getConcat());
		}
	}

	@Test
	public void testConsumerZample() throws InterruptedException {

		Consumer<TestContext> wkf = TestFlowsHighOrder.zampleConsumers(sequential, parallel);
		
		for(int i=1; i<=1; i++) {
			TestBase.init();
			wkf.accept(new TestContext(i));
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(15, TestBase.getSum());
			assertEquals("12345", TestBase.getConcat());
		}
	}

	/*
	 * Initialize 
	 */
	static public HigherOrderConsumer<TestContext> sequential;
	static public HigherOrderConsumer<TestContext> parallel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestBase.MAX_THREADS = 7; /* minimal amount of threads for the test case !!!*/
		TestBase.setUpBeforeClass();
		sequential = HigherOrderConsumer.sequential();
		parallel = HigherOrderConsumer.parallel(executor);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		if( MAX_ITERATION == 1) {
			System.out.println(TestBase.getTrace());
		}
		
	}
}
