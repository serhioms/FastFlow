package ca.rdmss.fastflow;

public class FwError<T> {
	public final T context;
	public final FwState state;
	public final int index;
	public final int skip;
	public final FwFlow<?>[] next;
	public final Exception e;

	public FwError(Exception e, T context, FwState state, int index, int skip, FwFlow<?>[] next) {
		this.context = context;
		this.state = state;
		this.index = index;
		this.skip = skip;
		this.next = next;
		this.e = e;
	}
}
