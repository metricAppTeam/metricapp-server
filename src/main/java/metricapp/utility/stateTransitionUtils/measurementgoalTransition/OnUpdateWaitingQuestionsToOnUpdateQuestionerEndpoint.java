package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.utility.NotificationService;


public class OnUpdateWaitingQuestionsToOnUpdateQuestionerEndpoint extends MeasurementGoalStateTransitionCommand {

	
	public OnUpdateWaitingQuestionsToOnUpdateQuestionerEndpoint(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() {
		System.out.println("From Created to OnUpdate");
		Event event = new Event(EventPhase.PHASE2_2, after.getMetricatorId(), ArtifactScope.MGOAL, after.getId(), "Questioner Endpoint Reached");
		NotificationService.getInstance().publish("METRICATOR", event);	
		
	}

	
}
