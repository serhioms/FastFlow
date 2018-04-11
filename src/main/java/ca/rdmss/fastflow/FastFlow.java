package ca.rdmss.fastflow;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FastFlow<T> {

	static public final long SHUTDOWN_TIMEOUT_MLS = 100L;
	static public final int OPTIMAL_THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

	public final FwHighOrder<T> sequential;
	public final FwHighOrder<T> parallel;
	public final FwHighOrder<T> asynchronous;
	public final ThreadPoolExecutor executor;
	
	private long shutdownTimeoutMls = SHUTDOWN_TIMEOUT_MLS;
	
	public FastFlow() {
		this(OPTIMAL_THREAD_POOL_SIZE);
	} 

	public FastFlow(ThreadPoolExecutor executor) {
		this.executor = executor;
		this.sequential = FwHighOrder.sequential(executor);
		this.parallel = FwHighOrder.parallel(executor);
		asynchronous = FwHighOrder.asynchronous(executor);
	} 

	public FastFlow(int nThreads) {
		this.executor = (ThreadPoolExecutor )Executors.newFixedThreadPool(nThreads);
		this.sequential = FwHighOrder.sequential(executor);
		this.parallel = FwHighOrder.parallel(executor);
		asynchronous = FwHighOrder.asynchronous(executor);
	}

	public void waitForRunning() throws InterruptedException {
		while(!executor.getQueue().isEmpty() || executor.getActiveCount() > 0) {
			TimeUnit.NANOSECONDS.sleep(1);
		}
	}
	
	public boolean isRunning() {
		return !executor.getQueue().isEmpty() || executor.getActiveCount() > 0;
	}
	
	public void shutdown() throws InterruptedException {
		do {
			TimeUnit.MILLISECONDS.sleep(shutdownTimeoutMls);
		} while( isRunning() );
		for(executor.shutdown(); !executor.awaitTermination(shutdownTimeoutMls, TimeUnit.MILLISECONDS); );
	}

	public long getShutdownTimeoutMls() {
		return shutdownTimeoutMls;
	}

	public void setShutdownTimeoutMls(long shutdownTimeoutMls) {
		this.shutdownTimeoutMls = shutdownTimeoutMls;
	}
	
	public int getThreadPoolSize(){
		return executor.getCorePoolSize();
	}

	public ThreadPoolExecutor getExecutor() {
		return executor;
	}
}
