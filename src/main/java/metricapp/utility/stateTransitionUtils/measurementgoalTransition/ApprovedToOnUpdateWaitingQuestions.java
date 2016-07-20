package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.utility.NotificationService;

public class ApprovedToOnUpdateWaitingQuestions extends MeasurementGoalStateTransitionCommand {

	public ApprovedToOnUpdateWaitingQuestions(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() throws Exception {
		super.execute();
		
		System.out.println("approved to onupdate");
		Event event = new Event(EventPhase.PHASE2_2, after.getMetricatorId(), ArtifactScope.MGOAL, after.getId(), "Change Request for Measurement Goal");
		NotificationService.getInstance().publish("METRICATOR", event);	
		
	}

}
