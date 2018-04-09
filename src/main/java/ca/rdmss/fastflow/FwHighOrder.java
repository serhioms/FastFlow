package ca.rdmss.fastflow;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * High-order workflow manager applies to WTask<T> interface which accept's only one parameter <T> - job context.
 *  
 * Actually you've to use sequential, parallel or asynchronous interfaces to build your own WFlow<T> instance. 
 */
@FunctionalInterface
public interface FwHighOrder<T> {
	
	static public final Package THIS_PACKAGE = FwHighOrder.class.getPackage();
	
	/*
	 * Combines given WTask into single one.
	 */
	public FwFlow<T> combine(FwFlow<?>... consumers);

	/*
	 * No thread locking synchronization! 
	 * 
	 * Works the same way as XPDL FULL-BLOCKED activity defined. Each task executed in separate thread but still sequentially. 
	 * Last one trigger next parent.
	 */
	@SuppressWarnings("unchecked")
	static <T> FwHighOrder<T> sequential(ExecutorService executor) {
		 return tasks -> (context, index, skip, next) -> {
			 	if( index < tasks.length ) {
			 		FwFlow<T> task = (FwFlow<T> )tasks[index];
					if( task.getClass().getPackage() == THIS_PACKAGE ) {
						task.doNotUseMeDirectlyPlease(context, 0, 0, (a,b,c,d)->{
							if( 1+index < tasks.length || next.length > 0) {
								sequential(executor).combine(tasks).doNotUseMeDirectlyPlease(context, 1+index, skip, next);
							}
						});
					} else {
						task.doNotUseMeDirectlyPlease(context,0,0);
						if( 1+index < tasks.length || next.length > 0) {
							executor.execute(() -> {
								sequential(executor).combine(tasks).doNotUseMeDirectlyPlease(context, 1+index, skip, next);
							});
						}
					}
			 	} else if( next.length > 0 ){
					sequential(executor).combine(next).doNotUseMeDirectlyPlease(context, skip, 0);
			 	}
		 };
    }

	/*
	 * No thread locking synchronization! 
	 *
	 * Works the same way as XPDL FULL-BLOCKED activity defined. All tasks performed in parallel threads.
	 * The last one and the only trigger next parent.
	 */
	@SuppressWarnings("unchecked")
	static <T> FwHighOrder<T> parallel(ExecutorService executor) {
        return tasks -> (context, index, skip, next) -> {
        	final AtomicInteger latch = new AtomicInteger(tasks.length);
			Arrays.stream(tasks).forEach( t -> executor.execute(() -> {
		 		FwFlow<T> task = (FwFlow<T> )t;
				if( task.getClass().getPackage() == THIS_PACKAGE ) {
					((FwFlow<T> )task).doNotUseMeDirectlyPlease(context, 0, 0, (a,b,c,d)->{
						if( latch.decrementAndGet() == 0) {
							if( next.length > 0 ){
								sequential(executor).combine(next).doNotUseMeDirectlyPlease(context, skip, 0);
							}
						}
					});
				} else {
					((FwFlow<T> )task).doNotUseMeDirectlyPlease(context,0,0);
					if( latch.decrementAndGet() == 0) {
						if( next.length > 0 ){
							sequential(executor).combine(next).doNotUseMeDirectlyPlease(context, skip, 0);
						}
					}
				}
			}));
        };
    }

	/*
	 * No synchronization at all! 
	 *
	 * Works the same way as XPDL NON-BLOCKED activity defined. All tasks performed in parallel threads.
	 * Next parent triggered immediately after all children get started.
	 */
	@SuppressWarnings("unchecked")
	static <T> FwHighOrder<T> asynchronous(ExecutorService executor) {
        return tasks -> (context, index, skip, next) -> {
			Arrays.stream(tasks).forEach(task -> executor.execute(() -> ((FwFlow<T> )task).doNotUseMeDirectlyPlease(context,0,0)));
			if( next.length > 0 ){
				sequential(executor).combine(next).doNotUseMeDirectlyPlease(context, skip, 0);
			}
        };
    }
}