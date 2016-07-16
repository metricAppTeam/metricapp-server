package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;

public class ApprovedToSuspendend extends MetricStateTransitionCommand{

	public ApprovedToSuspendend(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() throws Exception {
		super.execute();
		
		System.out.println("approved to suspended");
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote()
		
	}
	
	

}
