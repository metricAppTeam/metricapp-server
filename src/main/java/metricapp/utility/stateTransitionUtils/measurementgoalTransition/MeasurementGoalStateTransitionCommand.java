package metricapp.utility.stateTransitionUtils.measurementgoalTransition;

import metricapp.entity.Element;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.utility.stateTransitionUtils.StateTransitionCommand;

public class MeasurementGoalStateTransitionCommand implements StateTransitionCommand {

	protected MeasurementGoal before;
	protected MeasurementGoal after;
	
	
	public MeasurementGoalStateTransitionCommand(Element before, Element after) {
		this.before = (MeasurementGoal) before;
		this.after = (MeasurementGoal) after;
		
	}
	
	
	public void execute() throws Exception{
		if (before == null || after == null){
			throw new NullPointerException("Command needs to be initialized!");
		}
	}
}
