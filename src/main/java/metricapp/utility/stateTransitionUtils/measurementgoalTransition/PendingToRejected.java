package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.utility.NotificationService;

public class PendingToRejected extends MeasurementGoalStateTransitionCommand {

	public PendingToRejected(Element before, Element after) {
		super(before, after);
	}
	
	public void execute(){
		Event event = new Event(EventPhase.PHASE2_2, after.getMetricatorId(), ArtifactScope.MGOAL, after.getId(), "Measurement Goal Rejected!");
		NotificationService.getInstance().publish("METRICATOR", event);	
		
	}

}
