package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dflow.TestDFlowPerfomance;
import fastflow.TestFastFlowPerfomance1;
import fastflow.TestFastFlowPerfomance2;
import fastflow.TestFastFlowPerfomanceN;
import higherorder.TestHighOrderPerfomance2;

@RunWith(Suite.class)
@SuiteClasses({TestDFlowPerfomance.class, TestHighOrderPerfomance2.class, TestFastFlowPerfomance1.class, TestFastFlowPerfomance2.class, TestFastFlowPerfomanceN.class})
public class ComparePerfomance {

	public final static int MAX_TRY = 20_000;
	public final static String THREAD_SET = "1,2,3,4,8,16";
	public final static boolean PERFOMANCE = true;;

}

/*

=== TestHighOrderPerfomanceN done 200,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
1       1.5    sec 7.4    mks      7.405
2       2.0    sec 10.1   mks     10.085
3       2.3    sec 11.3   mks     11.275
4       2.4    sec 12.2   mks     12.245
8       3.3    sec 16.4   mks     16.380
16      5.7    sec 28.4   mks     28.365
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual
Scheduled 6800000 tasks
Completed 6800000 tasks
Max wait 8 tasks
Max pool size 8

=== TestFastFlowPerfomanceN done 200,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
1       306.0  mls 1.5    mks      1.530
2       823.0  mls 4.1    mks      4.115
3       1.2    sec 6.0    mks      6.000
4       1.5    sec 7.3    mks      7.295
8       3.6    sec 17.8   mks     17.755
16      8.1    sec 40.6   mks     40.590
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual
Scheduled 34000000 tasks
Completed 34000000 tasks
Max wait 8 tasks
Max pool size 8

=== TestFastFlowPerfomance2 done 200,000 time(s) ===
Threads Total      OneTry     OneTry(ns)
------- ---------- ---------- ----------
1       138.0  mls 690.0   ns    690.000
2       691.0  mls 3.5    mks   3455.000
3       817.0  mls 4.1    mks   4085.000
4       1.2    sec 6.2    mks   6245.000
8       2.8    sec 14.2   mks  14165.000
16      6.0    sec 30.0   mks  29970.000
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual
Scheduled 34000000 tasks
Completed 34000000 tasks
Max wait 2 tasks
Max pool size 2

=== TestFastFlowPerfomance1 done 200,000 time(s) ===
Threads Total      OneTry     OneTry(ns)
------- ---------- ---------- ----------
1       75.0   mls 375.0   ns    375.000
2       734.0  mls 3.7    mks   3670.000
3       698.0  mls 3.5    mks   3490.000
4       745.0  mls 3.7    mks   3725.000
8       2.6    sec 13.1   mks  13100.000
16      4.9    sec 24.5   mks  24530.000
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual
Scheduled 34000000 tasks
Completed 34000000 tasks
Max wait 1 tasks
Max pool size 1

=== TestDFlowPerfomance done 200,000 time(s) ===
Threads Total      OneTry     OneTry(ns)
------- ---------- ---------- ----------
1       26.0   mls 130.0   ns    130.000
2       61.0   mls 305.0   ns    305.000
3       97.0   mls 485.0   ns    485.000
4       105.0  mls 525.0   ns    525.000
8       538.0  mls 2.7    mks   2690.000
16      1.7    sec 8.7    mks   8715.000
------- ---------- ---------- ----------
Expected counter 6800000 vs 6800000 actual

*/