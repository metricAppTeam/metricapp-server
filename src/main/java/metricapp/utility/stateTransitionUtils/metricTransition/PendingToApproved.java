package metricapp.utility.stateTransitionUtils.metricTransition;



import metricapp.entity.Element;
import metricapp.entity.metric.Metric;

import metricapp.service.repository.BusApprovedElementRepository;

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
		
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote() \n TODO send to bus the new metric ->need to convert: wipe securekey, change id, ermesLastVersion
	}

}
