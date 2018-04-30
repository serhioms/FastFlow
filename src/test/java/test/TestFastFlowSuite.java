package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fastflow.TestFastFlow1;
import fastflow.TestFastFlowN;
import higherorder.TestHighOrderConsumer;

@RunWith(Suite.class)
@SuiteClasses({ TestHighOrderConsumer.class, TestFastFlow1.class, TestFastFlowN.class})
public class TestFastFlowSuite {
}
