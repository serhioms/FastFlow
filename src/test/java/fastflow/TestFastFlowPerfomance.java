package fastflow;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import ca.rdmss.fastflow.FastFlow;
import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.multitest.annotation.MultiEndOfSet;
import ca.rdmss.multitest.annotation.MultiTest;
import ca.rdmss.multitest.annotation.MultiThread;
import ca.rdmss.multitest.junitrule.MultiTestRule;
import fastflow.impl.TestContext;
import perfomance.CompareDisruptorPerfomance;
import perfomance.CompareFastFlowPerfomance;
import perfomance.PerfomanceFFlows;

@MultiTest(repeatNo=CompareFastFlowPerfomance.MAX_TRY, threadSet=CompareFastFlowPerfomance.THREAD_SET)
public class TestFastFlowPerfomance {

	static FastFlow<TestContext> ff;
	static FwFlow<TestContext> wkf;

	@BeforeClass
	public static void runBeforeClass() throws Exception {
		ff = new FastFlow<TestContext>();
		wkf = PerfomanceFFlows.complexFlow(ff.sequential, ff.parallel);
	}

	@AfterClass
	public static void runAfterClass() throws Exception {
		ff.shutdown();
		
		System.out.println("Scheduled "+ff.getExecutor().getTaskCount()+" tasks");
		System.out.println("Completed "+ff.getExecutor().getCompletedTaskCount()+" tasks");
		System.out.println("Max wait "+ff.getExecutor().getLargestPoolSize()+" tasks");
		System.out.println("Max pool size "+ff.getExecutor().getMaximumPoolSize());
	}

	AtomicInteger actual = new AtomicInteger(0); 
	AtomicInteger expected = new AtomicInteger(0);
	
	@Rule
	public MultiTestRule rule = new MultiTestRule(this);

	@MultiThread
	public void thread() throws InterruptedException {
		wkf.start(new TestContext(actual.incrementAndGet(), CompareDisruptorPerfomance.PERFOMANCE));
	}
	
	@MultiEndOfSet
	public void endOfSet() throws InterruptedException {
		ff.waitForRunning();
		expected.addAndGet(CompareFastFlowPerfomance.MAX_TRY*rule.getThreadNo());
	}

	@Test
	public void test() throws Throwable {
		System.out.printf("%s\n", rule.getReport());
		System.out.printf("Expected counter %d vs %d actual\n", expected.get(), actual.get());
		assertEquals("Check log", expected.get(), actual.get());
	}
}
