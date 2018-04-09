package ca.rdmss.fastflow;

/*
 * WFlow interface wrap out whole workflow instance. 
 */
@FunctionalInterface
public interface FwFlow<T> {

	/*
	 * The only applicable method for given workflow instance
	 *
	 * @T context - workflow context provided to any workflow task  
	 */
	default void run(T context) {
		doNotUseMeDirectlyPlease(context, 0, 0);
    }

	void doNotUseMeDirectlyPlease(T context, int index, int skip, FwFlow<?>... next);
}
