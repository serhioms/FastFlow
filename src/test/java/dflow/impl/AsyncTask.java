package dflow.impl;

import ca.rdmss.dflow.TaskAsync;
import ca.rdmss.dflow.TaskTransition;
import fastflow.impl.TestContext;
import test.impl.TestBase;

public class AsyncTask extends TaskAsync<TestContext> {

	final public int ident;

	public AsyncTask(int ident) {
		this.ident = ident;
	}

	@Override
	public TaskTransition execute(TestContext context) throws Throwable {
		context.actual.incrementAndGet();
		if( context.ident > 0 ) {
			TestBase.work(context==null?-1:Integer.parseInt(context.toString()), this.ident, this+"\n");
		}
		return TaskTransition.Next;
	}
	
}
