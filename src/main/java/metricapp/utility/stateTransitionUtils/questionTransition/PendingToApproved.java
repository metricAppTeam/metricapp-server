package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;

public class PendingToApproved extends QuestionStateTransitionCommand{

	public PendingToApproved(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute(){
		super.execute();
		System.out.println("Question state switched from 'Pending' to 'Approved'");
	}

}
