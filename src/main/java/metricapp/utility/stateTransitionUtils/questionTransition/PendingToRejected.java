package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;

public class PendingToRejected extends QuestionStateTransitionCommand {

	public PendingToRejected(Element before, Element after) {
		super(before, after);
	}

}
