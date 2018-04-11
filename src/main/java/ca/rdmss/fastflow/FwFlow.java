package ca.rdmss.fastflow;

/*
 *Represents fast flow instance 
 */
@FunctionalInterface
public interface FwFlow<T> {

	/*
	 * The only applicable method for given flow instance
	 * 
	 * @T context - flow context provided to any flow task  
	 */
	default void start(T context) {
		doNotUseMeDirectlyPlease(context, 0, 0);
    }

	void doNotUseMeDirectlyPlease(T context, int index, int skip, FwFlow<?>... next);
}
