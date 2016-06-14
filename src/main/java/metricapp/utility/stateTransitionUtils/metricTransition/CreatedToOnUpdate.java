package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;


public class CreatedToOnUpdate extends MetricStateTransitionCommand {

	
	public CreatedToOnUpdate(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() {
		System.out.println("da created a onupdate");
		System.out.println("TODO alert newMetric.getCreatorId()");
	}

	
}
