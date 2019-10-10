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
	default void start(T context, FwState state) {
		doNotUseMeDirectlyPlease(context, state, 0, 0);
    }

	default void start(T context) {
		doNotUseMeDirectlyPlease(context, new FwState(()->false, false), 0, 0);
    }

	void doNotUseMeDirectlyPlease(T context, FwState state, int index, int skip, FwFlow<?>... next);
}
