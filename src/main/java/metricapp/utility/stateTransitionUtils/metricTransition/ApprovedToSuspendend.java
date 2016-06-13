package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;

public class ApprovedToSuspendend extends MetricStateTransitionCommand{

	public ApprovedToSuspendend(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() {
		super.execute();
		
		System.out.println("approved to suspended");
		System.out.println("// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote()");
		
	}
	
	

}
