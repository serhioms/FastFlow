package perfomance;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fastflow.TestFastFlowPerfomanceOnly;

@RunWith(Suite.class)
@SuiteClasses({TestFastFlowPerfomanceOnly.class})
public class FastFlowPerfomance {

	public final static int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors(); // Consumers

	public final static int MAX_TRY = 40_000;
	public final static String THREAD_SET = "16,8,4,2,1"; // Publishers
	
	public final static boolean PERFOMANCE = true;

}

/*
=== TestFastFlowPerfomanceOnly done 40,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      1.9    sec 48.0   mks     48.025
8       473.0  mls 11.8   mks     11.825
4       222.0  mls 5.6    mks      5.550
2       140.0  mls 3.5    mks      3.500
1       17.0   mls 425.0   ns      0.425
------- ---------- ---------- ----------
Expected counter 1240000 vs 1240000 actual
Scheduled 44640000 tasks
Completed 44640000 tasks
Max wait 1 tasks
Max pool size 1


=== TestFastFlowPerfomanceOnly done 40,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      2.7    sec 67.5   mks     67.500
8       732.0  mls 18.3   mks     18.300
4       367.0  mls 9.2    mks      9.175
2       171.0  mls 4.3    mks      4.275
1       30.0   mls 750.0   ns      0.750
------- ---------- ---------- ----------
Expected counter 1240000 vs 1240000 actual
Scheduled 44640000 tasks
Completed 44640000 tasks
Max wait 2 tasks
Max pool size 2

=== TestFastFlowPerfomanceOnly done 40,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      3.2    sec 80.1   mks     80.050
8       950.0  mls 23.8   mks     23.750
4       516.0  mls 12.9   mks     12.900
2       239.0  mls 6.0    mks      5.975
1       76.0   mls 1.9    mks      1.900
------- ---------- ---------- ----------
Expected counter 1240000 vs 1240000 actual
Scheduled 44640000 tasks
Completed 44640000 tasks
Max wait 4 tasks
Max pool size 4

=== TestFastFlowPerfomanceOnly done 40,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      4.0    sec 100.5  mks    100.475
8       1.1    sec 26.6   mks     26.625
4       591.0  mls 14.8   mks     14.775
2       242.0  mls 6.1    mks      6.050
1       91.0   mls 2.3    mks      2.275
------- ---------- ---------- ----------
Expected counter 1240000 vs 1240000 actual
Scheduled 44640000 tasks
Completed 44640000 tasks
Max wait 8 tasks
Max pool size 8

=== TestFastFlowPerfomanceOnly done 40,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      5.4    sec 135.1  mks    135.100
8       1.2    sec 30.8   mks     30.800
4       445.0  mls 11.1   mks     11.125
2       213.0  mls 5.3    mks      5.325
1       81.0   mls 2.0    mks      2.025
------- ---------- ---------- ----------
Expected counter 1240000 vs 1240000 actual
Scheduled 44640000 tasks
Completed 44640000 tasks
Max wait 16 tasks
Max pool size 16

=== TestFastFlowPerfomanceOnly done 40,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      3.9    sec 96.4   mks     96.350
8       999.0  mls 25.0   mks     24.975
4       505.0  mls 12.6   mks     12.625
2       235.0  mls 5.9    mks      5.875
1       90.0   mls 2.3    mks      2.250
------- ---------- ---------- ----------
Expected counter 1240000 vs 1240000 actual
Scheduled 44640000 tasks
Completed 44640000 tasks
Max wait 32 tasks
Max pool size 32


=== TestFastFlowPerfomanceOnly done 40,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      4.1    sec 102.9  mks    102.850
8       1.0    sec 25.2   mks     25.225
4       565.0  mls 14.1   mks     14.125
2       235.0  mls 5.9    mks      5.875
1       85.0   mls 2.1    mks      2.125
------- ---------- ---------- ----------
Expected counter 1240000 vs 1240000 actual
Scheduled 44640000 tasks
Completed 44640000 tasks
Max wait 64 tasks
Max pool size 64


=== TestFastFlowPerfomanceOnly done 40,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      4.8    sec 119.5  mks    119.450
8       1.2    sec 30.7   mks     30.650
4       450.0  mls 11.3   mks     11.250
2       213.0  mls 5.3    mks      5.325
1       78.0   mls 2.0    mks      1.950
------- ---------- ---------- ----------
Expected counter 1240000 vs 1240000 actual
Scheduled 44640000 tasks
Completed 44640000 tasks
Max wait 128 tasks
Max pool size 128
*/