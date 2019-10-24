package ca.rdmss.fastflow;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class FwState {

	private boolean isDebug = false;
	private boolean isRunning = true;
	private boolean isCycle = false;
	private boolean isFlowStopRequested = false;

	private final Supplier<Boolean> stop;
	
	public FwState(Supplier<Boolean> stop, boolean isDebug) {
		this.stop = stop;
		this.isDebug = isDebug;
	}

	public FwState(boolean isDebug) {
		this(()->false, isDebug);
	}

	public FwState() {
		this(()->false, false);
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void stopFlow() {
		isRunning = false;
	}

	public boolean isFlowRunning() {
		return isRunning;
	}

	public void requestStopFlow() {
		isFlowStopRequested = true;
	}
	public boolean isFlowStopRequested() {
		return stop.get() || isFlowStopRequested;
	}
	
	public void untilFinish() {
		try {
			while(isRunning ){
				TimeUnit.MICROSECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void startCycle() {
		isCycle = true;
	}


	public boolean isCycle() {
		return isCycle;
	}

	public void stopCycle() {
		isCycle = false;
	}

}
