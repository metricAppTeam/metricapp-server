package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;

public class ApprovedToOnUpdate extends MetricStateTransitionCommand {

	public ApprovedToOnUpdate(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() throws Exception {
		super.execute();
		
		System.out.println("approved to onupdate");
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote()
	}

}
