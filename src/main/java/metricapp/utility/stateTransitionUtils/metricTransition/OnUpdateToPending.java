package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.utility.NotificationService;



public class OnUpdateToPending extends MetricStateTransitionCommand {

	

	public OnUpdateToPending(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception {
		super.execute();
		
		System.out.println("onupdate a pending");	
		Event event = new Event(EventPhase.PHASE2_2, after.getMetricatorId(), ArtifactScope.METRIC, after.getId(), "A Metric is pending for approval");
		NotificationService.getInstance().publish("EXPERT", event);
		
	}

	
	

}
