package fastflow.impl;

import ca.rdmss.fastflow.FwAdapter;
import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.fastflow.FwHighOrder;

public class TestFlows {

	static public <T extends TestContext> FwFlow<T> zampleRunnables(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
				FwAdapter.frRunnable(TestTask.Job1::run,
				TestTask.Job2::run,
				TestTask.Job3::run,
				TestTask.Job4::run,
				TestTask.Job5::run,
				TestTask.theEnd::run)
		);
	}

	
	static public <T extends TestContext> FwFlow<T> zampleConsumers(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
				FwAdapter.frConsumer((T c)->TestTask.Job1.accept(c),
				TestTask.Job2::accept,
				TestTask.Job3::accept,
				TestTask.Job4::accept,
				TestTask.Job5::accept,
				TestTask.theEnd::accept)
		);
	}

	
	static public <T extends TestContext> FwFlow<T> zample1(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
				TestTask.Job1,
				TestTask.Job2,
				TestTask.Job3,
				TestTask.Job4,
				TestTask.Job5,
				TestTask.theEnd 
		);
	}

	
	static public <T extends TestContext> FwFlow<T> zample2(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
				parallel.combine(
					TestTask.Job1,
					TestTask.Job1,
					TestTask.Job1,
					TestTask.Job1,
					TestTask.Job1
				),
				TestTask.theEnd 
		);
	}
	

	
	static public <T extends TestContext> FwFlow<T> zample3(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
				TestTask.Job2,
				TestTask.Job3,
				parallel.combine(
					TestTask.Job1,
					TestTask.Job1,
					TestTask.Job1
				),
				TestTask.Job4,
				TestTask.Job5,
				TestTask.theEnd 
		);
	}

	
	static public <T extends TestContext> FwFlow<T> zample4(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
					TestTask.Job1,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2,
								TestTask.Job2,
								TestTask.Job2
								),
						sequential.combine(
								TestTask.Job2,
								TestTask.Job2,
								TestTask.Job2
								),
						sequential.combine(
								TestTask.Job2,
								TestTask.Job2,
								TestTask.Job2
								)
					),
					TestTask.Job3,
					TestTask.theEnd
				);
	}

	
	static public <T extends TestContext> FwFlow<T> zample5(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
					TestTask.Job1,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2,
								TestTask.Job2,
								TestTask.Job2
								),
						parallel.combine(
								TestTask.Job2,
								TestTask.Job2,
								TestTask.Job2
								),
						sequential.combine(
								TestTask.Job2,
								TestTask.Job2,
								TestTask.Job2
								)
					),
					TestTask.Job3,
					TestTask.theEnd
				);
	}

	
	static public <T extends TestContext> FwFlow<T> zample7(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
					TestTask.Job1,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										sequential.combine(
												TestTask.Job2,
												TestTask.Job2,
												TestTask.Job2
												),
										TestTask.Job2
										),
								TestTask.Job2
								),
						parallel.combine(
								TestTask.Job2,
								sequential.combine(
										TestTask.Job2,
										parallel.combine(
												TestTask.Job2,
												TestTask.Job2,
												TestTask.Job2
												),
										TestTask.Job2
										),
								TestTask.Job2
								),
						sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										sequential.combine(
												TestTask.Job2,
												TestTask.Job2,
												TestTask.Job2
												),
										TestTask.Job2
										),
								TestTask.Job2
								)
					),
					TestTask.Job3,
					TestTask.theEnd
				);
	}

	
	static public <T extends TestContext> FwFlow<T> zample6(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
					TestTask.Job1,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										TestTask.Job2
										),
								TestTask.Job2
								)
						,sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										TestTask.Job2,
										TestTask.Job2
										),
								TestTask.Job2
								)
						,sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										TestTask.Job2,
										TestTask.Job2,
										TestTask.Job2
										),
								TestTask.Job2
								)
					),
					TestTask.Job3,
					TestTask.theEnd
				);
	}
	
	
	static public <T extends TestContext> FwFlow<T> zample8(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
					TestTask.Job1,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										sequential.combine(
												TestTask.Job2,
												parallel.combine(
														TestTask.Job2,
														TestTask.Job2,
														TestTask.Job2
														),
												TestTask.Job2
												),
										TestTask.Job2
										),
								TestTask.Job2
								),
						parallel.combine(
								TestTask.Job2,
								sequential.combine(
										TestTask.Job2,
										parallel.combine(
												TestTask.Job2,
												sequential.combine(
														TestTask.Job2,
														TestTask.Job2,
														TestTask.Job2
														),
												TestTask.Job2
												),
										TestTask.Job2
										),
								TestTask.Job2
								),
						sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										sequential.combine(
												TestTask.Job2,
												parallel.combine(
														TestTask.Job2,
														TestTask.Job2,
														TestTask.Job2
														),
												TestTask.Job2
												),
										TestTask.Job2
										),
								TestTask.Job2
								)
					),
					TestTask.Job3,
					TestTask.theEnd
				);
	}
	
	
	static public <T extends TestContext> FwFlow<T> zample32(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
				TestTask.Job2,
				TestTask.Job3,
				parallel.combine(
					TestTask.Job1,
					TestTask.Job1,
					TestTask.Job1
				),
				TestTask.Job4,
				TestTask.Job5,
				parallel.combine(
						TestTask.Job1,
						TestTask.Job1,
						TestTask.Job1
					),
				TestTask.Job6,
				TestTask.Job7,
				TestTask.theEnd 
		);
	}

	
	static public <T extends TestContext> FwFlow<T> zample62(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
		return sequential.combine(
					TestTask.Job1,
					parallel.combine( 
						sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										TestTask.Job2
										),
								TestTask.Job2
								)
						,parallel.combine(
								TestTask.Job2,
								sequential.combine(
										TestTask.Job2,
										TestTask.Job2,
										TestTask.Job2
										),
								TestTask.Job2
								)
						,sequential.combine(
								TestTask.Job2,
								parallel.combine(
										TestTask.Job2,
										TestTask.Job2,
										TestTask.Job2,
										TestTask.Job2
										),
								TestTask.Job2
								)
					),
					TestTask.Job3,
					TestTask.theEnd
				);
	}
}
