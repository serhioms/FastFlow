package test.impl;

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

/*
 * 
 */
public class TestBase {

	static public int MAX_ITERATION = 10000;
	static public int MAX_THREADS = Runtime.getRuntime().availableProcessors();
	
	@Rule public TestName name = new TestName();
	 
	static public ExecutorService executor;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		executor = Executors.newFixedThreadPool(MAX_THREADS);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		for(executor.shutdown(); !executor.awaitTermination(100L, TimeUnit.MILLISECONDS); );
	}
	

	protected long avgTimeMls = 0;

	@Before
	public void setUp() throws Exception {
		avgTimeMls = 0;
	}

	@After
	public void tearDown() throws Exception {
		System.out.printf("%d\t%s =\t%.3f\tmls\n", MAX_THREADS, name.getMethodName(), (avgTimeMls+0.0)/(MAX_ITERATION+0.0));
	}
	
	public static void sleepMls(int mls) {
		if( mls > 0 ) {
			try {
				TimeUnit.MILLISECONDS.sleep(mls);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static public Random rnd = new Random(new Date().getTime());

	public static void sleepMks(int mks) {
		if( mks > 0 ) {
			try {
				TimeUnit.MICROSECONDS.sleep(rnd.nextInt(mks)+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	static private AtomicInteger sum = new AtomicInteger(0);
	static private AtomicInteger contextSum = new AtomicInteger(0);
	static public AtomicReference<String> concat = new AtomicReference<String>("");
	static public AtomicReference<String> trace = new AtomicReference<String>("");
	
	static private volatile boolean isRunning;
	static private long startTime;
	
	static public void init() {
		isRunning = true;
		startTime = System.currentTimeMillis();
		sum = new AtomicInteger(0);
		contextSum = new AtomicInteger(0);
		concat = new AtomicReference<String>("");
		trace = new AtomicReference<String>("");
		threadCounterMap.clear();
		countTasks.set(0); 
		countTime.set(0); 
	}

	static public void done() {
		while( isRunning ) {}
	}

	static public void stop() {
		isRunning = false;
	}

	static public String getTrace() {
		return trace.get();
	}

	static public String getConcat() {
		return concat.get();
	}

	static public int getSum() {
		return sum.get();
	}

	static public int getContextSum() {
		return contextSum.get();
	}

	public static long getTimeMls() {
		return System.currentTimeMillis()-startTime;
	}

	public static AtomicInteger countTasks = new AtomicInteger(0); 
	public static AtomicLong countTime = new AtomicLong(0); 
	
	public static long getTimePerTask(TimeUnit unit) {
		return countTasks.get()==0?0: unit.convert(countTime.get(), TimeUnit.MILLISECONDS)/countTasks.get();
	}
	
	public static void work(int context, int ident, String newtrace) {
		long start = System.currentTimeMillis();
		
		countTasks.incrementAndGet();
		countThreads();
		
		if( MAX_ITERATION == 1 ) {
			while(true) {
				String oldtrace = trace.get();
				if( trace.compareAndSet(oldtrace, oldtrace+newtrace) ) {
					break;
				}
			}
		}
		if( ident == -1 ) {
			isRunning = false;
		} else {
			while(true) {
				int oldsum = sum.get();
				if( sum.compareAndSet(oldsum, oldsum+ident) ) {
					break;
				}
			}
			while(true) {
				int oldsum = contextSum.get();
				if( contextSum.compareAndSet(oldsum, oldsum+context) ) {
					break;
				}
			}
			while(true) {
				String oldconcat = concat.get();
				if( concat.compareAndSet(oldconcat, oldconcat+ident) ) {
					break;
				}
			}
		}
		countTime.addAndGet(System.currentTimeMillis()-start);
	}
	
	static Map<String, AtomicInteger> threadCounterMap = new ConcurrentHashMap<String, AtomicInteger>(128);
	
	public static void countThreads() {
		String threadName = Thread.currentThread().getName();
		AtomicInteger threadCounter = threadCounterMap.get(threadName);
		if( threadCounter == null ) {
			threadCounterMap.put(threadName, new AtomicInteger(1));
		} else {
			threadCounter.incrementAndGet();
		}
	}
	
	public static String threadsDistribution(int maxThreads) {
		/*return threadCounterMap.keySet().stream().collect(Collectors.toList()).toString();*/
		return threadCounterMap.values().stream().map(a->a.get()).sorted().collect(Collectors.toList()).toString();
	}
}
