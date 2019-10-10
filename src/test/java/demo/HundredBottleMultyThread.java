package demo;

import ca.rdmss.fastflow.FastFlow;
import ca.rdmss.fastflow.FwFlow;

public class HundredBottleMultyThread {

	public static void main(String[] args) throws InterruptedException {
		
		FastFlow<String> ff = new FastFlow<String>();
		
		FwFlow<String> hundredBottleFlow = ff.sequential.combine(
				HundredBottleFlow.getInstance(ff.sequential, ff.parallel)
			);
		
		hundredBottleFlow.start(null,null);
		
		ff.shutdown();
		
		System.out.println("Scheduled "+ff.threadPool.getTaskCount()+" tasks");
		System.out.println("Completed "+ff.threadPool.getCompletedTaskCount()+" tasks");
		System.out.println("Max wait "+ff.threadPool.getLargestPoolSize()+" tasks");
		System.out.println("Max pool size "+ff.threadPool.getMaximumPoolSize());
	}

}
