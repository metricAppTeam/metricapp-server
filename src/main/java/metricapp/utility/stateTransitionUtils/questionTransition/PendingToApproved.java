package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;
import metricapp.entity.question.Question;
import metricapp.service.repository.BusApprovedElementRepository;

public class PendingToApproved extends QuestionStateTransitionCommand{

	public PendingToApproved(Element before, Element after) {
		super(before, after);
	}
	
	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("Question state switched from 'Pending' to 'Approved'");
		
		String busVersion = BusApprovedElementRepository.getInstance().sendApprovedElement(after, Question.class).getVersionBus();
		after.setVersionBus(busVersion);
	}

}
