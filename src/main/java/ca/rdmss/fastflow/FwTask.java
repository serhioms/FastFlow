package ca.rdmss.fastflow;

/*
 * Represents fast flow task instance 
 */
@FunctionalInterface
public interface FwTask<T> extends FwFlow<T> {
	
	/*
	 * The only applicable method for given task instance
	 * 
	 * @T context - flow context provided by flow start method  
	 */
	void job(T context, FwState state);

	default void doNotUseMeDirectlyPlease(T context, FwState state, int index, int skip, FwFlow<?>... next) {
		job(context, state);
	}
}
