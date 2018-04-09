# Fast Flow
Simple, light and very fast workflow engine which perform sequential, parallel and asynchronous tasks in thread pool executor. Where workflow is bunch of tasks and transitions.

This is a regular Maven project.

## What is not implemented?
There is no joins in fast flow but forks only. There is no conditional transitions but unconditional only. 

## What is special?
It is multithreaded and very fast! Almost same fast as [LMax Disruptor](https://martinfowler.com/articles/lmax.html) because of non blocking thread synchronization. It makes it twice faster then original blocking algorithm demonstrated by [Federico Peralta high-order runnables](https://github.com/boundsofjava/boj-newsletter-001/blob/master/src/main/java/com/boundsofjava/newsletter/higherorderrunnable/HigherOrderRunnable.java) in his great article ["The bounds of java"](http://boundsofjava.com/newsletter/001-higher-order-runnables). 

Since [LMax Disruptor](https://github.com/LMAX-Exchange/disruptor) get announced by [LMax](https://www.lmax.com/) multi-threaded programming become a downtrend way of doing high performance programming in java. Lets get it back! We are leaving in the world of multi-core CPU! By the way nothing can stop you from using thread pool executor with one thread only...

Another feature is generic workflow context which represents task's data.

## Big idea
The Big Idea is switching from MVC toward MVW where classic controller done vie workflow! There are lots of advantages from this approach like easy parallel programming (as simple as sequential), controller logic visualization (like any workflow does), task unification. Lets get out of hard-coded controller in your applications. 

## Sequential execution
Sequential execution start by executing the first task and continue by executing next task in sequence. Usually it requires one thread only. But fast flow executes them most likely in separate threads with non-blocking synchronization in between. Start given task first then as soon as task ends execute next one from the sequence. 

![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/sequential.png)

## Parallel execution
For given number of parallel tasks we need same amount of threads to run them all in parallel aka simultaneously or concurrently. Depends mostly on how many CPU cores are in place. Summarize all that pros and cons fast flow sets size of thread pool executor exactly same as number of CPU cores. Then queues all parallel tasks into executor's queue. As soon as last task ends pick up next task from parent flow end execute it without blocking parent thread.

If you are single thread adherent or LMax Disruptor follower just set up thread pool having one thread only. Fast flow engine guarantees all work be done regardless complexity of tasks composition in your workflow.

![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/parallel.png)

## Asynchronous execution
Works exactly same way as parallel execution but without any thread synchronization between parent and children - starts them all and forget. 

Actually composition of sequential and asynchronous tasks in your workflow with thread pool size equals 2 preatty equivalent to [Disruptor Flow project](https://github.com/serhioms/DisruptorFlow) from my github. That is iteresting to compare performance for the same flow:

<???>

## Hello World demo

## [99 bottle demo](https://en.wikipedia.org/wiki/99_Bottles_of_Beer)

## Exception handling

Exception Handler is generic class which flow disruptor call on any exception during task execution. There is a default handler implemented as simple as System.err messenger.

	    dflow.setExceptionHandler(new ExceptionHandler<TestContext>(){
	        @Override
	        public TaskTransition handleTaskException(TestContext context, Throwable ex) {
	            System.err.printf("Flow failed due to %s\n", ex.getMessage());
	            return TaskTransition.Fail;
	        }
	    });

## Workflow context

Flow context can be any java class object. Context object must be provided once per flow run. The context will be propagated among all executed tasks.

    public class TestContext {
    	***
    }
    
    flow.run(new TestContext());


### [Full Example](https://github.com/serhioms/DisruptorFlow/blob/master/test/ca/rdmss/test/dflow/DFlowExample.java)


    
## Usage
Since fast flow is not published at any maven repository you can [download latest jar](https://github.com/serhioms/DisruptorFlow/blob/master/distribution/fastflow-1.0.0.jar) and include it in your project. Otherwise try source code.


### [Fast Flow Test Suit](https://github.com/serhioms/FastFlow/blob/master/test/ca/rdmss/test/dflow/Suite_DFlow.java)

i7-3630QM CPU@ 2.40Ghz (4 core, 8 logical processors, L1=256kb, l2=1mb, l3=6mb) -ea -Xms1g -Xmx1g

	=== Test_DFlow_Unicast done 2,000,000 time(s) ===
	Threads Total      OneTry     OneTry(ns)
	------- ---------- ---------- ----------
	1       301.0  mls 150.5   ns    150.500 pass: expected=2,000,000 actual=2,000,000
	2       807.0  mls 403.5   ns    403.500 pass: expected=4,000,000 actual=4,000,000
	3       1.5    sec 739.5   ns    739.500 pass: expected=6,000,000 actual=6,000,000
	4       3.0    sec 1.5    mks   1482.500 pass: expected=8,000,000 actual=8,000,000
	------- ---------- ---------- ----------

	=== Test_DFlow_Sync done 2,000,000 time(s) ===
	Threads Total      OneTry     OneTry(ns)
	------- ---------- ---------- ----------
	1       884.0  mls 442.0   ns    442.000 pass: expected=2,000,000 actual=2,000,000
	2       1.8    sec 899.5   ns    899.500 pass: expected=4,000,000 actual=4,000,000
	3       2.5    sec 1.3    mks   1263.500 pass: expected=6,000,000 actual=6,000,000
	4       3.4    sec 1.7    mks   1689.500 pass: expected=8,000,000 actual=8,000,000
	------- ---------- ---------- ----------

	=== Test_DFlow_Async done 2,000,000 time(s) ===
	Threads Total      OneTry     OneTry(ns)
	------- ---------- ---------- ----------
	1       1.9    sec 947.5   ns    947.500 pass: expected=2,000,000 actual=2,000,000
	2       3.4    sec 1.7    mks   1695.500 pass: expected=4,000,000 actual=4,000,000
	3       5.3    sec 2.7    mks   2670.000 pass: expected=6,000,000 actual=6,000,000
	4       7.0    sec 3.5    mks   3518.000 pass: expected=8,000,000 actual=8,000,000
	------- ---------- ---------- ----------

	=== Test_DFlow_Flow done 2,000,000 time(s) ===
	Threads Total      OneTry     OneTry(ns)
	------- ---------- ---------- ----------
	1       1.8    sec 903.5   ns    903.500 pass: expected=2,000,000 actual=2,000,000
	2       4.0    sec 2.0    mks   2005.000 pass: expected=4,000,000 actual=4,000,000
	3       5.3    sec 2.7    mks   2668.500 pass: expected=6,000,000 actual=6,000,000
	4       7.4    sec 3.7    mks   3686.500 pass: expected=8,000,000 actual=8,000,000
	------- ---------- ---------- ----------

	===Test_DFlow_FlowException done 2,000,000 time(s) in   3.4 sec (  1.7 mks/try) === 
	pass: expected=1,999,696 actual=1,999,696
	
Each test done 2 mln times with 1, 2, 3, 4 parallel threads correspondently. Last test done in 1 thread only but with couple exceptions during task execution.
