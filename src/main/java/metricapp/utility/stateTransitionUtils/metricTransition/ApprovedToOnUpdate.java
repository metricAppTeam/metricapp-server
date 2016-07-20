package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.utility.NotificationService;

public class ApprovedToOnUpdate extends MetricStateTransitionCommand {

	public ApprovedToOnUpdate(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() throws Exception {
		super.execute();
		
		System.out.println("approved to onupdate");
		Event event = new Event(EventPhase.PHASE2_2, after.getMetricatorId(), ArtifactScope.METRIC, after.getId(), "Change request for a Metric");
		NotificationService.getInstance().publish("METRICATOR", event);
		}

}
