package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;


public class OnUpdateToPending extends MetricStateTransitionCommand {

	

	public OnUpdateToPending(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() {
		super.execute();
		
		System.out.println("onupdate a pending");	
		System.out.println("TODO alert newMetric.getCreatorId()");		
	}

	
	

}
