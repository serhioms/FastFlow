package demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import test.fastflow.higherorder.HigherOrderConsumer;

public class HundredBottleMultyThreadBlocking {

	public static void main(String[] args) throws InterruptedException {
		
		final ThreadPoolExecutor executor = (ThreadPoolExecutor )Executors.newFixedThreadPool(99);
		
		HigherOrderConsumer<String> sequential = HigherOrderConsumer.sequential();
		HigherOrderConsumer<String> parallel = HigherOrderConsumer.parallel(executor);
		
		@SuppressWarnings("unchecked")
		Consumer<String> hundredBottleFlow = sequential.combine(HundredBottleFlow.getInstance(sequential, parallel));

		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				try {
					int abortedTasks = executor.getQueue().size();
					if( !executor.getQueue().isEmpty() || executor.getActiveCount() > 0 ){
						for(executor.shutdown(); !executor.awaitTermination(100L, TimeUnit.MILLISECONDS); ){
							if( !executor.shutdownNow().isEmpty() ){
								System.out.println("=== hangs!!! ===");
							}
						}
					}
					
					
					System.out.println("Scheduled "+executor.getTaskCount()+" tasks");
					System.out.println("Completed "+executor.getCompletedTaskCount()+" tasks");
					System.out.println("Aborted "+abortedTasks+" tasks");
					System.out.println("Max wait "+executor.getLargestPoolSize()+" tasks");
					System.out.println("Thread pool size "+executor.getMaximumPoolSize());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		hundredBottleFlow.accept(null);
	}

}
