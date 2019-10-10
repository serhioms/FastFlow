package ca.rdmss.fastflow;

import java.util.function.Consumer;
import java.util.Arrays;

/*
 * Convert Runnable, Consumer to WTask<> and back and force
 */
public interface FwAdapter {

    static FwTask<?> frRunnable(Runnable... runnables) {
        return (ctx,st)->{
        	Arrays.stream(runnables).forEach(Runnable::run);
        };
    }

    static <T> Runnable toRunnable(FwTask<?>... tasks) {
        return ()->Arrays.stream(tasks).forEach((task)->task.job(null,null));
    }

    @SafeVarargs
	static <T> FwTask<T> frConsumer(Consumer<T>... consumers) {
        return (ctx,st)->{
        	Arrays.stream(consumers).forEach((consumer)->consumer.accept(ctx));
        };
    }

    @SafeVarargs
	static <T> Consumer<T> toConsumer(FwTask<T>... tasks) {
        return (context)->Arrays.stream(tasks).forEach((task)->task.job(context, null));
    }

}
