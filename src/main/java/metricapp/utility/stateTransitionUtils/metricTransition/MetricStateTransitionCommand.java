package metricapp.utility.stateTransitionUtils.metricTransition;

import org.springframework.beans.factory.annotation.Autowired;

import metricapp.entity.Element;
import metricapp.entity.metric.Metric;
import metricapp.service.repository.BusApprovedElementRepository;
import metricapp.service.spec.repository.BusApprovedElementInterface;
import metricapp.utility.stateTransitionUtils.StateTransitionCommand;

/**
 * This abstract class is the prototype of a Command, configured to be used for Metric transitions.
 *  It has 2 attributes, that MUST be not null during execution.
 *<p>
 * <p> Child classes MUST implement a 2 parameters constructor that calls the method in this class:
 * <p> e.g. public CreatedToOnUpdate(Element before, Element after){
 * <p>     super(before, after);
 *<p>  }
 *
 * <p> Child should overrides execute method and call it like super class method:
 *<p>  e.g. public void execute(){
 * <p>     super.execute();
 * <p>    foo(things);
 * <p>     ...
 * <p> }
 *
 */
public abstract class MetricStateTransitionCommand implements StateTransitionCommand{
	
	protected Metric before;
	protected Metric after;


	/**
	 * this method simply casts Element to Metric objects passed like parameters.
	 * It is not intended to be use directly, a factory can do it for you.
	 *
	 * @param before Metric before
	 * @param after Metric after
     */
	public MetricStateTransitionCommand(Element before, Element after) {
		this.before = (Metric) before;
		this.after = (Metric) after;
		
	}

	/**
	 * This is a prototype of the real method that will be implemented by child classes.
	 * It just check if parameters are null.
	 *
	 */
	public void execute(){
		if (before == null || after == null){
			throw new NullPointerException("Command needs to be initialized!");
		}
	}

}
