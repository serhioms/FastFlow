package perfomance;

import java.util.function.Consumer;

import ca.rdmss.fastflow.FwFlow;
import ca.rdmss.fastflow.FwHighOrder;
import fastflow.impl.TestContext;
import fastflow.impl.TestTask;
import higherorder.HigherOrderConsumer;

public class PerfomanceFFlows {

	static public <T extends TestContext> FwFlow<T> complexFlow(FwHighOrder<T> sequential, FwHighOrder<T> parallel) {
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
	
	@SuppressWarnings("unchecked")
	static public <T extends TestContext> Consumer<T> complexFlow(HigherOrderConsumer<T> sequential, HigherOrderConsumer<T> parallel) {
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

	
}
