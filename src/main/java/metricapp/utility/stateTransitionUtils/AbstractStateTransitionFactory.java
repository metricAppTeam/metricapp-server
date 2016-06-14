package metricapp.utility.stateTransitionUtils;

import metricapp.entity.Element;
import metricapp.entity.Entity;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.utility.stateTransitionUtils.measurementGoalTransition.MeasurementGoalStateTransitionFactory;
import metricapp.utility.stateTransitionUtils.metricTransition.MetricStateTransitionFactory;
import metricapp.utility.stateTransitionUtils.questionTransition.QuestionStateTransitionFactory;

public abstract class AbstractStateTransitionFactory {

	protected Entity entity;

	public AbstractStateTransitionFactory(Entity entity) {
		this.entity = entity;
	}

	public static final AbstractStateTransitionFactory getFactory(Entity entity) throws NotFoundException {
		switch (entity) {
		case MeasurementGoal:
			return new MeasurementGoalStateTransitionFactory(Entity.MeasurementGoal);
		case Metric:
			return new MetricStateTransitionFactory(Entity.Metric);
		case Question:
			return new QuestionStateTransitionFactory(Entity.Question);
		default:
			throw new NotFoundException();
		}
	}


	public StateTransitionCommand transition(Element before, Element after) throws IllegalStateTransitionException {
		{
			try {
				
				// another way is to indicate current package.
				// this.class.getClass().getPackage().getName() + ...
				return (StateTransitionCommand) Class
						.forName(AbstractStateTransitionFactory.class.getPackage().getName() + "."
								+ entity.name().toLowerCase() + "Transition." + before.getState().name() + "To"
								+ after.getState().name())
						.getConstructor(Element.class,Element.class).newInstance(before, after);
				
			} catch (Exception e) {
				throw new IllegalStateTransitionException(e);
			}
		}
	}
		
	
}
