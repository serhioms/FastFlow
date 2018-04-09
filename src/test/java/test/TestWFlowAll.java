package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.fastflow.TestHighOrderConsumer;
import test.fastflow.TestHighOrderRunnable;
import test.fastflow.TestFwFlow;
import test.fastflow.TestFwFlow1;

@RunWith(Suite.class)
@SuiteClasses({ TestHighOrderConsumer.class, TestFwFlow1.class, TestFwFlow.class, TestHighOrderRunnable.class})
public class TestWFlowAll {

}
