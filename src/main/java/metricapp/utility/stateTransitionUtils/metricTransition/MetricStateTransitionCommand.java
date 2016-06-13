package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.entity.Element;
import metricapp.entity.metric.Metric;
import metricapp.utility.stateTransitionUtils.StateTransitionCommand;

public abstract class MetricStateTransitionCommand implements StateTransitionCommand{

	protected Metric before;
	protected Metric after;
	
	
	public MetricStateTransitionCommand(Element before, Element after) {
		this.before = (Metric) before;
		this.after = (Metric) after;
		
	}
	
	
	public void execute(){
		if (before == null || after == null){
			throw new NullPointerException("Command needs to be initialized!");
		}
	}

}
