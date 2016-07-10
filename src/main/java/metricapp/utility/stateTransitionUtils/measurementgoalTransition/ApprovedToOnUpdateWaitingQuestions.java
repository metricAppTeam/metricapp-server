package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;

public class ApprovedToOnUpdateWaitingQuestions extends MeasurementGoalStateTransitionCommand {

	public ApprovedToOnUpdateWaitingQuestions(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() throws Exception {
		super.execute();
		
		System.out.println("approved to onupdate");
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote()
	}

}
