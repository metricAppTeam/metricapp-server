package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.utility.NotificationService;

public class RejectedToOnUpdateWaitingQuestions extends MeasurementGoalStateTransitionCommand {

	public RejectedToOnUpdateWaitingQuestions(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception {
		super.execute();
		
		Event event = new Event(EventPhase.PHASE2_2, after.getMetricatorId(), ArtifactScope.MGOAL, after.getId(), "A Measurement Goal is being updated");
		NotificationService.getInstance().publish("EXPERT", event);	
		}

}
