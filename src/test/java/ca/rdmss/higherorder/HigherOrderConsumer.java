package ca.rdmss.higherorder;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/*
 * Higher-order consumer applies to Consumer<T> interface which accept's only one parameter <T> - job context.
 */
@FunctionalInterface
public interface HigherOrderConsumer<T> {

	/*
	 * Combines given consumers into single one.
	 */
	@SuppressWarnings("unchecked")
	Consumer<T> combine(Consumer<? super T>... consumers);

	/*
	 * Same blocking synchronization as high-order runnable...
	 */
	@SuppressWarnings("unchecked")
	static <T> HigherOrderConsumer<T> sequential() {
        return consumers -> (context) -> Arrays.stream(consumers).forEach(consumer->((Consumer<T>)consumer).accept(context));
    }

	/*
	 * Same blocking synchronization as high-order runnable...
	 */
	@SuppressWarnings("unchecked")
	static <T> HigherOrderConsumer<T> parallel(ExecutorService executor) {
        return consumers -> (context) -> {
            try {
                CountDownLatch latch = new CountDownLatch(consumers.length);
                Arrays.stream(consumers).forEach(consumer -> executor.submit(() -> {
                    try {
                    	((Consumer<T>)consumer).accept(context);
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
}