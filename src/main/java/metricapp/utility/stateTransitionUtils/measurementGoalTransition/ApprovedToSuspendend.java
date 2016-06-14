package metricapp.utility.stateTransitionUtils.measurementGoalTransition;

import metricapp.entity.Element;

public class ApprovedToSuspendend extends MeasurementGoalStateTransitionCommand{

	public ApprovedToSuspendend(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() {
		super.execute();
		
		System.out.println("approved to suspended");
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote()
		
	}
	
	

}