package metricapp.utility.stateTransitionUtils.metricTransition;

import metricapp.utility.stateTransitionUtils.StateTransitionCommand;

public class CreatedToOnUpdate implements StateTransitionCommand {

	public CreatedToOnUpdate() {
	}

	@Override
	public void execute() {
		System.out.println("da created a onupdate");
	}

}
