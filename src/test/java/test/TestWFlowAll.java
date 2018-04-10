package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.fastflow.TestFwFlow;
import test.fastflow.TestFwFlow1;
import test.fastflow.TestHighOrderConsumer;

@RunWith(Suite.class)
@SuiteClasses({ TestHighOrderConsumer.class, TestFwFlow1.class, TestFwFlow.class})
public class TestWFlowAll {

}
