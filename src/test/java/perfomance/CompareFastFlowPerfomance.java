package perfomance;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fastflow.TestFastFlowPerfomance;
import higherorder.TestHighOrderPerfomance;

@RunWith(Suite.class)
@SuiteClasses({TestHighOrderPerfomance.class, TestFastFlowPerfomance.class})
public class CompareFastFlowPerfomance {

	public final static int MAX_TRY = 2_000;
	public final static String THREAD_SET = "8,4,2,1";
	public final static boolean PERFOMANCE = true;

}

/*

=== TestHighOrderPerfomance done 2,000 time(s) ===
Threads Total      OneTry     OneTry(mls)
------- ---------- ---------- ----------
8       199.8  sec 99.9   mls     99.914
4       273.9  sec 137.0  mls    136.969
2       162.6  sec 81.3   mls     81.282
1       73.0   sec 36.5   mls     36.503
------- ---------- ---------- ----------
Expected counter 30000 vs 30000 actual
Scheduled 630000 tasks
Completed 630000 tasks
Max wait 64 tasks
Max pool size 64

=== TestFastFlowPerfomance done 2,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
8       222.0  mls 111.0  mks    111.000
4       25.0   mls 12.5   mks     12.500
2       17.0   mls 8.5    mks      8.500
1       6.0    mls 3.0    mks      3.000
------- ---------- ---------- ----------
Expected counter 30000 vs 30000 actual
Scheduled 1080000 tasks
Completed 1080000 tasks
Max wait 8 tasks
Max pool size 8

*/