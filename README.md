# Fast Flow
Simple, light and very fast workflow engine which perform sequential, parallel and asynchronous tasks in thread pool executor. Where workflow is bunch of tasks and forks.

This is a regular Maven project.

## What is not implemented?
There is no joins in fast flow but forks only. There is no conditional transitions but unconditional only. 

## What is special?
It is multi-threaded and very fast! Almost same fast as [LMax Disruptor](https://martinfowler.com/articles/lmax.html) because of non blocking thread synchronization. It makes it twice faster then original blocking algorithm presented by [Federico Peralta high-order runnables](https://github.com/boundsofjava/boj-newsletter-001/blob/master/src/main/java/com/boundsofjava/newsletter/higherorderrunnable/HigherOrderRunnable.java) in his great article ["The bounds of java"](http://boundsofjava.com/newsletter/001-higher-order-runnables). 

Since [LMax Disruptor](https://github.com/LMAX-Exchange/disruptor) get announced by [LMax](https://www.lmax.com/) multi-threaded programming become a downtrend way of doing high performance programming. Lets get it back! We are leaving in the world of multi-core CPU! By the way nothing can stop you from using thread pool executor with one thread only...

Another feature is generic workflow context which represents task's data.

## Big idea
The Big Idea is switching from MVC toward MVW where classic controller done vie workflow! There are lots of advantages from this approach like easy parallel programming (as simple as sequential), controller logic visualization (like any workflow does), task unification. Lets get out of hard-coded controller in your next application. 

## Sequential execution
Sequential execution start by executing the first task and continue by executing next task in sequence. Usually it requires one thread only. But fast flow executes them most likely in separate threads with non-blocking synchronization in between. Start given task first then as soon as task ends execute next one from the sequence in another thread from the pool. 

![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/sequential.png)

## Parallel execution
For given number of parallel tasks we need same amount of threads to run them all in parallel aka simultaneously or concurrently. Depends mostly on how many CPU cores are in place. Summarize all that pros and cons fast flow sets size of thread pool exactly same as number of CPU cores. Then queues all parallel tasks into executor's queue. As soon as last task ends fast flow executes next one from parent flow without blocking parent thread at all.

If you are single thread adherent or LMax Disruptor follower just set up thread pool having one thread only. Fast flow guarantees all work be done regardless complexity of task composition in your flow.

![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/parallel.png)

## Asynchronous execution
Works exactly same way as parallel execution but without any thread synchronization between parent and children at all - starts them all and forget. 

Actually composition of sequential and asynchronous task in your workflow running with thread pool size equals 2 almost equivalent to [Disruptor Flow](https://github.com/serhioms/DisruptorFlow) from my github. Lets compare their performance:

<???>

## [Hello World demo](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HelloFastFlow.java)

## [100 Bottle demo](https://en.wikipedia.org/wiki/99_Bottles_of_Beer)
There are 3 implementations of this demo: [vie blocking synchronization with 99 available threads](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HundredBottleMultyThreadBlocking.java), vie [non-blocking synchronization with couple threads](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HundredBottleMultyThread.java) and vie [non-blocking synchronization with 1 thread only](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HundredBottleSingleThread.java).

As expected blocking implementation get hangs on the last bottle!

	100 bottles of beer on the wall, 100 bottles of beer.
	Take one down, pass it around, la, lA, La, LA, 99 bottles of beer on the wall, 99 bottles of beer.
	***
	Take one down, pass it around, la, lA, La, LA, 1 bottles of beer on the wall, 1 bottles of beer.
	Take one down, pass it around, === hangs!!! ===
	
	Scheduled 495 tasks
	Completed 495 tasks
	Aborted 4 tasks
	Max wait 99 tasks
	Thread pool size 99

Non-blocking implementations both 1 thread only and couple threads works fine till the end.

	100 bottles of beer on the wall, 100 bottles of beer.
	Take one down, pass it around, la, lA, La, LA, 99 bottles of beer on the wall, 99 bottles of beer.
	***
	Take one down, pass it around, lA, la, LA, La, 1 bottles of beer on the wall, 1 bottles of beer.
	Take one down, pass it around, la, La, lA, LA, No more bottles of beer on the wall, no more bottles of beer.
	We've taken them down and passed them around; now we're drunk and passed out!
	
	Scheduled 702 tasks
	Completed 702 tasks
	Max wait 8 tasks
	Max pool size 8

Here is single threaded log
	
	***
	Scheduled 702 tasks
	Completed 702 tasks
	Max wait 1 tasks
	Max pool size 1

## Exception handling

Exception Handler is generic class which fast flow call in case of any exception during the run. Default handler just print stack trace into System.err:

	    fastflow.setExceptionHandler(new ExceptionHandler<TestContext>(){
	        @Override
	        public TaskTransition handleTaskException((task, ex)->{
	            System.err.printf("Task %s get failed %s\n", task, ex.getMessage());
	            return TaskTransition.ContinueExecution;
	        }
	    });

## Workflow context

Flow context is java object. Must be provided in flow run method. The context will be propagated among all executed tasks.

    public class TheContext {
    	***
    }
    flow.run(new TheContext());

## Usage
Since fast flow is not published to maven repository you can [download latest jar](https://github.com/serhioms/DisruptorFlow/blob/master/distribution/fastflow-1.0.0.jar) and include it in your project. Otherwise try source code.


### Test Suit

<???>
