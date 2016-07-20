package metricapp.utility.stateTransitionUtils.metricTransition;



import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.entity.metric.Metric;

import metricapp.service.repository.BusApprovedElementRepository;
import metricapp.utility.NotificationService;

public class PendingToApproved extends MetricStateTransitionCommand{
	
	public PendingToApproved(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("pending to approved "+after.toString());

		String busVersion=BusApprovedElementRepository.getInstance().sendApprovedElement(after, Metric.class).getVersionBus();
		after.setVersionBus(busVersion);
		
		Event event = new Event(EventPhase.PHASE2_2, after.getMetricatorId(), ArtifactScope.METRIC, after.getId(), "New Metric Approved");
		NotificationService.getInstance().publish("GRID", event);	}

}
