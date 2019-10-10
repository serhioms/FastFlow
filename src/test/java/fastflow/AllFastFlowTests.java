package fastflow;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAnotherThreadPool.class, TestFastFlow1.class, TestFastFlowN.class, TestFastThreadPool.class,
		TestFixedThreadPool.class, TestLMaxThreadPool.class, TestSimpleThreadPool.class })
public class AllFastFlowTests {

}
