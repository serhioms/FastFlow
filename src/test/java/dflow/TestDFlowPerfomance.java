package dflow;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import ca.rdmss.dflow.DisruptorFlow;
import ca.rdmss.dflow.Task;
import ca.rdmss.dflow.TaskFlow;
import ca.rdmss.multitest.annotation.MultiBefore;
import ca.rdmss.multitest.annotation.MultiEndOfSet;
import ca.rdmss.multitest.annotation.MultiTest;
import ca.rdmss.multitest.annotation.MultiThread;
import ca.rdmss.multitest.junitrule.MultiTestRule;
import dflow.impl.AsyncTask;
import dflow.impl.SyncTask;
import fastflow.impl.TestContext;
import test.ComparePerfomance;

@MultiTest(repeatNo=ComparePerfomance.MAX_TRY, threadSet=ComparePerfomance.THREAD_SET)
public class TestDFlowPerfomance {

	static DisruptorFlow<TestContext> dflow = null;
	static Task<TestContext>[] wkf = null;
	
	@BeforeClass
	public static void runBeforeClass() throws Exception {
		dflow = new DisruptorFlow<TestContext>();
		wkf = dflow.createFlow(
			new SyncTask(1),
			new TaskFlow<TestContext>(
					new SyncTask(3),
					new AsyncTask(4),
					new SyncTask(5)),
			new SyncTask(5),
			new SyncTask(-1)
		);
	}

	@AfterClass
	public static void runAfterClass() throws Exception {
		dflow = null;
	}
	
	AtomicInteger actual = new AtomicInteger(0); 
	AtomicInteger expected = new AtomicInteger(0);
	
	@Rule
	public MultiTestRule rule = new MultiTestRule(this);

	@MultiBefore
	public void setUp() throws Exception {
		dflow.start();
	}
	
	@MultiThread
	public void producer(){
		dflow.process(new TestContext(actual.incrementAndGet(), ComparePerfomance.PERFOMANCE), wkf);
	}

	@MultiEndOfSet
	public void endOfSet(){
		dflow.stop();
		
		// N producers -> 1 consumer
		expected.addAndGet(ComparePerfomance.MAX_TRY*rule.getThreadNo()*1);
		
		dflow.start();
	}

	@Test
	public void test() throws Throwable {

		dflow.stop();

		System.out.printf("%s\n", rule.getReport());
		System.out.printf("Expected counter %d vs %d actual\n", expected.get(), actual.get());
		assertEquals("Check log", expected.get(), actual.get());
	}
}
