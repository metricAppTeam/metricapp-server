package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;

public class RejectedToSuspended extends MetricStateTransitionCommand {

	public RejectedToSuspended(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() throws Exception {
		super.execute();
		
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote()
	}

}
