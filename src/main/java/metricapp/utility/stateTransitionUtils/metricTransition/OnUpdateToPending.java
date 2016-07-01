package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;


public class OnUpdateToPending extends MetricStateTransitionCommand {

	

	public OnUpdateToPending(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception {
		super.execute();
		
		System.out.println("onupdate a pending");	
		// TODO alert newMetric.getCreatorId()		
	}

	
	

}
