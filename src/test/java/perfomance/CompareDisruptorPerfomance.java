package perfomance;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dflow.TestDFlowPerf;
import fastflow.TestFFPerf1DFlowLike;
import fastflow.TestFFPerf2DFlowLike;
import fastflow.TestFFPerfNDFlowLike;
import higherorder.TestHOPerf2DFlowLike;

@RunWith(Suite.class)
@SuiteClasses({TestDFlowPerf.class, TestHOPerf2DFlowLike.class, TestFFPerf1DFlowLike.class, TestFFPerf2DFlowLike.class, TestFFPerfNDFlowLike.class})
public class CompareDisruptorPerfomance {

	public final static int MAX_TRY = 200_000;
	public final static String THREAD_SET = "1,2,3,4,8,16";
	public final static boolean PERFOMANCE = true;;

}

/*
=== TestDFlowPerfomance done 200,000 time(s) ===
Threads Total      OneTry     OneTry(ns)
------- ---------- ---------- ----------
1       96.0   mls 480.0   ns    480.000
2       92.0   mls 460.0   ns    460.000
3       88.0   mls 440.0   ns    440.000
4       103.0  mls 515.0   ns    515.000
8       591.0  mls 3.0    mks   2955.000
16      2.0    sec 10.0   mks  10045.000
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual

=== TestHighOrderPerfomance2 done 200,000 time(s) ===
Threads Total      OneTry     OneTry(ns)
------- ---------- ---------- ----------
1       131.0  mls 655.0   ns    655.000
2       715.0  mls 3.6    mks   3575.000
3       853.0  mls 4.3    mks   4265.000
4       1.0    sec 5.0    mks   5000.000
8       2.3    sec 11.6   mks  11640.000
16      4.0    sec 20.2   mks  20150.000
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual
Scheduled 6800000 tasks
Completed 6800000 tasks
Max wait 2 tasks
Max pool size 2

=== TestFastFlowPerfomance1 done 200,000 time(s) ===
Threads Total      OneTry     OneTry(ns)
------- ---------- ---------- ----------
1       94.0   mls 470.0   ns    470.000
2       732.0  mls 3.7    mks   3660.000
3       844.0  mls 4.2    mks   4220.000
4       852.0  mls 4.3    mks   4260.000
8       2.9    sec 14.3   mks  14315.000
16      6.0    sec 30.2   mks  30185.000
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual
Scheduled 34000000 tasks
Completed 34000000 tasks
Max wait 1 tasks
Max pool size 1

=== TestFastFlowPerfomance2 done 200,000 time(s) ===
Threads Total      OneTry     OneTry(ns)
------- ---------- ---------- ----------
1       133.0  mls 665.0   ns    665.000
2       694.0  mls 3.5    mks   3470.000
3       978.0  mls 4.9    mks   4890.000
4       1.1    sec 5.3    mks   5275.000
8       2.8    sec 13.9   mks  13925.000
16      5.7    sec 28.4   mks  28405.000
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual
Scheduled 34000000 tasks
Completed 34000000 tasks
Max wait 2 tasks
Max pool size 2

=== TestFastFlowPerfomanceN done 200,000 time(s) ===
Threads Total      OneTry     OneTry(ns)
------- ---------- ---------- ----------
1       184.0  mls 920.0   ns    920.000
2       700.0  mls 3.5    mks   3500.000
3       1.0    sec 5.2    mks   5155.000
4       1.3    sec 6.7    mks   6725.000
8       3.0    sec 15.1   mks  15115.000
16      7.2    sec 35.8   mks  35755.000
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual
Scheduled 34000000 tasks
Completed 34000000 tasks
Max wait 8 tasks
Max pool size 8
*/