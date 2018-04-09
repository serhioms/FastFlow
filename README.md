# Fast Flow
Simple, light and very fast workflow engine which perform sequential, parallel and asynchronous tasks in thread pool executor. My workflow definition is as simple as bunch of tasks and transitions.

This is a regular Maven project.

# What is not implemented?
There is no joins in fast flow but forked transitions only. There is no conditional transition but unconditional only.

# What is special?
It is very fast! Almost same fast as [LMax Disruptor]() but totally multi-threaded. The heart is non blocking synchronization for parallel tasks execution. It makes it twice faster then original blocking algorithm demonstrated by [Federico Peralta's high-order function example]() in his great article ["The bounds of java"](https://github.com/boundsofjava/boj-newsletter-001). 

Since [LMax Disruptor]() get announced by [LMax]() multi-threaded programming become a down trend way of doing high performance programming in java. Lets get it back! We are leaving in the world of multi-core CPU!  What can stop you from using thread pool executor just with one thread? Since there is no concurrent locks in fast flow you have that flexability to make it happen.

Another good feature is flow context which is implemented vie java generics. 

# Big idea
This project is a result of great impression from ["The bounds of java"]() article written by Federico Peralta. To me that is not just a perfect example of functional programming in java. To me it is the way of implementing my BIG IDEA - MVW instead of MVC! Where classic controller must be done as multi-threaded workflow engine! Lets make parallel programming as simple as sequential. Lets get out of hard-coded controller in your application toward easy extendable and visualizable one as simple as picture of states and transitions. The only show stopper is light and fast implementation. [Federico's high-order function ]() shows exactly the way.   

## Sequential execution
From [XPDL](http://www.xpdl.org/standards/xpdl-2.2/XPDL%202.2%20(2012-02-24).pdf) prospective sequential tasks equivalent to automatic, FULL-BLOCKED sequential tasks. That is all true that sequential execution of any number of tasks requires exactly one thread. Even so fast flow sequential tasks are most likely running in separate threads! But totally synchronized without any thread blocking the way when given task ends fast flow engine queued next one from the sequence into executor's queue. 

![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/sequential.png)

## Parallel execution
From [XPDL](http://www.xpdl.org/standards/xpdl-2.2/XPDL%202.2%20(2012-02-24).pdf) prospective parallel tasks equivalent to automatic, FULL-BLOCKED and AND-FORKED transitions to number of parallel tasks. Theoretically for given number of parallel tasks we need same amount of threads to run them all in parallel simultaneously. In reality any CPU has constant  number of cores which means it is totally useless to run more parallel tasks then CPU cores. To summarize all this pros and cons fast flow engine make size of thread pool executor are exactly the same as CPU cores regardless how many parallel tasks in your workflow - some of them will run simultaneously as JVM guarantee but others will wait in executor's queue for next available thread from the pool. Anyway all parallel tasks are totally synchronized against parent which means that parent must "wait" until last child finalize its work and then and only then start next task from parent flow. Why "wait" is quoted? Because fast flow do not block parent thread but calculate number of parallel children instead! That is the way to avoid any blocking synchronization. 

If you are "single thread developer" aka LMax Disruptor follower then you can configure thread pool to have just one thread but fast flow engine guarantee all work will be done regardless any complexity of sequential-parallel-asynchronous task you have in your workflow.

![alt text](https://github.com/serhioms/FastFlow/blob/master/diagram/parallel.png)

## Asynchronous execution
From [XPDL](http://www.xpdl.org/standards/xpdl-2.2/XPDL%202.2%20(2012-02-24).pdf) prospective asynchronous tasks equivalent to NON-BLOCKED AND-FORKED transitions to number of asynchronous tasks. Looks exactly same as parallel execution but without any thread synchronization between parent and children - parent queued them all in executor queue and forget. Then fast flow engine pick up next task from parent flow and continue execution logic regardless of state of any asynchronous child.  

Actually combination of sequential tasks and asynchronous tasks in your workflow with thread pool size equals 2 equivalent to [Disruptor Flow project](https://github.com/serhioms/DisruptorFlow) presented in my github public page. Lets compare performance of same flow then:


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