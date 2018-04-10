package test.fastflow.zample;

import java.util.function.Consumer;

import ca.rdmss.fastflow.FwAdapter;
import test.fastflow.higherorder.HigherOrderConsumer;
import test.fastflow.zample.impl.TestContext;
import test.fastflow.zample.impl.TestTask;

public class ConsumerZamples {

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zampleConsumers(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
				FwAdapter.toConsumer(
						(T c)->TestTask.Job1.job(c),
						(T c)->TestTask.Job2.job(c),
						TestTask.Job3::accept,
						TestTask.Job4::job,
						TestTask.Job5::accept,
						TestTask.theEnd::job)
		);
	}

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample1(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
				TestTask.Job1::accept,
				TestTask.Job2::accept,
				TestTask.Job3::accept,
				TestTask.Job4::accept,
				TestTask.Job5::accept,
				TestTask.theEnd::accept 
		);
	}

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample2(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
				parallel.combine(
					TestTask.Job1::accept,
					TestTask.Job1::accept,
					TestTask.Job1::accept,
					TestTask.Job1::accept,
					TestTask.Job1::accept
				),
				TestTask.theEnd::accept 
		);
	}
	

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample3(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
				TestTask.Job2::accept,
				TestTask.Job3::accept,
				parallel.combine(
					TestTask.Job1::accept,
					TestTask.Job1::accept,
					TestTask.Job1::accept
				),
				TestTask.Job4::accept,
				TestTask.Job5::accept,
				TestTask.theEnd::accept 
		);
	}

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample4(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
					TestTask.Job1::accept,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::accept,
								TestTask.Job2::accept,
								TestTask.Job2::accept
								),
						sequential.combine(
								TestTask.Job2::accept,
								TestTask.Job2::accept,
								TestTask.Job2::accept
								),
						sequential.combine(
								TestTask.Job2::accept,
								TestTask.Job2::accept,
								TestTask.Job2::accept
								)
					),
					TestTask.Job3::accept,
					TestTask.theEnd::accept
				);
	}

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample5(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
					TestTask.Job1::accept,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::accept,
								TestTask.Job2::accept,
								TestTask.Job2::accept
								),
						parallel.combine(
								TestTask.Job2::accept,
								TestTask.Job2::accept,
								TestTask.Job2::accept
								),
						sequential.combine(
								TestTask.Job2::accept,
								TestTask.Job2::accept,
								TestTask.Job2::accept
								)
					),
					TestTask.Job3::accept,
					TestTask.theEnd::accept
				);
	}

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample6(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
					TestTask.Job1::accept,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::accept,
								parallel.combine(
										TestTask.Job2::accept,
										TestTask.Job2::accept,
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								),
						parallel.combine(
								TestTask.Job2::accept,
								sequential.combine(
										TestTask.Job2::accept,
										TestTask.Job2::accept,
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								),
						sequential.combine(
								TestTask.Job2::accept,
								parallel.combine(
										TestTask.Job2::accept,
										TestTask.Job2::accept,
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								)
					),
					TestTask.Job3::accept,
					TestTask.theEnd::accept
				);
	}
	
	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample7(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
					TestTask.Job1::accept,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::accept,
								parallel.combine(
										TestTask.Job2::accept,
										sequential.combine(
												TestTask.Job2::accept,
												TestTask.Job2::accept,
												TestTask.Job2::accept
												),
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								),
						parallel.combine(
								TestTask.Job2::accept,
								sequential.combine(
										TestTask.Job2::accept,
										parallel.combine(
												TestTask.Job2::accept,
												TestTask.Job2::accept,
												TestTask.Job2::accept
												),
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								),
						sequential.combine(
								TestTask.Job2::accept,
								parallel.combine(
										TestTask.Job2::accept,
										sequential.combine(
												TestTask.Job2::accept,
												TestTask.Job2::accept,
												TestTask.Job2::accept
												),
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								)
					),
					TestTask.Job3::accept,
					TestTask.theEnd::accept
				);
	}
	
	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample8(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
					TestTask.Job1::accept,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::accept,
								parallel.combine(
										TestTask.Job2::accept,
										sequential.combine(
												TestTask.Job2::accept,
												parallel.combine(
														TestTask.Job2::accept,
														TestTask.Job2::accept,
														TestTask.Job2::accept
														),
												TestTask.Job2::accept
												),
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								),
						parallel.combine(
								TestTask.Job2::accept,
								sequential.combine(
										TestTask.Job2::accept,
										parallel.combine(
												TestTask.Job2::accept,
												sequential.combine(
														TestTask.Job2::accept,
														TestTask.Job2::accept,
														TestTask.Job2::accept
														),
												TestTask.Job2::accept
												),
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								),
						sequential.combine(
								TestTask.Job2::accept,
								parallel.combine(
										TestTask.Job2::accept,
										sequential.combine(
												TestTask.Job2::accept,
												parallel.combine(
														TestTask.Job2::accept,
														TestTask.Job2::accept,
														TestTask.Job2::accept
														),
												TestTask.Job2::accept
												),
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								)
					),
					TestTask.Job3::accept,
					TestTask.theEnd::accept
				);
	}
	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample32(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
				TestTask.Job2::accept,
				TestTask.Job3::accept,
				parallel.combine(
					TestTask.Job1::accept,
					TestTask.Job1::accept,
					TestTask.Job1::accept
				),
				TestTask.Job4::accept,
				TestTask.Job5::accept,
				parallel.combine(
						TestTask.Job1::accept,
						TestTask.Job1::accept,
						TestTask.Job1::accept
					),
				TestTask.Job6::accept,
				TestTask.Job7::accept,
				TestTask.theEnd ::accept
		);
	}
	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zample62(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
		return sequential.combine(
					TestTask.Job1::accept,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2::accept,
								parallel.combine(
										TestTask.Job2::accept,
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								)
						,parallel.combine(
								TestTask.Job2::accept,
								sequential.combine(
										TestTask.Job2::accept,
										TestTask.Job2::accept,
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								)
						,sequential.combine(
								TestTask.Job2::accept,
								parallel.combine(
										TestTask.Job2::accept,
										TestTask.Job2::accept,
										TestTask.Job2::accept,
										TestTask.Job2::accept
										),
								TestTask.Job2::accept
								)
					),
					TestTask.Job3::accept,
					TestTask.theEnd::accept
				);
	}
}
