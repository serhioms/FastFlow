# Fast Flow
Simple, light and very fast workflow engine which perform sequential, parallel and asynchronous tasks in thread pool executor. Where workflow is bunch of tasks and forks.

## What is not implemented?
There is no joins in fast flow but forks only. There is no conditional transitions but unconditional only. 

## What is special?
It is multi-threaded and very fast! Almost same fast as [LMax Disruptor](https://martinfowler.com/articles/lmax.html) because of non blocking thread synchronization. Actually internal aka [Runnable](https://github.com/boundsofjava/boj-newsletter-001/blob/master/src/main/java/com/boundsofjava/newsletter/higherorderrunnable/HigherOrderRunnable.java) [engine of Fast Flow project](https://github.com/serhioms/FastFlow/blob/master/src/main/java/ca/rdmss/fastflow/FwHighOrder.java) was inspired by great article of Federico Peralta ["The bounds of java"](http://boundsofjava.com/newsletter/001-higher-order-runnables).

Since [LMax Disruptor](https://github.com/LMAX-Exchange/disruptor) get announced by [LMax](https://www.lmax.com/) multi-threaded programming become a downtrend way of doing high performance programming. Lets get it back! We are leaving in the world of multi-core CPU! By the way nothing can stop you from using thread pool executor with one thread only...

## Big idea
The Big Idea is switching from MVC toward MVW where classic controller done vie workflow! There are lots of advantages from this approach like easy parallel programming (as simple as sequential), controller logic visualization (like any workflow does), task unification etc. Lets get out from hard-coded controller in your next application. 

## Sequential execution
Sequential execution starts by executing the first task and continue by executing next task in sequence. Usually it requires one thread only. But fast flow executes them most likely in separate threads with non-blocking synchronization in between. It starts given task first then as soon as task ends execute next one from the sequence in another thread... 

![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/sequential.png)

## Parallel execution
For given number of parallel tasks we need same amount of threads to run them all in parallel aka simultaneously or concurrently. Depends mostly on how many CPU cores are in place. Summarize all that pros and cons fast flow sets size of thread pool exactly same as number of CPU cores. Then place all parallel tasks into executor's queue. As soon as last task ends fast flow executes next one from the parent flow without blocking parent thread at all!

![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/parallel.png)

If you are single thread adherent or LMax Disruptor follower just set up thread pool having one thread only. Fast flow guarantees all work be done regardless complexity of task composition in your flow.

## [Hello World demo](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HelloWorld.java)
Here is most simple way to try fast flow:

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

The log is:

		Hello,FastFlow!!

Bit verbose lambda (a,b,c,d)->{} is the price for non-blocking synchronization implemented in fast flow. None of args are really matter except first one A - it is the context object provided in hello.start(null) method i.e. null.

Hello flow combines 4 sequential tasks and 2 parallel tasks which can be represented by 2 level flow tree. Next example of slightly modified famous [99 Bottle song](https://en.wikipedia.org/wiki/99_Bottles_of_Beer) much more complicated and finally can be represented by [100 level tree of sequential->parrallel->sequential->parallel->*** ](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HundredBottleFlow.java) flow tree.

## [100 Bottle demo](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HundredBottleFlow.java)
There are 3 implementations of this demo: vie [blocking synchronization with 99 available threads](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HundredBottleMultyThreadBlocking.java), vie [non-blocking synchronization with couple threads](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HundredBottleMultyThread.java) and vie [non-blocking synchronization with 1 thread only](https://github.com/serhioms/FastFlow/blob/master/src/test/java/demo/HundredBottleSingleThread.java).

As expected blocking implementation get hangs on the last bottle because it requires minimum 100 threads in the executor's pool to be available!

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

Non-blocking implementations works fine on any pool with greater then 0 available threads.

Here is 8-threads fast flow log:

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

Here is 1-thread fast flow log:
	
	***
	We've taken them down and passed them around; now we're drunk and passed out!
	
	Scheduled 702 tasks
	Completed 702 tasks
	Max wait 1 tasks
	Max pool size 1

## [Flow context](https://github.com/serhioms/FastFlow/blob/master/src/test/java/fastflow/impl/TestContext.java)
Flow context is any java object provided in flow run method which propagates among all tasks. 
Here is another hello flow with HelloContext:

	public class HelloTask implements FwTask<HelloContext> {
	
		/*
		 * Flow context represents bunch of properties of running flow 
		 */
		static public class HelloContext {
			public AtomicInteger counter = new AtomicInteger(0); 
		}
	
		public String phrase; 
		public HelloTask(String phrase) {
			this.phrase = phrase;
		}
	
		/*
		 * Flow task implementation has just A parameter - flow context
		 */
		@Override
		public void job(HelloContext context) {
			System.out.printf("%d) %s\n", context.counter.incrementAndGet(), phrase);
		}
	
		
		public static void main(String[] args) throws InterruptedException {
			
			FastFlow<HelloContext> ff = new FastFlow<HelloContext>();
			
			FwFlow<HelloContext> hello = ff.sequential.combine(
					new HelloTask("Hello"),
					new HelloTask(","),
					new HelloTask("World"),
					ff.parallel.combine(
							new HelloTask("!"),
							new HelloTask("!")
					),
					new HelloTask("")
				);
			
			hello.start(new HelloContext());
			
			ff.shutdown();
		}
	
	}

Here is the log:

	1) Hello
	2) ,
	3) World
	4) !
	5) !
	6) 

