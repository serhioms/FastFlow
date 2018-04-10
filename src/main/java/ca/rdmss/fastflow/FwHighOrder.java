package ca.rdmss.fastflow;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * High-order flow interface. You can build your flow vie sequential parallel and asynchronous interfaces.
 *  
 * (c) Hereby granted by Federico Peralta https://github.com/boundsofjava/boj-newsletter-001/blob/master/LICENSE
 */
@FunctionalInterface
public interface FwHighOrder<T> {
	
	static public final Package THIS_PACKAGE = FwHighOrder.class.getPackage();
	
	/*
	 * Combines given WTask<?> into single one
	 */
	public FwFlow<T> combine(FwFlow<?>... tasks);

	/*
	 * Sequential interface implements full-blocked, automatic XPDL activities executed sequentially. 
	 * 
	 * No thread blocking synchronization! 
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
	 * Parallel interface implements full-blocked, automatic XPDL activities executed in parallel. 
	 *
	 * No thread locking synchronization! 
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
	 * Asynchronous interface implements non-blocked, automatic XPDL activities executed in parallel. 
	 *
	 * No synchronization at all! 
	 *
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