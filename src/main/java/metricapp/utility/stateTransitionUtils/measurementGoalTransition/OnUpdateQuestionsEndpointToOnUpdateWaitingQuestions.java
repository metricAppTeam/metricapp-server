package metricapp.utility.stateTransitionUtils.measurementGoalTransition;

import metricapp.entity.Element;


public class OnUpdateQuestionsEndpointToOnUpdateWaitingQuestions extends MeasurementGoalStateTransitionCommand {

	
	public OnUpdateQuestionsEndpointToOnUpdateWaitingQuestions(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() {
		System.out.println("From Created to OnUpdate");
		// TODO alert newMeasurementGoal.getCreatorId()
	}

	
}
