package test.fastflow;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.boundsofjava.newsletter.higherorderrunnable.HigherOrderRunnable;

import test.fastflow.zample.RunnableZamples;
import test.impl.TestBase;

public class TestHighOrderRunnable extends TestBase {

	@Test
	public void testRunnableZample32() throws InterruptedException {
		
		Runnable wkf = RunnableZamples.zample32(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals("231114511167", TestBase.getConcat());
			assertEquals(33, TestBase.getSum());
		}
	}

	@Test
	public void testRunnableZample62() throws InterruptedException {

		Runnable wkf = RunnableZamples.zample6(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(34, TestBase.getSum());
			assertEquals("12222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample8() throws InterruptedException {

		Runnable wkf = RunnableZamples.zample8(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(58, TestBase.getSum());
			assertEquals("12222222222222222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample7() throws InterruptedException {

		Runnable wkf = RunnableZamples.zample7(sequential, parallel);
				
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(46, TestBase.getSum());
			assertEquals("12222222222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample6() throws InterruptedException {

		Runnable wkf = RunnableZamples.zample6(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(34, TestBase.getSum());
			assertEquals("12222222222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample5() throws InterruptedException {

		Runnable wkf = RunnableZamples.zample5(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(22, TestBase.getSum());
			assertEquals("12222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample4() throws InterruptedException {
		
		Runnable wkf = RunnableZamples.zample4(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(22, TestBase.getSum());
			assertEquals("12222222223", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample3() throws InterruptedException {
		
		Runnable wkf = RunnableZamples.zample3(sequential, parallel);

		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(17, TestBase.getSum());
			assertEquals("2311145", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample2() throws InterruptedException {

		Runnable wkf = RunnableZamples.zample2(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(5, TestBase.getSum());
			assertEquals("11111", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample1() throws InterruptedException {

		Runnable wkf = RunnableZamples.zample1(sequential, parallel);
		
		for(int i=0; i<MAX_ITERATION; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(15, TestBase.getSum());
			assertEquals("12345", TestBase.getConcat());
		}
	}
	
	@Test
	public void testRunnableZample() throws InterruptedException {

		Runnable wkf = RunnableZamples.zampleRunnables(sequential, parallel);
		
		for(int i=0; i<1; i++) {
			TestBase.init();
			wkf.run();
			TestBase.done();
			avgTimeMls += TestBase.getTimeMls();
			assertEquals(15, TestBase.getSum());
			assertEquals("12345", TestBase.getConcat());
		}
	}
	

	/*
	 * Initialize 
	 */
	static public HigherOrderRunnable sequential;
	static public HigherOrderRunnable parallel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestBase.MAX_THREADS = 7; // minimal amount of threads for the test case !!!
		TestBase.setUpBeforeClass();
		sequential = HigherOrderRunnable.sequential();
		parallel = HigherOrderRunnable.parallel(executor);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		if( MAX_ITERATION == 1) {
			System.out.println(TestBase.getTrace());
		}
		
	}
}
