package demo;

import ca.rdmss.fastflow.FastFlow;
import ca.rdmss.fastflow.FwFlow;

public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		
		FastFlow<Object> ff = new FastFlow<Object>();
		
		FwFlow<Object> hello = ff.sequential.combine(
				(a,b,c,d)->System.out.print("Hello"),
				(a,b,c,d)->System.out.print(","),
				(a,b,c,d)->System.out.print("World"),
				ff.parallel.combine(
						(a,b,c,d)->System.out.print("!"),
						(a,b,c,d)->System.out.print("!")
				),
				(a,b,c,d)->System.out.println("")
			);
		
		hello.start(null);
		
		ff.shutdown();
	}

}
