/**
 * This package is a module to manage the transition between two states for Elements of the Grid and others.
 *
 * It was born from the need to decouple business and logic controllers to state transitions of the Elements.
 * Our Elements was structured with a State Machine Diagram in our documentation, this module implements transition actions.
 *
 * To create this module these is the targets that he needs achieve:
 * -To Decouple Controllers from State transition logic.
 * -To Offer a fast and lightweight service in scenarios of massive mulththread execution.
 * -To Expose a simple way to implement actions, triggered by the change of state.
 * -To Manage with the same tool different Entities at the same way.
 *
 * To achieve these needs, this module implements different and well integrated solutions:
 *
 * The Command Pattern is used to bring users an object, configured and ready to run, that he can easily execute when he needs.
 * @see metricapp.utility.stateTransitionUtils.StateTransitionCommand is the interface of this kind of object.
 *
 * This module doesn't implement directly the State Pattern, but it is inspired of it. Elements change their behaviour according to internal state in transparent way.
 * @See State is the enumeration where the states are described.
 *
 * Factory Pattern is implemented in single packages.
 * Every entity has a factory that can create new StateTransitionCommand, configured and ready to run.
 * In this way the user can easily get a configured command, he has only to execute it.
 * With different objects released to user, the speed of execution in multithread scenario is incremented, it permits massive use of parallel work.
 * @See MeasurementGoalStateTransitionFactory
 * @See QuestionStateTransitionFactory
 * @See MetricStateTransitionFactory
 *
 *
 * Abstract Factory Pattern is used to uniform behaviour of different factories. Different entities has the same interface.
 * This improve the readiness of the code, it's user friendly and it is debuggable and manageable.
 * @See AbstractStateTransitionFactory
 *
 * Every Factory use Reflection to inspect the entity state transition package to retrieve the correct transition.
 * This is the most flexible structure, in fact to add a transition a user just needs to create a new class in the entity state transition package.
 * No other actions are required.
 *
 *  * Classes of transition state commands MUST agree with the specification:
 * -Class must be written like StateBeforeToStateAfter
 * -Class must be in its entity package, like measurementGoalTransition, metricTransition, questionTransition
 * -Class must not be abstract
 * -Class must extends local entity StateTransitionCommand abstract class, like MetricStateTRansitionCommand, MeasurementGoalStateTRansitionCommand.
 * -Class must implements a 2 parameters constructor (Element before, Element after) that calls super(before, after)
 * -Class must overrides public void execute() method, and should call super.execute() that performs a check of parameters.
 *
 * Example of use:
 * AbstractStateTransitionFactory.getFactory(Entity.Metric).transition(oldMetric, newMetric).execute();
 *
 */
package metricapp.utility.stateTransitionUtils;