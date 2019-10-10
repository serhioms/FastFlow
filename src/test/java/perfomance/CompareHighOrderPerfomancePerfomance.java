package perfomance;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import perfomance.impl.TestFastFlowPerfomance;
import perfomance.impl.TestHighOrderPerfomance;

@RunWith(Suite.class)
@SuiteClasses({TestHighOrderPerfomance.class, TestFastFlowPerfomance.class})
public class CompareHighOrderPerfomancePerfomance {

	public final static int MAX_TRY = 100; //2_000;
	public final static String PUBLISHER_THREADS = "8,4,2,1";
	public static final long nanosleep = 1000L;
}

/*
July 1, 2018

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

********************************************************************************
Jan 8, 2019

=== TestHighOrderPerfomance done 2,000 time(s) ===
Threads Total      OneTry     OneTry(mls)
------- ---------- ---------- ----------
8       120.0  sec 60.0   mls     60.002
4       155.2  sec 77.6   mls     77.618
2       99.6   sec 49.8   mls     49.809
1       51.1   sec 25.5   mls     25.541
------- ---------- ---------- ----------
Expected counter 900000 vs 900000 actual
Scheduled 630000 tasks
Completed 630000 tasks
Max wait 64 tasks
Max pool size 64

=== TestFastFlowPerfomance done 2,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
8       618.0  mls 309.0  mks    309.000
4       182.0  mls 91.0   mks     91.000
2       96.0   mls 48.0   mks     48.000
1       42.0   mls 21.0   mks     21.000
------- ---------- ---------- ----------
Expected counter 900000 vs 900000 actual
Scheduled 1080000 tasks
Completed 1080000 tasks
Max wait 4 tasks
Max pool size 4


*/