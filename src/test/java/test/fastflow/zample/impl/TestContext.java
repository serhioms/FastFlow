package test.fastflow.zample.impl;

/*
 * Simple workflow context - takes unique identifier 
 */
public class TestContext {

	public final int ident;

	public TestContext(int ident) {
		this.ident = ident;
	}

	@Override
	public String toString() {
		return Integer.toString(ident);
	}
}
