package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Entity;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

public class QuestionStateTransitionFactory extends AbstractStateTransitionFactory {

	
	public QuestionStateTransitionFactory(Entity entity) {
		super(entity);
	}
	
	// Initialization-on-demand holder idiom
    private static class SingletonHolder {
        private static final QuestionStateTransitionFactory INSTANCE = new QuestionStateTransitionFactory(Entity.Question);
    }

    public static QuestionStateTransitionFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
