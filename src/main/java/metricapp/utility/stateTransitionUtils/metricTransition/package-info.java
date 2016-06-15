/**
 * This package manages the state transition command for Metric entity.
 *
 * It has a factory of State transition command for Metric.
 * @see metricapp.utility.stateTransitionUtils.metricTransition.MetricStateTransitionFactory
 *
 * Every command extends MetricStateTransitionCommand, that implements StateTransitionCommand interface.
 * @see metricapp.utility.stateTransitionUtils.metricTransition.MetricStateTransitionCommand
 *
 *  User Must put here every state transition for metric that he needs to execute.
 *
 * The name of these transition MUST be stateBeforeTostateAfter.
 */
package metricapp.utility.stateTransitionUtils.metricTransition;