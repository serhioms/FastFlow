package perfomance;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import perfomance.impl.TestFastFlowPerfomance;
import perfomance.impl.TestHighOrderPerfomance;

@RunWith(Suite.class)
@SuiteClasses({TestHighOrderPerfomance.class, TestFastFlowPerfomance.class})
public class CompareHighOrderPerfomancePerfomance {

	public final static int MAX_TRY = 2_000;
	public final static String PUBLISHER_THREADS = "8,4,2,1";
	
}

/*

=== TestHighOrderPerfomance done 2,000 time(s) ===
Threads Total      OneTry     OneTry(mls)
------- ---------- ---------- ----------
8       204.3  sec 102.2  mls    102.166
4       266.5  sec 133.2  mls    133.234
2       157.8  sec 78.9   mls     78.895
1       71.0   sec 35.5   mls     35.486
------- ---------- ---------- ----------
Expected counter 30000 vs 30000 actual
Scheduled 630000 tasks
Completed 630000 tasks
Max wait 64 tasks
Max pool size 64

=== TestFastFlowPerfomance done 2,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
8       158.0  mls 79.0   mks     79.000
4       32.0   mls 16.0   mks     16.000
2       18.0   mls 9.0    mks      9.000
1       7.0    mls 3.5    mks      3.500
------- ---------- ---------- ----------
Expected counter 30000 vs 30000 actual
Scheduled 1080000 tasks
Completed 1080000 tasks
Max wait 8 tasks
Max pool size 8

*/