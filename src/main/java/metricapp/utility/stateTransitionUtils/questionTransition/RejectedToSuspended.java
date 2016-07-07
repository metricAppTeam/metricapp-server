package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;

public class RejectedToSuspended extends QuestionStateTransitionCommand {

	public RejectedToSuspended(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("Question state switched from 'Rejected' to 'Suspended'");
	}
	
}