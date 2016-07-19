package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;

public class PendingToRejected extends QuestionStateTransitionCommand {

//	@Autowired
//	private NotificationServiceInterface notificationService;

	public PendingToRejected(Element before, Element after) {
		super(before, after);
//		notificationService.publish(after.getId(), EventScope.GRID, 0, ArtifactScope.QUESTION, after.getId(), 
//		"Question state switched from 'Pending' to 'Rejected'");
	}

	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("Question state switched from 'Pending' to 'Rejected'");
	}
	
}
