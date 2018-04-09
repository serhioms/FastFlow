package ca.rdmss.fastflow;

/*
 * Any task managed by WFlow must implement this interface or use WAdapter instead.
 */
@FunctionalInterface
public interface FwTask<T> extends FwFlow<T> {

	/*
	 * Task implementation method provide workflow context from given run  
	 */
	void job(T context);

	
	default public void doNotUseMeDirectlyPlease(T context, int index, int skip, FwFlow<?>... next) {
		job(context);
	}
}
