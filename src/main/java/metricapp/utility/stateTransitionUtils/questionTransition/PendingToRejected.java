package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;

public class PendingToRejected extends QuestionStateTransitionCommand {

	public PendingToRejected(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("Question state switched from 'Pending' to 'Approved'");
	}
	
}
