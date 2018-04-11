package higherorder;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/*
 * Higher-order consumer is almost the same as higher-order runnable... 
 * 
 * (c) Hereby granted by Federico Peralta https://github.com/boundsofjava/boj-newsletter-001/blob/master/LICENSE
 */
@FunctionalInterface
public interface HigherOrderConsumer<T> {

	/*
	 * Combines given consumers into single one.
	 */
	@SuppressWarnings("unchecked")
	Consumer<T> combine(Consumer<? super T>... consumers);

	/*
	 * Sequential execution of all consumers in same thread simply do not requires any synchronization!
	 */
	@SuppressWarnings("unchecked")
	static <T> HigherOrderConsumer<T> sequential() {
        return consumers -> (context) -> Arrays.stream(consumers).forEach(consumer->((Consumer<T>)consumer).accept(context));
    }

	/*
	 * Parallel execution of all consumers require synchronization - thread blocking synchronization!
	 */
	@SuppressWarnings("unchecked")
	static <T> HigherOrderConsumer<T> parallel(ExecutorService executor) {
        return consumers -> (context) -> {
            try {
                CountDownLatch latch = new CountDownLatch(consumers.length);
                Arrays.stream(consumers).forEach(consumer -> executor.execute(() -> {
                    try {
                    	((Consumer<T>)consumer).accept(context); // provide context to consumer
                    } finally {
                        latch.countDown();
                    }
                }));
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

	/*
	 * Asynchronous execution of all consumers does not require any synchronization
	 */
	@SuppressWarnings("unchecked")
	static <T> HigherOrderConsumer<T> asynchronous(ExecutorService executor) {
        return consumers -> (context) -> {
            Arrays.stream(consumers).forEach(consumer -> executor.execute(() -> {
               	((Consumer<T>)consumer).accept(context); // provide context to consumer
            }));
        };
    }
}