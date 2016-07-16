/**
 * This package is a module to manage the transition between two states for Elements of the Grid and others.
 *
 *  It was born from the need to decouple business and logic controllers to state transitions of the Elements.
 *  Our Elements was structured with a State Machine Diagram in our documentation, this module implements transition actions.
 *
 *     <ul>
 *  <li>To create this module these is the targets that he needs achieve:
 *  <li>-To Decouple Controllers from State transition logic.
 * <li>-To Offer a fast and lightweight service in scenarios of massive mulththread execution.
 * <li>-To Expose a simple way to implement actions, triggered by the change of state.
 *  <li>-To Manage with the same tool different Entities at the same way.
 *  </ul>
 *
 * <p>
 * To achieve these needs, this module implements different and well integrated solutions:
 *<p>
 *  The Command Pattern is used to bring users an object, configured and ready to run, that he can easily execute when he needs.
 * StateTransitionCommand is the interface of this kind of object.
 *<p>
 *  This module doesn't implement directly the State Pattern, but it is inspired of it. Elements change their behaviour according to internal state in transparent way.
 *   State is the enumeration where the states are described.
 *<p>
 *  Factory Pattern is implemented in single packages.
 *  Every entity has a factory that can create new StateTransitionCommand, configured and ready to run.
 *  In this way the user can easily get a configured command, he has only to execute it.
 *  With different objects released to user, the speed of execution in multithread scenario is incremented, it permits massive use of parallel work.
 *
 *<p>
 * Abstract Factory Pattern is used to uniform behaviour of different factories. Different entities has the same interface.
 * This improve the readiness of the code, it's user friendly and it is debuggable and manageable.
 *
 *<p>
 * Every Factory use Reflection to inspect the entity state transition package to retrieve the correct transition.
 * This is the most flexible structure, in fact to add a transition a user just needs to create a new class in the entity state transition package.
 * No other actions are required.
 *<p>
 * Every Factory is instanced with the use of Singleton Pattern, to guarantee performances.
 * It's not necessary for abstract factory to create a new object at every request.
 * Singleton is implemented by Initialization-on-demand holder idiom
 *
 *
 * <ul>
 * <li>Classes of transition state commands MUST agree with the specification:
 * <li> -Class must be written like StateBeforeToStateAfter
 * <li> -Class must be in its entity package, like measurementGoalTransition, metricTransition, questionTransition
 * <li> -Class must not be abstract
 * <li> -Class must extends local entity StateTransitionCommand abstract class, like MetricStateTRansitionCommand, MeasurementGoalStateTRansitionCommand.
 * <li> -Class must implements a 2 parameters constructor (Element before, Element after) that calls super(before, after)
 * <li> -Class must overrides public void execute() method, and should call super.execute() that performs a check of parameters.
 * </ul>
 *
 * <p>
 * Example of use:
 * AbstractStateTransitionFactory.getFactory(Entity.Metric).transition(oldMetric, newMetric).execute();
 *
 */
package metricapp.utility.stateTransitionUtils;