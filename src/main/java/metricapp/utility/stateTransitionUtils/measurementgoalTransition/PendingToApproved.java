package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.repository.BusApprovedElementRepository;
import metricapp.utility.NotificationService;

public class PendingToApproved extends MeasurementGoalStateTransitionCommand{

	public PendingToApproved(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("pending to approved");
		
		String busVersion=BusApprovedElementRepository.getInstance().sendApprovedElement(after, MeasurementGoal.class).getVersionBus();
		after.setVersionBus(busVersion);
		Event event = new Event(EventPhase.PHASE2_2, after.getMetricatorId(), ArtifactScope.MGOAL, after.getId(), "Measurement Goal Approved");
		NotificationService.getInstance().publish("METRICATOR", event);	
		
	}

}
