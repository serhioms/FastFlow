package ca.rdmss.fastflow;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import ca.rdmss.fastflow.impl.FwCycle;
/*
 * High-order flow interface. You can build your flow vie sequential parallel and asynchronous interfaces.
 *  
 * (c) Hereby granted by Federico Peralta https://github.com/boundsofjava/boj-newsletter-001/blob/master/LICENSE
 */
@FunctionalInterface
public interface FwHighOrder<T> {
	
	static public final Package THIS_PACKAGE = FwHighOrder.class.getPackage();
	static public final Package CYCLE_PACKAGE = FwCycle.class.getPackage();
	static public final Consumer<FwError<?>> defErrorHandler = (FwError<?> err)->{
		if( err != null ) {
			if( err.context != null ) {
				System.out.println("context"+err.context);
			}
			System.out.println("index"+err.index);
			System.out.println("skip"+err.skip);
			if( err.next != null ) {
				System.out.println("next.length"+err.next.length);
			}
			if( err.e != null ) {
				err.e.printStackTrace();
			}
		}
	};
	
	/*
	 * Combines given FwFlow<?> into single one
	 */
	public FwFlow<T> combine(FwFlow<?>... tasks);

	/*
	 * Sequential interface implements full-blocked, automatic XPDL activities executed sequentially. 
	 * 
	 * No thread blocking synchronization! 
	 */
	static <T> FwHighOrder<T> sequential(ExecutorService executor) { return sequential(executor, defErrorHandler);}
	@SuppressWarnings("unchecked")
	static <T> FwHighOrder<T> sequential(ExecutorService executor, Consumer<FwError<?>> errorHandler) {
		 return tasks -> (context, state, index, skip, next) -> {
			 try {
				//System.out.printf("seq: %b task=%d.%d, next=%d.%d, cycle=%b\n", state==null?false:state.isFlowRunning(), tasks.length, 1+index, skip, next==null?0:next.length, state==null?false:state.isCycle());
				if( state == null || state.isFlowRunning() ) {
				 	if( index < tasks.length ) {
				 		FwFlow<T> task = (FwFlow<T> )tasks[index];
						if( task.getClass().getPackage() == THIS_PACKAGE ) {
							task.doNotUseMeDirectlyPlease(context, state, 0, 0, (a,b,c,d,e)->{
								if( 1+index < tasks.length || next.length > 0) {
									sequential(executor, errorHandler).combine(tasks).doNotUseMeDirectlyPlease(context, state, 1+index, skip, next);
								}
							}); 
						} else if( task.getClass().getPackage() == CYCLE_PACKAGE ) {
							task.doNotUseMeDirectlyPlease(context, state, 0, 0); 
							if( !state.isCycle() ) {
								if( 1+index < tasks.length || next.length > 0 ) {
									executor.execute(() -> {
										sequential(executor, errorHandler).combine(tasks).doNotUseMeDirectlyPlease(context, state, 1+index, skip, next);
									});
								}
							}
						} else {
							task.doNotUseMeDirectlyPlease(context, state, 0 ,0);
							if( 1+index < tasks.length || next.length > 0 ) {
								executor.execute(() -> {
									sequential(executor, errorHandler).combine(tasks).doNotUseMeDirectlyPlease(context, state, 1+index, skip, next);
								});
							}
						}
				 	} else if( next.length > 0 ){
						sequential(executor, errorHandler).combine(next).doNotUseMeDirectlyPlease(context, state, skip, 0);
				 	}
				}
			 } catch(Exception e) {
				 errorHandler.accept(new FwError<>(e, context, state, index, skip, next));
			 }
		 };
    }

	/*
	 * Parallel interface implements full-blocked, automatic XPDL activities executed in parallel. 
	 *
	 * No thread locking synchronization! 
	 */
	static <T> FwHighOrder<T> parallel(ExecutorService executor) { return parallel(executor, defErrorHandler);}
	@SuppressWarnings("unchecked")
	static <T> FwHighOrder<T> parallel(ExecutorService executor, Consumer<FwError<?>> errorHandler) {
        return tasks -> (context, state, index, skip, next) -> {
        	try {
    			//System.out.printf("seq: %b task=%d.%d, next=%d.%d, cycle=%b\n", state==null?false:state.isFlowRunning(), tasks.length, 1+index, skip, next==null?0:next.length, state==null?false:state.isCycle());
				if( state == null || state.isFlowRunning() ) {
		        	final AtomicInteger latch = new AtomicInteger(tasks.length);
					Arrays.stream(tasks).forEach( t -> executor.execute(() -> {
				 		FwFlow<T> task = (FwFlow<T> )t;
						if( task.getClass().getPackage() == THIS_PACKAGE ) {
							((FwFlow<T> )task).doNotUseMeDirectlyPlease(context, state, 0, 0, (a,b,c,d,e)->{
								if( latch.decrementAndGet() == 0 ) {
									if( next.length > 0 ){
										sequential(executor, errorHandler).combine(next).doNotUseMeDirectlyPlease(context, state, skip, 0);
									}
								}
							});
						} else {
							((FwFlow<T> )task).doNotUseMeDirectlyPlease(context, state, 0, 0);
							if( latch.decrementAndGet() == 0 ) {
								if( next.length > 0 ){
									sequential(executor, errorHandler).combine(next).doNotUseMeDirectlyPlease(context, state, skip, 0);
								}
							}
						}
					}));
		        }
			 } catch(Exception e) {
				 errorHandler.accept(new FwError<>(e, context, state, index, skip, next));
			 }
        };
    }

	/*
	 * Asynchronous interface implements non-blocked, automatic XPDL activities executed in parallel. 
	 *
	 * No synchronization at all! 
	 *
	 */
	static <T> FwHighOrder<T> asynchronous(ExecutorService executor) { return asynchronous(executor, defErrorHandler);}
	@SuppressWarnings("unchecked")
	static <T> FwHighOrder<T> asynchronous(ExecutorService executor, Consumer<FwError<?>> errorHandler) {
        return tasks -> (context, state, index, skip, next) -> {
        	try {
				if( state == null || state.isFlowRunning() ) {
					Arrays.stream(tasks).forEach(task -> executor.execute(() -> ((FwFlow<T> )task).doNotUseMeDirectlyPlease(context, state, 0,0)));
					if( next.length > 0 ){
						sequential(executor, errorHandler).combine(next).doNotUseMeDirectlyPlease(context, state, skip, 0);
					}
				}
			 } catch(Exception e) {
				 errorHandler.accept(new FwError<>(e, context, state, index, skip, next));
			 }
        };
    }
}