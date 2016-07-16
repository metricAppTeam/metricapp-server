package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.repository.BusApprovedElementRepository;

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
		// TODO alert newMeasurementGoal.getMetricatorId() with newMeasurementGoal.getReleaseNote() \n TODO send to bus the new measurement goal ->need to convert: wipe securekey, change id, ermesLastVersion
	}

}
