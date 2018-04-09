package demo;

import ca.rdmss.fastflow.FastFlow;
import ca.rdmss.fastflow.FwFlow;

public class HelloFastFlow {

	public static void main(String[] args) throws InterruptedException {
		
		FastFlow<String> ff = new FastFlow<String>();
		
		FwFlow<String> hello = ff.sequential.combine(
				(a,b,c,d)->System.out.print("Hello"),
				(a,b,c,d)->System.out.print(","),
				(a,b,c,d)->System.out.print("FastFlow"),
				(a,b,c,d)->System.out.println(a)
			);
		
		hello.run("!");
		
		ff.shutdown();
	}

}
