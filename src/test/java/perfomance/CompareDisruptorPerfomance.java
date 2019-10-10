package perfomance;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dflow.TestDisruptorFlowPerfomance;
import perfomance.impl.TestFastFlowPerfomanceDFLikeLL;
import perfomance.impl.TestFastFlowPerfomanceDFLikeRB;
import perfomance.impl.TestHighOrderPerfomanceDFLike;

@RunWith(Suite.class)
@SuiteClasses({TestFastFlowPerfomanceDFLikeRB.class, TestFastFlowPerfomanceDFLikeLL.class, TestHighOrderPerfomanceDFLike.class, TestDisruptorFlowPerfomance.class})
public class CompareDisruptorPerfomance {

	public final static int MAX_TRY = 100; //1_000_000;
	public final static String PUBLISHER_THREADS = "16,8,4,3,2,1";
	public final static int CONSUMER_POOL = 2;
	public static final long nanosleep = 1000L;
}

/*
July 1, 2018

=== TestDisruptorFlowPerfomance done 1,000,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      9.9    sec 9.9    mks      9.897
8       3.3    sec 3.3    mks      3.258
4       739.0  mls 739.0   ns      0.739
3       550.0  mls 550.0   ns      0.550
2       320.0  mls 320.0   ns      0.320
1       117.0  mls 117.0   ns      0.117
------- ---------- ---------- ----------
Expected counter 204000000 vs 204000000 actual

=== TestHighOrderPerfomanceDFLike done 1,000,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      22.2   sec 22.2   mks     22.178
8       12.5   sec 12.5   mks     12.495
4       5.2    sec 5.2    mks      5.153
3       4.2    sec 4.2    mks      4.240
2       3.6    sec 3.6    mks      3.636
1       429.0  mls 429.0   ns      0.429
------- ---------- ---------- ----------
Expected counter 204000000 vs 204000000 actual
Scheduled 34000000 tasks
Completed 34000000 tasks
Max wait 2 tasks
Max pool size 2

=== TestFastFlowPerfomanceDFLikeLL done 1,000,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      58.1   sec 58.1   mks     58.146
8       22.6   sec 22.6   mks     22.573
4       6.9    sec 6.9    mks      6.901
3       5.6    sec 5.6    mks      5.605
2       3.6    sec 3.6    mks      3.610
1       635.0  mls 635.0   ns      0.635
------- ---------- ---------- ----------
Expected counter 204000000 vs 204000000 actual
Scheduled 170000000 tasks
Completed 170000000 tasks
Max wait 2 tasks
Max pool size 2

=== TestFastFlowPerfomanceDFLikeRB done 1,000,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      50.1   sec 50.1   mks     50.107
8       18.1   sec 18.1   mks     18.068
4       8.5    sec 8.5    mks      8.486
3       3.6    sec 3.6    mks      3.571
2       2.1    sec 2.1    mks      2.104
1       363.0  mls 363.0   ns      0.363
------- ---------- ---------- ----------
Expected counter 204000000 vs 204000000 actual
Scheduled 170000000 tasks
Completed 170000000 tasks
Max wait 2 tasks
Max pool size 2

*************************************************************************************************
Jan 8, 2019

=== TestFastFlowPerfomanceDFLikeRB done 1,000,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      63.0   sec 63.0   mks     62.997
8       29.2   sec 29.2   mks     29.239
4       13.1   sec 13.1   mks     13.120
3       11.7   sec 11.7   mks     11.723
2       5.2    sec 5.2    mks      5.151
1       3.9    sec 3.9    mks      3.935
------- ---------- ---------- ----------
Expected counter 204000000 vs 204000000 actual
Scheduled 170000000 tasks
Completed 170000000 tasks
Max wait 2 tasks
Max pool size 2

=== TestFastFlowPerfomanceDFLikeLL done 1,000,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      68.3   sec 68.3   mks     68.258
8       34.4   sec 34.4   mks     34.445
4       14.6   sec 14.6   mks     14.555
3       14.8   sec 14.8   mks     14.769
2       12.8   sec 12.8   mks     12.756
1       5.6    sec 5.6    mks      5.565
------- ---------- ---------- ----------
Expected counter 204000000 vs 204000000 actual
Scheduled 170000000 tasks
Completed 170000000 tasks
Max wait 2 tasks
Max pool size 2

=== TestHighOrderPerfomanceDFLike done 1,000,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      51.7   sec 51.7   mks     51.668
8       33.3   sec 33.3   mks     33.252
4       19.3   sec 19.3   mks     19.298
3       18.0   sec 18.0   mks     17.969
2       11.4   sec 11.4   mks     11.352
1       695.0  mls 695.0   ns      0.695
------- ---------- ---------- ----------
Expected counter 204000000 vs 204000000 actual
Scheduled 34000000 tasks
Completed 34000000 tasks
Max wait 2 tasks
Max pool size 2

=== TestDisruptorFlowPerfomance done 1,000,000 time(s) ===
Threads Total      OneTry     OneTry(mks)
------- ---------- ---------- ----------
16      40.2   sec 40.2   mks     40.215
8       14.6   sec 14.6   mks     14.573
4       4.7    sec 4.7    mks      4.650
3       9.7    sec 9.7    mks      9.650
2       568.0  mls 568.0   ns      0.568
1       454.0  mls 454.0   ns      0.454
------- ---------- ---------- ----------
Expected counter 204000000 vs 204000000 actual

*/