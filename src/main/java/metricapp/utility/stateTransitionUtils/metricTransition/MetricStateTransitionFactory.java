package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Entity;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

public class MetricStateTransitionFactory extends AbstractStateTransitionFactory {
	
	public MetricStateTransitionFactory(Entity entity) {
		super(entity);
	}
	
	// Initialization-on-demand holder idiom
    private static class SingletonHolder {
        private static final MetricStateTransitionFactory INSTANCE = new MetricStateTransitionFactory(Entity.Metric);
    }

    public static MetricStateTransitionFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
