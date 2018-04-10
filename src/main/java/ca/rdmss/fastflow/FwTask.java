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
	void job(T context);

	
	default public void doNotUseMeDirectlyPlease(T context, int index, int skip, FwFlow<?>... next) {
		job(context);
	}
}
