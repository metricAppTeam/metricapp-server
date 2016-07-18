package metricapp.utility.stateTransitionUtils;

import metricapp.entity.Element;
import metricapp.entity.Entity;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.utility.stateTransitionUtils.measurementgoalTransition.MeasurementGoalStateTransitionFactory;
import metricapp.utility.stateTransitionUtils.metricTransition.MetricStateTransitionFactory;
import metricapp.utility.stateTransitionUtils.questionTransition.QuestionStateTransitionFactory;

/**
 * This class is part of Abstract Factory Pattern Implementation.
 * Every factory of this packages MUST be used like AbstractStateTransitionFactory type.
 * the static method getFactory create a new instance of factory according to entity passed like parameter.
 *
 * transition method is the method that implementing Reflection, searches by name the class of the transtion required for two elements passed like parameters.
 */
public abstract class AbstractStateTransitionFactory {

	protected Entity entity;

    /**
     * This constructor is needed by child classes, this class is abstract, cannot be instanced.
     * @param entity, from Enumeration Entity, identifies the type of factory the user needs.
     */
	public AbstractStateTransitionFactory(Entity entity) {
		this.entity = entity;
	}

    /**
     * This function is static and final, is the function that returns the user desired factory.
     *  Not all the entities have their own StateTransitionFactory, due to some entities are external to our system and we cannot manage their status.
     * Supported entities are: Metric, MeasurementGoal, Question
     *
     * @param entity from Enumeration Entity, identifies the type of factory the user needs.
     * @return the factory required by the user
     * @throws NotFoundException in case of bad input, entity is not supported
     */
	public static final AbstractStateTransitionFactory getFactory(Entity entity) throws NotFoundException {
		switch (entity) {
		case MeasurementGoal:
			return MeasurementGoalStateTransitionFactory.getInstance();
		case Metric:
			return MetricStateTransitionFactory.getInstance();
		case Question:
			return QuestionStateTransitionFactory.getInstance();
		default:
			throw new NotFoundException();
		}
	}

    /**
     * This method implements Reflection to retrieve single transition command.
     * User just needs to define the class of transition, calling it StateBeforeToStateAfter and put it on the right package.
     *  This function could be implement the call of a class with a predetermined map of classes, improving performances but making less flexible the structure.
     * In that way a user needs to change the map after the introduction or the delete of a state transition class.
     * In this way, user is not required in class grabbing process.
     *
     *  User Must use the command at least one time
     *
     * @param before You can pass MeasurementGoal, Question, Metric. state cannot be null
     * @param after same as before
     * @return it returns an object command that the user can asynchronously execute, it is fully configurated.
     * @throws IllegalStateTransitionException
     */
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
