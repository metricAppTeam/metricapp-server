package metricapp.utility;

import org.springframework.beans.factory.annotation.Autowired;

import metricapp.service.spec.controller.EventCRUDInterface;
import metricapp.service.spec.controller.TopicCRUDInterface;

public class NotificationManager {
	
	@Autowired
	private EventCRUDInterface eventController;	
	
	@Autowired
	private TopicCRUDInterface topicController;	
	
	private static NotificationManager instance;
	
	private NotificationManager() {}
	
	public static NotificationManager getInstance() {
		if (instance == null) {
			instance = new NotificationManager();
		}
		return instance;
	}

}
