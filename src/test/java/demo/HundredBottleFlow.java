package demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.fastflow.FwHighOrder;
import higherorder.impl.HigherOrderConsumer;

public class HundredBottleFlow {

	public static FwFlow<String> getInstance(FwHighOrder<String> sequential, FwHighOrder<String> parallel) {
		return createRecursively(sequential, parallel, new AtomicInteger(101));
	}


	static FwFlow<Object> Systemoutprintln(String s){
		System.out.println("!");
		return null;
	}

	static FwFlow<Object> Systemoutprint(String s){
		System.out.print("!");
		return null;
	}

	static FwFlow<Object> Systemoutprintf(String format, Object ... args){
		System.out.printf(format, args);
		return null;
	}

	private static FwFlow<String> createRecursively(FwHighOrder<String> sequential, FwHighOrder<String> parallel, AtomicInteger n) {
		return n.get() == 1?
			sequential.combine(
				(a,b,c,d,e)->Systemoutprintln("No more bottles of beer on the wall, no more bottles of beer."),
				(a,b,c,d,e)->Systemoutprintln("We've taken them down and passed them around; now we're drunk and passed out!")
			):
			sequential.combine(
				(a,b,c,d,e)->Systemoutprintf("%d bottles of beer on the wall, %d bottles of beer.\n", n.get(), n.get()),
				(a,b,c,d,e)->Systemoutprint("Take one down, pass it around, "),
				parallel.combine(
						(a,b,c,d,e)->Systemoutprint("la, "),
						(a,b,c,d,e)->Systemoutprint("lA, "),
						(a,b,c,d,e)->Systemoutprint("La, "),
						(a,b,c,d,e)->Systemoutprint("LA, ")
				),
				parallel.combine(createRecursively(sequential, parallel, new AtomicInteger(n.decrementAndGet())))
			);
	}

	
	
	
	public static Consumer<String> getInstance(HigherOrderConsumer<String> sequential, HigherOrderConsumer<String> parallel) {
		return createRecursively(sequential, parallel, new AtomicInteger(101));
	}

	@SuppressWarnings("unchecked")
	private static Consumer<String> createRecursively(HigherOrderConsumer<String> sequential, HigherOrderConsumer<String> parallel, AtomicInteger n) {
		return n.get() == 1? 
			sequential.combine(
					(a)->Systemoutprintln("No more bottles of beer on the wall, no more bottles of beer."),
					(a)->Systemoutprintln("We've taken them down and passed them around; now we're drunk and passed out!")
				):
			sequential.combine(
				(a)->Systemoutprintf("%d bottles of beer on the wall, %d bottles of beer.\n", n.get(), n.get()),
				(a)->Systemoutprint("Take one down, pass it around, "),
				parallel.combine(
					(a)->Systemoutprint("la, "),
					(a)->Systemoutprint("lA, "),
					(a)->Systemoutprint("La, "),
					(a)->Systemoutprint("LA, ")
				),
				parallel.combine(createRecursively(sequential, parallel, new AtomicInteger(n.decrementAndGet())))
			);
	}
}
