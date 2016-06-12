package metricapp.utility.stateTransitionUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import metricapp.entity.Element;
import metricapp.entity.Entity;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.utility.stateTransitionUtils.measurementGoalTransition.MeasurementGoalStateTransitionFactory;
import metricapp.utility.stateTransitionUtils.metricTransition.MetricStateTransitionFactory;
import metricapp.utility.stateTransitionUtils.questionTransition.QuestionStateTransitionFactory;

public abstract class AbstractStateTransitionFactory {

	private Entity entity;

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

	public final StateTransitionCommand transition(Element before, Element after) throws IllegalStateTransitionException {
		try {
			return (StateTransitionCommand) Class.forName(AbstractStateTransitionFactory.class.getPackage().getName() + "." + entity.name().toLowerCase() + "Transition." + before.getState().name() + "To" + after.getState().name())
					.newInstance();
		} catch (Exception e) {
			throw new IllegalStateTransitionException(e);
		}
		
	}
}
