package ca.rdmss.fastflow.impl;

import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.fastflow.FwState;
import ca.rdmss.fastflow.FwTask;

public class FwCycle<T> {

	private FwFlow<T> subflow;
	@SuppressWarnings("unused")
	private final FwState state;
	
	public FwCycle() {
		this(null);
	}
	
	public FwCycle(FwState state) {
		this.state = state;
	}
	
	public FwTask<T> doCycle(FwFlow<T> subflow) {
		this.subflow = subflow;
		return (ctx,st)->{
			st.startCycle();
			subflow.start(ctx,st);
		};
	}

	public FwFlow<T> untill() {
		return (FwTask<T>)(ctx,st)->{
			if( st.isFlowStopRequested() ) {
				st.stopCycle();
			} else if( st.isCycle() ) {
				subflow.start(ctx,st);
			}
		};
	}

	@Override
	public int hashCode() {
		return -1;
	}
	
	

}