### [Compare Perfomance](https://github.com/serhioms/FastFlow/blob/master/src/test/java/perfomance/CompareFastFlowPerfomance.java): [FastFlow-no blocking](https://github.com/serhioms/FastFlow/blob/master/src/test/java/fastflow/TestFastFlowPerfomance.java) vs [High Order with thread blocking](https://github.com/serhioms/FastFlow/blob/master/src/test/java/higherorder/TestHighOrderPerfomance.java)

Lets run [these flows](https://github.com/serhioms/FastFlow/blob/master/src/test/java/perfomance/PerfomanceFFlows.java) 2,000 times:

| Publisher(s) | FastFlow<br/>#8 threads | HighOrder (blocking)<br/>#60 threads |
| --- | --- | --- |
| 1 thread   |   3 mks |  40 mls |
| 2 threads  |   8 mks |  80 mls |
| 4 threads  |  12 mks | 140 mls |
| 8 threads  | 100 mks | 100 mls |

Is not it that fast! Fast flow without thread blocking 1000'th time faster then thread blocking algo. Meanwhile thread blocking algo require 60 threads in the pool minimum otherwise it will hang with 8 publishers!

### Fast Flow Perfomance

First of all perfomance calculated as = Log10( 1/duration ) where duration is the time of execution [this workflow](https://github.com/serhioms/FastFlow/blob/master/src/test/java/perfomance/PerfomanceFFlows.java). Actual time is vary from 500 nanoseconds to 100 milliseconds per flow by the way. Test is done on Intel(R) 8 core CPU i7-4770 @3.4 GHz. 
![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/FastFlowPerfomance.png)

So far the absolute perfomance winner is 1 thread in executor's thread pool per 1 flow publisher. Flow publisher executes the same workflow 40,000 times. If amount of publishers grow up then amount of executed workflows grow up then perfomance getting down obviously. But still highest one achived by 1 thread in the pool. That is why [LMax Desruptor](https://meterpreter.org/lmax-disruptor-3-3-7-release-high-performance-inter-thread-messaging-library/) absolute highest perfomance pattern! While increasing thread pool size up to 8 threads perfomance getting down to its minimum around 4-8 threads. If you continue increasing pool size then perfomance getting local maximum around 8-32 threads. Over 32 threads we can consider perfomance as a constant. 

Why so? Why we still trying parallel programming? How to calculate optimal amount of threads in executor's pool for specific CPU and for certain amount of clients aka publishers? [Can answer here...](https://ca.linkedin.com/in/sergeymoskovskiy)

## Asynchronous execution
Along with sequential and parallel task executors there is one more - ***asynchronous***. Works exactly same way as parallel but without any thread synchronization at all - parent task start and forget all asynchronous children. 

Actually composition of sequential and asynchronous tasks running on 2 threads only equivalent to [Disruptor Flow](https://github.com/serhioms/DisruptorFlow) from my github. Lets compare their performance for [the same flow](https://github.com/serhioms/FastFlow/blob/master/src/test/java/perfomance/PerfomanceDFlows.java) running 200,000 times:

| Publisher(s),<br/>mks | DisruptorFlow<br/>#2 threads | FastFlow<br/>#1 thread | FastFlow<br/>#2 threads| FastFlow<br/>#8 threads | HighOrder (blocking)<br/>#2 threads |
| --- | --- | --- | --- | --- | --- |
| 1 thread   |  0.1 | 0.5 | 0.7 |  1 |  0.5 |
| 2 threads  |  0.3 |  3  |  4  |  4 |  3   |
| 3 threads  |  0.5 |  4  |  5  |  6 |  4   |
| 4 threads  |  0.7 |  5  |  6  |  7 |  5   |
| 8 threads  |  3   | 14  | 14  | 15 | 11   |
| 16 threads | 10   | 30  | 28  | 35 | 20   |

So far flow based on LMax Disruptor 3-5 times faster then Fast Flow or High Order implementation. More over High Order implementation slightly faster then Fast Flow!? Why so? There is a reason behind - there is no any blocking synchronization in [this flow](https://github.com/serhioms/FastFlow/blob/master/src/test/java/perfomance/PerfomanceDFlows.java) because no parallel task in it! 

## Usage
Since fast flow is not published in any maven repository you can [download latest jar](https://github.com/serhioms/FastFlow/tree/master/distribution/fast-flow-8.0.1.jar) or use source code as is.

License is [MIT](https://github.com/serhioms/FastFlow/blob/master/LICENSE)
