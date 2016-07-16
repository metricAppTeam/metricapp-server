package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;
import metricapp.entity.question.Question;
import metricapp.utility.stateTransitionUtils.StateTransitionCommand;

public class QuestionStateTransitionCommand implements StateTransitionCommand {
	protected Question before;
	protected Question after;
	
	
	public QuestionStateTransitionCommand(Element before, Element after) {
		this.before = (Question) before;
		this.after = (Question) after;
		
	}
	
	
	public void execute() throws Exception{
		if (before == null || after == null){
			throw new NullPointerException("Command needs to be initialized!");
		}
	}

}
