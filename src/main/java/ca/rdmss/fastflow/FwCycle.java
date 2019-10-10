package ca.rdmss.fastflow;

public class FwCycle<T> {

	private FwFlow<T> subflow;
	
	public FwFlow<T> doCycle(FwFlow<T> subflow) {
		this.subflow = subflow;
		return subflow;
	}

	public FwFlow<T> untill() {
		return (FwTask<T>)(ctx,st)->{
			subflow.start(ctx,st);
		};
	}

}
