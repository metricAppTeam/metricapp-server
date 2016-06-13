package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;

public class PendingToApproved extends MetricStateTransitionCommand{

	public PendingToApproved(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() {
		super.execute();
		System.out.println("pending to approved");
		System.out.println("// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote() \n TODO send to bus the new metric ->need to convert: wipe securekey, change id, ermesLastVersion");
	}

}
