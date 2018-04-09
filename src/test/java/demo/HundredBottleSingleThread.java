package demo;

import ca.rdmss.fastflow.FastFlow;
import ca.rdmss.fastflow.FwFlow;

public class HundredBottleSingleThread {

	public static void main(String[] args) throws InterruptedException {
		
		FastFlow<String> ff = new FastFlow<String>(1);
		
		FwFlow<String> hundredBottleFlow = ff.sequential.combine(
				HundredBottleFlow.getInstance(ff.sequential, ff.parallel)
			);
		
		hundredBottleFlow.run("=== the end === just "+ff.getThreadPoolSize()+" thread perfomed!");
		
		ff.shutdown();
		
		System.out.println("Scheduled "+ff.getExecutor().getTaskCount()+" tasks");
		System.out.println("Completed "+ff.getExecutor().getCompletedTaskCount()+" tasks");
		System.out.println("Max wait "+ff.getExecutor().getLargestPoolSize()+" tasks");
		System.out.println("Max pool size "+ff.getExecutor().getMaximumPoolSize());
	}

}
