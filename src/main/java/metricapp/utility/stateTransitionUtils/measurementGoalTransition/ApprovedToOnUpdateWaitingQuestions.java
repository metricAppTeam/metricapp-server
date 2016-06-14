package metricapp.utility.stateTransitionUtils.measurementGoalTransition;

import metricapp.entity.Element;

public class ApprovedToOnUpdateWaitingQuestions extends MeasurementGoalStateTransitionCommand {

	public ApprovedToOnUpdateWaitingQuestions(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() {
		super.execute();
		
		System.out.println("approved to onupdate");
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote()
	}

}
