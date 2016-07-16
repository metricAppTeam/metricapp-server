package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Entity;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

public class MeasurementGoalStateTransitionFactory extends AbstractStateTransitionFactory {
	
	public MeasurementGoalStateTransitionFactory(Entity entity) {
		super(entity);
	}
	
	// Initialization-on-demand holder idiom
    private static class SingletonHolder {
        private static final MeasurementGoalStateTransitionFactory INSTANCE = new MeasurementGoalStateTransitionFactory(Entity.MeasurementGoal);
    }

    public static MeasurementGoalStateTransitionFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
