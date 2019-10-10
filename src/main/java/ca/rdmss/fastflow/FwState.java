package ca.rdmss.fastflow;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class FwState {

	private final AtomicBoolean isDebug = new AtomicBoolean(false);
	private final AtomicBoolean isRunning = new AtomicBoolean(true);

	private final Supplier<Boolean> stop;
	
	public FwState(Supplier<Boolean> stop, boolean isDebug) {
		this.stop = stop;
		this.isDebug.set(isDebug);
	}

	public void flowStop() {
		isRunning.set(false);
	}

	public boolean isDebug() {
		return isDebug.get();
	}

	public boolean isFlowRunning() {
		return isRunning.get();
	}

	public boolean isFlowStopRequested() {
		return stop.get();
	}

	public void untilFinish() {
		try {
			while( isRunning.get() ){
				TimeUnit.MICROSECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
