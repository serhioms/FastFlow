package ca.rdmss.fastflow;

/*
 *Represents fast flow instance 
 */
@FunctionalInterface
public interface FwFlow<T> {

	public static final FwState DEFAULT_STATE = new FwState(()->false, false);
	
	/*
	 * The only applicable method for given flow instance
	 * 
	 * @T context - flow context provided to any flow task  
	 */
	default void start(T context, FwState state) {
		doNotUseMeDirectlyPlease(context, state, 0, 0);
    }

	default void start(T context) {
		doNotUseMeDirectlyPlease(context, DEFAULT_STATE, 0, 0);
    }

	void doNotUseMeDirectlyPlease(T context, FwState state, int index, int skip, FwFlow<?>... next);
}
