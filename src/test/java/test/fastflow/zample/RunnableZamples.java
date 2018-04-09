package test.fastflow.zample;

import com.boundsofjava.newsletter.higherorderrunnable.HigherOrderRunnable;

import ca.rdmss.fastflow.FwAdapter;
import test.fastflow.zample.impl.TestContext;
import test.fastflow.zample.impl.TestTask;

public class RunnableZamples {

	static public Runnable zampleRunnables(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
				FwAdapter.toRunnable((TestContext c)->TestTask.Job1.job(c),
						(TestContext c)->TestTask.Job2.job(c),
						(TestContext c)->TestTask.Job3.job(c),
						(TestContext c)->TestTask.Job4.job(c),
						(TestContext c)->TestTask.Job5.job(c),
						(TestContext c)->TestTask.theEnd.job(c))
		);
	}

	static public Runnable zample1(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
				TestTask.Job1::run,
				TestTask.Job2::run,
				TestTask.Job3::run,
				TestTask.Job4::run,
				TestTask.Job5::run,
				TestTask.theEnd::run 
		);
	}
	
	static public Runnable zample2(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
				parallel.combine(
					TestTask.Job1::run,
					TestTask.Job1::run,
					TestTask.Job1::run,
					TestTask.Job1::run,
					TestTask.Job1::run
				),
				TestTask.theEnd::run 
		);
	}

	static public Runnable zample3(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
				TestTask.Job2::run,
				TestTask.Job3::run,
				parallel.combine(
					TestTask.Job1::run,
					TestTask.Job1::run,
					TestTask.Job1::run
				),
				TestTask.Job4::run,
				TestTask.Job5::run,
				TestTask.theEnd::run 
		);
	}

	static public Runnable zample4(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
					TestTask.Job1::run,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::run,
								TestTask.Job2::run,
								TestTask.Job2::run
								),
						sequential.combine(
								TestTask.Job2::run,
								TestTask.Job2::run,
								TestTask.Job2::run
								),
						sequential.combine(
								TestTask.Job2::run,
								TestTask.Job2::run,
								TestTask.Job2::run
								)
					),
					TestTask.Job3::run,
					TestTask.theEnd::run
				);
	}

	static public Runnable zample5(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
					TestTask.Job1::run,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::run,
								TestTask.Job2::run,
								TestTask.Job2::run
								),
						parallel.combine(
								TestTask.Job2::run,
								TestTask.Job2::run,
								TestTask.Job2::run
								),
						sequential.combine(
								TestTask.Job2::run,
								TestTask.Job2::run,
								TestTask.Job2::run
								)
					),
					TestTask.Job3::run,
					TestTask.theEnd::run
				);
	}

	static public Runnable zample6(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
					TestTask.Job1::run,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::run,
								parallel.combine(
										TestTask.Job2::run,
										TestTask.Job2::run,
										TestTask.Job2::run
										),
								TestTask.Job2::run
								),
						parallel.combine(
								TestTask.Job2::run,
								sequential.combine(
										TestTask.Job2::run,
										TestTask.Job2::run,
										TestTask.Job2::run
										),
								TestTask.Job2::run
								),
						sequential.combine(
								TestTask.Job2::run,
								parallel.combine(
										TestTask.Job2::run,
										TestTask.Job2::run,
										TestTask.Job2::run
										),
								TestTask.Job2::run
								)
					),
					TestTask.Job3::run,
					TestTask.theEnd::run
				);
	}
	
	static public Runnable zample7(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
					TestTask.Job1::run,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::run,
								parallel.combine(
										TestTask.Job2::run,
										sequential.combine(
												TestTask.Job2::run,
												TestTask.Job2::run,
												TestTask.Job2::run
												),
										TestTask.Job2::run
										),
								TestTask.Job2::run
								),
						parallel.combine(
								TestTask.Job2::run,
								sequential.combine(
										TestTask.Job2::run,
										parallel.combine(
												TestTask.Job2::run,
												TestTask.Job2::run,
												TestTask.Job2::run
												),
										TestTask.Job2::run
										),
								TestTask.Job2::run
								),
						sequential.combine(
								TestTask.Job2::run,
								parallel.combine(
										TestTask.Job2::run,
										sequential.combine(
												TestTask.Job2::run,
												TestTask.Job2::run,
												TestTask.Job2::run
												),
										TestTask.Job2::run
										),
								TestTask.Job2::run
								)
					),
					TestTask.Job3::run,
					TestTask.theEnd::run
				);
	}
	static public Runnable zample8(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
					TestTask.Job1::run,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::run,
								parallel.combine(
										TestTask.Job2::run,
										sequential.combine(
												TestTask.Job2::run,
												parallel.combine(
														TestTask.Job2::run,
														TestTask.Job2::run,
														TestTask.Job2::run
														),
												TestTask.Job2::run
												),
										TestTask.Job2::run
										),
								TestTask.Job2::run
								),
						parallel.combine(
								TestTask.Job2::run,
								sequential.combine(
										TestTask.Job2::run,
										parallel.combine(
												TestTask.Job2::run,
												sequential.combine(
														TestTask.Job2::run,
														TestTask.Job2::run,
														TestTask.Job2::run
														),
												TestTask.Job2::run
												),
										TestTask.Job2::run
										),
								TestTask.Job2::run
								),
						sequential.combine(
								TestTask.Job2::run,
								parallel.combine(
										TestTask.Job2::run,
										sequential.combine(
												TestTask.Job2::run,
												parallel.combine(
														TestTask.Job2::run,
														TestTask.Job2::run,
														TestTask.Job2::run
														),
												TestTask.Job2::run
												),
										TestTask.Job2::run
										),
								TestTask.Job2::run
								)
					),
					TestTask.Job3::run,
					TestTask.theEnd::run
				);
	}

	static public Runnable zample32(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
				TestTask.Job2::run,
				TestTask.Job3::run,
				parallel.combine(
					TestTask.Job1::run,
					TestTask.Job1::run,
					TestTask.Job1::run
				),
				TestTask.Job4::run,
				TestTask.Job5::run,
				parallel.combine(
						TestTask.Job1::run,
						TestTask.Job1::run,
						TestTask.Job1::run
					),
				TestTask.Job6::run,
				TestTask.Job7::run,
				TestTask.theEnd ::run
		);
	}

	static public Runnable zample62(HigherOrderRunnable sequential, HigherOrderRunnable parallel) {
		return sequential.combine(
					TestTask.Job1::run,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::run,
								parallel.combine(
										TestTask.Job2::run,
										TestTask.Job2::run
										),
								TestTask.Job2::run
								)
						,parallel.combine(
								TestTask.Job2::run,
								sequential.combine(
										TestTask.Job2::run,
										TestTask.Job2::run,
										TestTask.Job2::run
										),
								TestTask.Job2::run
								)
						,sequential.combine(
								TestTask.Job2::run,
								parallel.combine(
										TestTask.Job2::run,
										TestTask.Job2::run,
										TestTask.Job2::run,
										TestTask.Job2::run
										),
								TestTask.Job2::run
								)
					),
					TestTask.Job3::run,
					TestTask.theEnd::run
				);
	}
}
