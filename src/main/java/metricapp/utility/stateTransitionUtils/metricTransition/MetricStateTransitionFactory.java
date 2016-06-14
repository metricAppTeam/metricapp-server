package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Entity;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

public class MetricStateTransitionFactory extends AbstractStateTransitionFactory {

	private static MetricStateTransitionFactory metricStateTransitionFactory;
	
	public MetricStateTransitionFactory(Entity entity) {
		super(entity);
	}
	
	public static MetricStateTransitionFactory getInstance(){
		return metricStateTransitionFactory == null ? new MetricStateTransitionFactory(Entity.Metric) : metricStateTransitionFactory;
	}


}
