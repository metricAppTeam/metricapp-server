package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.utility.stateTransitionUtils.StateTransitionCommand;

public class OnUpdateToPending implements StateTransitionCommand {

	public OnUpdateToPending() {
	}

	@Override
	public void execute() {
		System.out.println("onupdate a pending");		
	}

}
