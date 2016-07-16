package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;


public class CreatedToOnUpdateQuestionsEndpoint extends MeasurementGoalStateTransitionCommand {

	
	public CreatedToOnUpdateQuestionsEndpoint(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() {
		System.out.println("From Created to OnUpdateQuestionsEndpoint");
		// TODO alert newMetric.getCreatorId()
	}

	
}
