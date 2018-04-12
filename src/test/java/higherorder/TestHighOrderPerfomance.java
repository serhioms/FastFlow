package higherorder;

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
import perfomance.CompareDisruptorPerfomance;
import perfomance.CompareFastFlowPerfomance;
import perfomance.PerfomanceFFlows;

@MultiTest(repeatNo=CompareFastFlowPerfomance.MAX_TRY, threadSet=CompareFastFlowPerfomance.THREAD_SET)
public class TestHighOrderPerfomance {

	static ThreadPoolExecutor executor;
	static Consumer<TestContext> wkf;

	@BeforeClass
	public static void runBeforeClass() throws Exception {

		executor = (ThreadPoolExecutor )Executors.newFixedThreadPool(60);
		
		HigherOrderConsumer<TestContext> sequential = HigherOrderConsumer.sequential();
		HigherOrderConsumer<TestContext> parallel = HigherOrderConsumer.parallel(executor);
		
		wkf = PerfomanceFFlows.complexFlow(sequential, parallel);
	}

	@AfterClass
	public static void runAfterClass() throws Exception {
		executor.shutdown();
		
		System.out.println("Scheduled "+executor.getTaskCount()+" tasks");
		System.out.println("Completed "+executor.getCompletedTaskCount()+" tasks");
		System.out.println("Max wait "+executor.getLargestPoolSize()+" tasks");
		System.out.println("Max pool size "+executor.getMaximumPoolSize());
	}

	AtomicInteger actual = new AtomicInteger(0); 
	AtomicInteger expected = new AtomicInteger(0);
	
	@Rule
	public MultiTestRule rule = new MultiTestRule(this);

	@MultiThread
	public void thread() throws InterruptedException {
		wkf.accept(new TestContext(actual.incrementAndGet(), CompareDisruptorPerfomance.PERFOMANCE));
	}
	
	@MultiEndOfSet
	public void endOfSet() throws InterruptedException {
		while(!executor.getQueue().isEmpty() || executor.getActiveCount() > 0) {
			TimeUnit.NANOSECONDS.sleep(1);
		}
		expected.addAndGet(CompareFastFlowPerfomance.MAX_TRY*rule.getThreadNo());
	}

	@Test
	public void test() throws Throwable {
		System.out.printf("%s\n", rule.getReport());
		System.out.printf("Expected counter %d vs %d actual\n", expected.get(), actual.get());
		assertEquals("Check log", expected.get(), actual.get());
	}
}
