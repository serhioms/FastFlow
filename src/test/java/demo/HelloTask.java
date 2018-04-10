package demo;

import java.util.concurrent.atomic.AtomicInteger;

import ca.rdmss.fastflow.FastFlow;
import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.fastflow.FwTask;
import demo.HelloTask.HelloContext;

public class HelloTask implements FwTask<HelloContext> {

	/*
	 * Flow context represents bunch of properties of running flow 
	 */
	static public class HelloContext {
		public AtomicInteger counter = new AtomicInteger(0); 
	}

	public String phrase; 
	
	public HelloTask(String phrase) {
		this.phrase = phrase;
	}

	/*
	 * Flow task implementation has just A parameter - flow context
	 */
	@Override
	public void job(HelloContext context) {
		System.out.printf("%d) %s\n", context.counter.incrementAndGet(), phrase);
	}

	
	public static void main(String[] args) throws InterruptedException {
		
		FastFlow<HelloContext> ff = new FastFlow<HelloContext>();
		
		FwFlow<HelloContext> hello = ff.sequential.combine(
				new HelloTask("Hello"),
				new HelloTask(","),
				new HelloTask("World"),
				ff.parallel.combine(
						new HelloTask("!"),
						new HelloTask("!")
				),
				new HelloTask("")
			);
		
		hello.start(new HelloContext());
		
		ff.shutdown();
	}

}
