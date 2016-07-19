package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;

public class RejectedToOnUpdate extends QuestionStateTransitionCommand {

//	@Autowired
//	private NotificationServiceInterface notificationService;
	
	public RejectedToOnUpdate(Element before, Element after) {
		super(before, after);
//		notificationService.publish(after.getId(), EventScope.GRID, 0, ArtifactScope.QUESTION, after.getId(), 
//				"Question state switched from 'Rejected' to 'OnUpdate'");
	}

	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("Question state switched from 'Rejected' to 'OnUpdate'");
	}
	
}
