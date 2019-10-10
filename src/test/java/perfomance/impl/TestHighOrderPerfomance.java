package perfomance.impl;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import ca.rdmss.multitest.annotation.MultiEndOfSet;
import ca.rdmss.multitest.annotation.MultiTest;
import ca.rdmss.multitest.annotation.MultiThread;
import ca.rdmss.multitest.junitrule.MultiTestRule;
import fastflow.impl.TestContext;
import higherorder.impl.HigherOrderConsumer;
import perfomance.CompareHighOrderPerfomancePerfomance;
import perfomance.wkf.TestWorkflowFLike;

@MultiTest(repeatNo=CompareHighOrderPerfomancePerfomance.MAX_TRY, threadSet=CompareHighOrderPerfomancePerfomance.PUBLISHER_THREADS)
public class TestHighOrderPerfomance {

	static ThreadPoolExecutor executor;
	static Consumer<TestContext> wkf;

	@BeforeClass
	public static void runBeforeClass() throws Exception {

		executor = (ThreadPoolExecutor )Executors.newFixedThreadPool(64);
		
		HigherOrderConsumer<TestContext> sequential = HigherOrderConsumer.sequential();
		HigherOrderConsumer<TestContext> parallel = HigherOrderConsumer.parallel(executor);
		
		wkf = TestWorkflowFLike.complexFlow(sequential, parallel);
	}

	@AfterClass
	public static void runAfterClass() throws Exception {
		executor.shutdown();
		
		System.out.println("Scheduled "+executor.getTaskCount()+" tasks");
		System.out.println("Completed "+executor.getCompletedTaskCount()+" tasks");
		System.out.println("Max wait "+executor.getLargestPoolSize()+" tasks");
		System.out.println("Max pool size "+executor.getMaximumPoolSize());
	}

	AtomicInteger expected = new AtomicInteger(0);
	TestContext context = new TestContext();
	
	@Rule
	public MultiTestRule rule = new MultiTestRule(this);

	@MultiThread
	public void thread() throws InterruptedException {
		wkf.accept(context);
	}
	
	@MultiEndOfSet
	public void endOfSet() throws InterruptedException {
		while(!executor.getQueue().isEmpty() || executor.getActiveCount() > 0) {
			TimeUnit.NANOSECONDS.sleep(1);
		}
		expected.addAndGet(CompareHighOrderPerfomancePerfomance.MAX_TRY*rule.getThreadNo()*TestWorkflowFLike.NUMBER_OF_TASK_IN_FLOW);
	}

	@Test
	public void test() throws Throwable {
		System.out.printf("%s\n", rule.getReport());
		System.out.printf("Expected counter %d vs %d actual\n", expected.get(), context.actual.get());
		assertEquals("Check log", expected.get(), context.actual.get());
	}
}
