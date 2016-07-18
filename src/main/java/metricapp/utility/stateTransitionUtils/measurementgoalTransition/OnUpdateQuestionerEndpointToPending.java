package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;


public class OnUpdateQuestionerEndpointToPending extends MeasurementGoalStateTransitionCommand {

	

	public OnUpdateQuestionerEndpointToPending(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception {
		super.execute();
		
		System.out.println("onupdate a pending");	
		// TODO alert newMetric.getCreatorId()		
	}

	
	

}
