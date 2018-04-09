package com.boundsofjava.newsletter.higherorderrunnable;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * Higher-order runnable that returns a runnable wrapping the given runnable tasks
 * in an implementation-dependant way.
 * </p>
 * <p>
 * Two standard implementations are provided as factory methods: one that runs
 * the given runnables sequentially and another one that runs them in parallel.
 * </p>
 */
@FunctionalInterface
public interface HigherOrderRunnable {

    /**
     * <p>
     * Combines the given <code>Runnable</code> tasks into a single
     * <code>Runnable</code>.
     * </p>
     *
     * @param runnables <code>Runnable</code>s to be combined
     * @return A <code>Runnable</code> that is the result of combining the given
     * <code>Runnable</code> tasks
     */
    Runnable combine(Runnable... runnables);

    /**
     * <p>
     * Factory method that creates a <code>HigherOrderRunnable</code> that will
     * execute the given <code>Runnable</code> tasks sequentially.
     * </p>
     *
     * @return A higher-order runnable that will execute <code>Runnable</code>
     * tasks sequentially
     */
    static HigherOrderRunnable sequential() {
        return runnables -> () -> Arrays.stream(runnables).forEach(Runnable::run);
    }

    /**
     * <p>
     * Factory method that creates a <code>HigherOrderRunnable</code> that will
     * execute the given <code>Runnable</code> tasks in parallel.
     * </p>
     *
     * @param executor An <code>ExecutorService</code> where <code>Runnable</code>
     *                 tasks are to be submitted to
     * @return A higher-order runnable that will execute <code>Runnable</code>s in parallel
     */
    static HigherOrderRunnable parallel(ExecutorService executor) {
        return runnables -> () -> {
            try {
                CountDownLatch latch = new CountDownLatch(runnables.length);
                Arrays.stream(runnables).forEach(task -> executor.submit(() -> {
                    try {
                        task.run();
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