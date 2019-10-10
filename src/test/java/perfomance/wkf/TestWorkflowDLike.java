package perfomance.wkf;

import java.util.function.Consumer;

import ca.rdmss.dflow.DisruptorFlow;
import ca.rdmss.dflow.Task;
import ca.rdmss.dflow.TaskFlow;
import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.fastflow.FwHighOrder;
import dflow.impl.AsyncTask;
import dflow.impl.SyncTask;
import fastflow.impl.TestContext;
import fastflow.impl.TestTask;
import higherorder.impl.HigherOrderConsumer;

public class TestWorkflowDLike {

	static public int NUMBER_OF_TASK_IN_FLOW = 6; 
			
	public static Task<TestContext>[] zampleDFlow(DisruptorFlow<TestContext> dflow) {
		return dflow.createFlow(
				new SyncTask(1),
				new TaskFlow<TestContext>(
						new SyncTask(3),
						new AsyncTask(4),
						new SyncTask(5)),
				new SyncTask(5),
				new SyncTask(-1)
			);
	}

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zampleHoFlow(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> asynchronous) {
		return sequential.combine(
				TestTask.Job1::job,
				sequential.combine(
						TestTask.Job2::job,
						asynchronous.combine(TestTask.Job3::job),
						TestTask.Job4::job
						),
				TestTask.Job5::job,
				TestTask.theEnd::job 
		);
	}


	static public <T extends TestContext> FwFlow<T> zampleFastFlow(FwHighOrder<T> sequential, FwHighOrder<T> asynchronous) {
		return sequential.combine(
				TestTask.Job1,
				sequential.combine(
						TestTask.Job2,
						asynchronous.combine(TestTask.Job3),
						TestTask.Job4
						),
				TestTask.Job5,
				TestTask.theEnd 
		);
	}

}
