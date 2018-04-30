package perfomance;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dflow.TestDisruptorFlowPerfomance;
import perfomance.impl.TestFastFlowPerfomanceDFLikeLL;
import perfomance.impl.TestFastFlowPerfomanceDFLikeRB;
import perfomance.impl.TestHighOrderPerfomanceDFLike;

@RunWith(Suite.class)
@SuiteClasses({TestDisruptorFlowPerfomance.class, TestHighOrderPerfomanceDFLike.class, TestFastFlowPerfomanceDFLikeLL.class, TestFastFlowPerfomanceDFLikeRB.class})
public class CompareDisruptorPerfomance {

	public final static int MAX_TRY = 1_000_000;
	public final static String PUBLISHER_THREADS = "16,8,4,3,2,1";
	public final static int CONSUMER_POOL = 2;
}

/*

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

*/