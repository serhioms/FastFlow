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

	private static FwFlow<String> createRecursively(FwHighOrder<String> sequential, FwHighOrder<String> parallel, AtomicInteger n) {
		return n.get() == 1?
			sequential.combine(
				(a,b,c,d)->System.out.println("No more bottles of beer on the wall, no more bottles of beer."),
				(a,b,c,d)->System.out.println("We've taken them down and passed them around; now we're drunk and passed out!")
			):
			sequential.combine(
				(a,b,c,d)->System.out.printf("%d bottles of beer on the wall, %d bottles of beer.\n", n.get(), n.get()),
				(a,b,c,d)->System.out.print("Take one down, pass it around, "),
				parallel.combine(
						(a,b,c,d)->System.out.print("la, "),
						(a,b,c,d)->System.out.print("lA, "),
						(a,b,c,d)->System.out.print("La, "),
						(a,b,c,d)->System.out.print("LA, ")
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
					(a)->System.out.println("No more bottles of beer on the wall, no more bottles of beer."),
					(a)->System.out.println("We've taken them down and passed them around; now we're drunk and passed out!")
				):
			sequential.combine(
				(a)->System.out.printf("%d bottles of beer on the wall, %d bottles of beer.\n", n.get(), n.get()),
				(a)->System.out.print("Take one down, pass it around, "),
				parallel.combine(
					(a)->System.out.print("la, "),
					(a)->System.out.print("lA, "),
					(a)->System.out.print("La, "),
					(a)->System.out.print("LA, ")
				),
				parallel.combine(createRecursively(sequential, parallel, new AtomicInteger(n.decrementAndGet())))
			);
	}
}
