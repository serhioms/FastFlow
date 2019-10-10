package fastflow;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import ca.rdmss.fastflow.FastFlow;
import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.multitest.annotation.MultiEndOfSet;
import ca.rdmss.multitest.annotation.MultiTest;
import ca.rdmss.multitest.annotation.MultiThread;
import ca.rdmss.multitest.junitrule.MultiTestRule;
import ca.rdmss.util.UtilExecutors;
import fastflow.impl.TestContext;
import perfomance.CompareFastFlowPerfomance;
import perfomance.wkf.TestWorkflowFLike;

@MultiTest(repeatNo=CompareFastFlowPerfomance.MAX_TRY, threadSet=CompareFastFlowPerfomance.PUBLISHER_THREADS)
@Ignore // newAnotherThreadPool does not work
public class TestAnotherThreadPool {

	static FastFlow<TestContext> ff;
	static FwFlow<TestContext> wkf;

	@BeforeClass
	public static void runBeforeClass() throws Exception {
		/*CompareFastFlowPerfomance.CONSUMER_POOL));*/
		ff = new FastFlow<TestContext>(UtilExecutors.newAnotherThreadPool(1)); 
		wkf = TestWorkflowFLike.complexFlow(ff.sequential, ff.parallel);
	}

	@AfterClass
	public static void runAfterClass() throws Exception {
		ff.shutdown();
		
		System.out.println("Scheduled "+ff.threadPool.getTaskCount()+" tasks");
		System.out.println("Completed "+ff.threadPool.getCompletedTaskCount()+" tasks");
		System.out.println("Max wait "+ff.threadPool.getLargestPoolSize()+" tasks");
		System.out.println("Max pool size "+ff.threadPool.getMaximumPoolSize());
	}

	AtomicInteger expected = new AtomicInteger(0);
	TestContext context = new TestContext();
	
	@Rule
	public MultiTestRule rule = new MultiTestRule(this);

	@MultiThread
	public void thread() throws InterruptedException {
		wkf.start(context,null);
	}
	
	@MultiEndOfSet
	public void endOfSet() throws InterruptedException {
		expected.addAndGet(CompareFastFlowPerfomance.MAX_TRY*rule.getThreadNo()*TestWorkflowFLike.NUMBER_OF_TASK_IN_FLOW);
		while( expected.get() > context.actual.get() ) {
			TimeUnit.NANOSECONDS.sleep(CompareFastFlowPerfomance.nanosleep);
		}
	}

	@Test
	public void test() throws Throwable {
		System.out.printf("%s\n", rule.getReport());
		System.out.printf("Expected counter %d vs %d actual\n", expected.get(), context.actual.get());

		assertEquals("Check log", expected.get(), context.actual.get());
		
	}
}
