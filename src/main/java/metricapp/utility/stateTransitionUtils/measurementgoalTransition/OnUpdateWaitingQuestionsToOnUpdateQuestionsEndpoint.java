package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;


public class OnUpdateWaitingQuestionsToOnUpdateQuestionsEndpoint extends MeasurementGoalStateTransitionCommand {

	
	public OnUpdateWaitingQuestionsToOnUpdateQuestionsEndpoint(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() {
		System.out.println("From Created to OnUpdate");
		// TODO alert newMeasurementGoal.getCreatorId()
	}

	
}
