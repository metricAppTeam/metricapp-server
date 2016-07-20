package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.utility.NotificationService;

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
		
		Event event = new Event(EventPhase.PHASE2_2, after.getCreatorId(), ArtifactScope.QUESTION, after.getId(), "Question Rejected!");
		NotificationService.getInstance().publish("QUESTIONER", event);
	}
	
}
