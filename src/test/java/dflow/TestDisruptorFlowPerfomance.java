package dflow;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import ca.rdmss.dflow.DisruptorFlow;
import ca.rdmss.dflow.Task;
import ca.rdmss.multitest.annotation.MultiBefore;
import ca.rdmss.multitest.annotation.MultiEndOfSet;
import ca.rdmss.multitest.annotation.MultiTest;
import ca.rdmss.multitest.annotation.MultiThread;
import ca.rdmss.multitest.junitrule.MultiTestRule;
import fastflow.impl.TestContext;
import perfomance.CompareDisruptorPerfomance;
import perfomance.wkf.TestWorkflowDLike;

@MultiTest(repeatNo=CompareDisruptorPerfomance.MAX_TRY, threadSet=CompareDisruptorPerfomance.PUBLISHER_THREADS)
public class TestDisruptorFlowPerfomance {

	static DisruptorFlow<TestContext> dflow = null;
	static Task<TestContext>[] wkf = null;
	
	@BeforeClass
	public static void runBeforeClass() throws Exception {
		dflow = new DisruptorFlow<TestContext>();
		wkf = TestWorkflowDLike.zampleDFlow(dflow);
	}

	@AfterClass
	public static void runAfterClass() throws Exception {
		dflow = null;
	}
	
	AtomicInteger expected = new AtomicInteger(0);
	TestContext context = new TestContext();
	
	@Rule
	public MultiTestRule rule = new MultiTestRule(this);

	@MultiBefore
	public void setUp() throws Exception {
		dflow.start();
	}
	
	
	@MultiThread
	public void producer(){
		dflow.process(context, wkf);
	}

	@MultiEndOfSet
	public void endOfSet(){
		dflow.stop();
		
		/* N producers -> 1 consumer*/
		expected.addAndGet(CompareDisruptorPerfomance.MAX_TRY*rule.getThreadNo()*1*TestWorkflowDLike.NUMBER_OF_TASK_IN_FLOW);
		
		dflow.start();
	}

	@Test
	public void test() throws Throwable {

		dflow.stop();

		System.out.printf("%s\n", rule.getReport());
		System.out.printf("Expected counter %d vs %d actual\n", expected.get(), context.actual.get());
		assertEquals("Check log", expected.get(), context.actual.get());
	}
}
