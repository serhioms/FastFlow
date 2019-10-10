package perfomance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fastflow.TestFastThreadPool;
import fastflow.TestFixedThreadPool;

@RunWith(Suite.class)
@SuiteClasses({ TestFixedThreadPool.class, TestFastThreadPool.class })
public class CompareFastFlowPerfomance {

	final static public int MAX_TRY = 100; //100_000;
	final static public String PUBLISHER_THREADS = "16,8,4,2,1"; 
	final static public int CONSUMER_POOL = Runtime.getRuntime().availableProcessors()*2;
	public static final long nanosleep = 1000L; 
}
/*
July 1, 2018

=== TestFixedThreadPool done 100,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      12.8   sec 127.5  mks    127.510
8       2.8    sec 28.2   mks     28.190
4       1.1    sec 10.7   mks     10.680
2       504.0  mls 5.0    mks      5.040
1       172.0  mls 1.7    mks      1.720
------- ---------- ---------- ----------
Expected counter 93000000 vs 93000000 actual
Scheduled 111600000 tasks
Completed 111600000 tasks
Max wait 8 tasks
Max pool size 8

=== TestFastThreadPool done 100,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      1.9    sec 18.5   mks     18.510
8       1.1    sec 11.4   mks     11.410
4       355.0  mls 3.6    mks      3.550
2       118.0  mls 1.2    mks      1.180
1       41.0   mls 410.0   ns      0.410
------- ---------- ---------- ----------
Expected counter 93000000 vs 7512358 actual
Scheduled 7727234 tasks
Completed 7727234 tasks
Max wait 8 tasks
Max pool size 8

***************************************************************************************************
Jan 8, 2019

=== TestFixedThreadPool done 100,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      98.2   sec 982.1  mks    982.110
8       28.2   sec 282.2  mks    282.200
4       10.7   sec 106.5  mks    106.530
2       4.8    sec 47.8   mks     47.810
1       2.2    sec 21.7   mks     21.730
------- ---------- ---------- ----------
Expected counter 93000000 vs 93000000 actual
Scheduled 111600000 tasks
Completed 111600000 tasks
Max wait 8 tasks
Max pool size 8

=== TestFastThreadPool done 100,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      99.8   sec 997.8  mks    997.780
8       34.1   sec 341.0  mks    340.960
4       11.4   sec 114.3  mks    114.260
2       5.2    sec 52.0   mks     51.970
1       2.4    sec 23.7   mks     23.720
------- ---------- ---------- ----------
Expected counter 93000000 vs 93000000 actual
Scheduled 111600000 tasks
Completed 111600000 tasks
Max wait 8 tasks
Max pool size 8

*/