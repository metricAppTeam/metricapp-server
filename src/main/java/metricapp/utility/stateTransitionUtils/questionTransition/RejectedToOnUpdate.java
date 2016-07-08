package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;

public class RejectedToOnUpdate extends QuestionStateTransitionCommand {

	public RejectedToOnUpdate(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("Question state switched from 'Rejected' to 'OnUpdate'");
	}
	
}
