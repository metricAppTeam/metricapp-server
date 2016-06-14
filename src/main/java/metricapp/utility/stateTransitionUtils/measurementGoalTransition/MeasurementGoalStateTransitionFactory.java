package metricapp.utility.stateTransitionUtils.measurementGoalTransition;

import metricapp.entity.Entity;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

public class MeasurementGoalStateTransitionFactory extends AbstractStateTransitionFactory {

	private static MeasurementGoalStateTransitionFactory measurementGoalStateTransitionFactory;
	
	public MeasurementGoalStateTransitionFactory(Entity entity) {
		super(entity);
	}
	
	public static MeasurementGoalStateTransitionFactory getInstance(){
		return measurementGoalStateTransitionFactory == null ? new MeasurementGoalStateTransitionFactory(Entity.MeasurementGoal) : measurementGoalStateTransitionFactory;
	}

}
