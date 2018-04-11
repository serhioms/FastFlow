package higherorder;

import java.util.function.Consumer;

import ca.rdmss.fastflow.FwAdapter;
import fastflow.impl.TestContext;
import fastflow.impl.TestTask;

public class HighOrderZamples {

	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> zampleDFlowLike(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> asynchronous) {
		return sequential.combine(
				TestTask.Job1::job,
				sequential.combine(
						TestTask.Job2::job,
						asynchronous.combine(TestTask.Job3::job),
						TestTask.Job4::job
						),
				TestTask.Job5::job,
				TestTask.theEnd::job 
		);
	}

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
