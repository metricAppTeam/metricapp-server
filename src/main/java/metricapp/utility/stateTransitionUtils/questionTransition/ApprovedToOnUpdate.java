package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;

public class ApprovedToOnUpdate extends QuestionStateTransitionCommand {

	public ApprovedToOnUpdate(Element before, Element after) {
		super(before, after);
	}

	@Override
	public void execute(){
		super.execute();
		System.out.println("Question state switched from 'Rejected' to 'OnUpdate'");
	}
	
}