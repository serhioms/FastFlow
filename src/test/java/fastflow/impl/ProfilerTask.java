package fastflow.impl;

import ca.rdmss.fastflow.FwState;
import ca.rdmss.fastflow.FwTask;
import test.impl.TestBase;

public enum ProfilerTask implements FwTask<TestContext> {
	Job0(0), Job1(1), Job2(2), Job3(3), Job4(4), Job5(5), Job6(6), Job7(7), Job8(8), Job9(9), Job10(10), Job11(11), Job12(12), 
	
	theEnd(-1); /* Job end finalize test calculations only - not recognized by workflow flow engine at all.*/

	final public int ident;

	private ProfilerTask(int ident) {
		this.ident = ident;
	}

	@Override
	public void job(TestContext context, FwState state) {
		long start = System.currentTimeMillis();
		context.actual.incrementAndGet();
		if( context.ident > 0 ) {
			TestBase.work(context==null?-1:Integer.parseInt(context.toString()), this.ident, this+"\n");
		} else if(ident == -1) {
			TestBase.stop();
		}
		TestBase.countTasks.incrementAndGet();
		TestBase.countThreads();
		TestBase.countTime.addAndGet(System.currentTimeMillis()-start);
	}
}
