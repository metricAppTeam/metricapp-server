package metricapp.utility.stateTransitionUtils.questionTransition;


import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.utility.NotificationService;

public class OnUpdateToPending extends QuestionStateTransitionCommand{

//	@Autowired
//	private NotificationServiceInterface notificationService;
	
	public OnUpdateToPending(Element before, Element after) {
		super(before, after);
//		notificationService.publish(after.getId(), EventScope.GRID, 0, ArtifactScope.QUESTION, after.getId(), 
//		"Question state switched from 'OnUpdate' to 'Pending'");
	}
	
	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("Question state switched from 'OnUpdate' to 'Pending'");
		Event event = new Event(EventPhase.PHASE2_2, after.getCreatorId(), ArtifactScope.QUESTION, after.getId(), "A Question is pending for approval");
		NotificationService.getInstance().publish("EXPERT", event);
	}

}
