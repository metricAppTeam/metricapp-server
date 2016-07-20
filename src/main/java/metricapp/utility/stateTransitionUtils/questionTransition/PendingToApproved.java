package metricapp.utility.stateTransitionUtils.questionTransition;

import metricapp.entity.Element;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.entity.question.Question;
import metricapp.service.repository.BusApprovedElementRepository;
import metricapp.utility.NotificationService;

public class PendingToApproved extends QuestionStateTransitionCommand{

//	@Autowired
//	private NotificationServiceInterface notificationService;
	
	public PendingToApproved(Element before, Element after) {
		super(before, after);
//		notificationService.publish(after.getId(), EventScope.GRID, 0, ArtifactScope.QUESTION, after.getId(), 
//		"Question state switched from 'Pending' to 'Approved'");
	}
	
	@Override
	public void execute() throws Exception{
		super.execute();
		System.out.println("Question state switched from 'Pending' to 'Approved'");
		
		String busVersion = BusApprovedElementRepository.getInstance().sendApprovedElement(after, Question.class).getVersionBus();
		after.setVersionBus(busVersion);

		Event event = new Event(EventPhase.PHASE2_2, after.getCreatorId(), ArtifactScope.QUESTION, after.getId(), "New Question approved");
		NotificationService.getInstance().publish("GRID", event);
	}

}
