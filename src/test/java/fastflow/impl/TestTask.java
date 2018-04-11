package fastflow.impl;

import java.util.function.Consumer;

import ca.rdmss.fastflow.FwTask;
import test.TestBase;

public enum TestTask implements Runnable, Consumer<TestContext>, FwTask<TestContext> {
	Job0(0), Job1(1), Job2(2), Job3(3), Job4(4), Job5(5), Job6(6), Job7(7), Job8(8), Job9(9), Job10(10), Job11(11), Job12(12), 
	
	theEnd(-1); // Job end finalize test calculations only - not recognized by workflow flow engine at all.

	final public int ident;

	private TestTask(int ident) {
		this.ident = ident;
	}

	@Override
	public void run() {
		TestBase.work(-1, this.ident, this+"\n");
	}

	@Override
	public void accept(TestContext context) {
		if( context == null ) {
			TestBase.work(-1, this.ident, this+"\n");
		} else {
			TestBase.work(context==null?-1:Integer.parseInt(context.toString()), this.ident, this+"\n");
		}
	}

	@Override
	public void job(TestContext context) {
		if( !context.isPerfomance ) {
			TestBase.work(context==null?-1:Integer.parseInt(context.toString()), this.ident, this+"\n");
		} else if(ident == -1) {
			TestBase.stop();
		}
	}
}
