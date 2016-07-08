package metricapp.utility.stateTransitionUtils.measurementGoalTransition;

import metricapp.entity.Element;

public class RejectedToOnUpdateWaitingQuestions extends MeasurementGoalStateTransitionCommand {

	public RejectedToOnUpdateWaitingQuestions(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception {
		super.execute();
		
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote()
	}

}
