package fastflow.impl;

/*
 * Simple flow context - takes unique identifier 
 */
public class TestContext {

	public final int ident;
	public final boolean isPerfomance;

	public TestContext(int ident) {
		this(ident, false);
	}

	public TestContext(int ident, boolean isPerfomance) {
		this.ident = ident;
		this.isPerfomance = isPerfomance;
	}

	@Override
	public String toString() {
		return Integer.toString(ident);
	}
}
