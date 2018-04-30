package perfomance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fastflow.TestFixedThreadPool;
import fastflow.TestFastThreadPool;

@RunWith(Suite.class)
@SuiteClasses({ TestFixedThreadPool.class, TestFastThreadPool.class })
public class CompareFastFlowPerfomance {

	final static public int MAX_TRY = 10_000; 
	final static public String PUBLISHER_THREADS = "16,8,4,2,1"; 
	final static public int CONSUMER_POOL = Runtime.getRuntime().availableProcessors(); 
}
/*

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
*/