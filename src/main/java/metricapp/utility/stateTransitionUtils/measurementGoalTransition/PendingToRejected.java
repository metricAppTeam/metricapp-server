package metricapp.utility.stateTransitionUtils.measurementGoalTransition;

import metricapp.entity.Element;

public class PendingToRejected extends MeasurementGoalStateTransitionCommand {

	public PendingToRejected(Element before, Element after) {
		super(before, after);
	}
	
	

}
