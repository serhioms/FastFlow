package ca.rdmss.fastflow;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FastFlow<T> {

	private static final int OPTIMAL_POOL_SIZE = Runtime.getRuntime().availableProcessors();
	static public final long DEF_SHUTDOWN_TIMEOUT_MLS = 100L;

	public final ThreadPoolExecutor threadPool;

	public final FwHighOrder<T> sequential;
	public final FwHighOrder<T> parallel;
	public final FwHighOrder<T> asynchronous;
	
	private long shutdownTimeoutMls = DEF_SHUTDOWN_TIMEOUT_MLS;
	
	public FastFlow(ThreadPoolExecutor executor) {
		this.threadPool = executor;
		this.sequential = FwHighOrder.sequential(executor);
		this.parallel = FwHighOrder.parallel(executor);
		this.asynchronous = FwHighOrder.asynchronous(executor);
	} 

	public FastFlow() {
		this(OPTIMAL_POOL_SIZE);
	} 

	public FastFlow(int nThreads) {
		this((ThreadPoolExecutor )Executors.newFixedThreadPool(nThreads));
	} 

	public void waitForRunning() throws InterruptedException {
		do {
			TimeUnit.MILLISECONDS.sleep(shutdownTimeoutMls);
		} while( isRunning() );
	}
	
	public boolean isRunning() {
		return !threadPool.getQueue().isEmpty() || threadPool.getActiveCount() > 0;
	}
	
	public void shutdown() throws InterruptedException {
		threadPool.getQueue().clear();
		waitForRunning();
		for(threadPool.shutdown(); !threadPool.awaitTermination(shutdownTimeoutMls, TimeUnit.MILLISECONDS); );
	}

	public void halt() throws InterruptedException {
		for(threadPool.shutdownNow(); !threadPool.awaitTermination(shutdownTimeoutMls, TimeUnit.MILLISECONDS); );
	}

	public long getShutdownTimeoutMls() {
		return shutdownTimeoutMls;
	}

	public void setShutdownTimeoutMls(long shutdownTimeoutMls) {
		this.shutdownTimeoutMls = shutdownTimeoutMls;
	}
}
