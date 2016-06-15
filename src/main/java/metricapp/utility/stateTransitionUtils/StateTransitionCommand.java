package metricapp.utility.stateTransitionUtils;

/**
 * This interface is part of Command Pattern, used to bring to user an object that he can execute, unlinked with logic controller.
 * Only execute method is specified, to keep customizable the initialization of the objects.
 *
 * Classes of transition state commands MUST agree with the specification:
 * -Class must be written like StateBeforeToStateAfter
 * -Class must be in its entity package, like measurementGoalTransition, metricTransition, questionTransition
 * -Class must not be abstract
 * -Class must extends local entity StateTransitionCommand abstract class, like MetricStateTRansitionCommand, MeasurementGoalStateTRansitionCommand.
 * -Class must implements a 2 parameters constructor (Element before, Element after) that calls super(before, after)
 * -Class must overrides public void execute() method, and should call super.execute() that performs a check of parameters.
 */
public interface StateTransitionCommand {

	public void execute();
	
	

}
