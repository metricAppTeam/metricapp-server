package metricapp.utility.stateTransitionUtils.metricTransition;

import java.io.IOException;

import metricapp.entity.Element;
import metricapp.entity.metric.Metric;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.service.repository.BusApprovedElementRepository;

public class PendingToApproved extends MetricStateTransitionCommand{
	
	public PendingToApproved(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("pending to approved "+after.toString());
		try {
			
			BusApprovedElementRepository.getInstance().sendApprovedElement(after, Metric.class);
			
		} catch (BadInputException | BusException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO alert newMetric.getMetricatorId() with newMetric.getReleaseNote() \n TODO send to bus the new metric ->need to convert: wipe securekey, change id, ermesLastVersion
	}

}
