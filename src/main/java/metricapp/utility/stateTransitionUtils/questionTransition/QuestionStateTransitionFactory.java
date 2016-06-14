package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Entity;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

public class QuestionStateTransitionFactory extends AbstractStateTransitionFactory {

	private static QuestionStateTransitionFactory questionStateTransitionFactory;
	
	public QuestionStateTransitionFactory(Entity entity) {
		super(entity);
	}

	public static QuestionStateTransitionFactory getInstance(){
		return questionStateTransitionFactory == null ? new QuestionStateTransitionFactory(Entity.Question) : questionStateTransitionFactory;
	}


}
