package fastflow.impl;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Simple flow context - takes unique identifier 
 */
public class TestContext {

	public final int ident;
	public final AtomicInteger actual = new AtomicInteger(0);

	public TestContext(int ident) {
		this.ident = ident;
	}

	public TestContext() {
		this.ident = 0;
	}

	@Override
	public String toString() {
		return Integer.toString(ident);
	}
}
