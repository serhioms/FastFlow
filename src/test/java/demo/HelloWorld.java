package demo;

import ca.rdmss.fastflow.FastFlow;
import ca.rdmss.fastflow.FwFlow;

public class HelloWorld {

	public static void main(String[] args) throws InterruptedException {
		
		FastFlow<Object> ff = new FastFlow<Object>();
		
		FwFlow<Object> hello = ff.sequential.combine(
				(a,b,c,d,e)->Systemoutprint("Hello"),
				(a,b,c,d,e)->Systemoutprint(","),
				(a,b,c,d,e)->Systemoutprint("World"),
				ff.parallel.combine(
						(a,b,c,d,e)->Systemoutprint("!"),
						(a,b,c,d,e)->Systemoutprint("!")
				),
				(a,b,c,d,e)->Systemoutprint("")
			);
		
		hello.start(null);
		
		ff.shutdown();
	}

	static FwFlow<Object> Systemoutprint(String s){
		System.out.print("!");
		return null;
	}
	
}
