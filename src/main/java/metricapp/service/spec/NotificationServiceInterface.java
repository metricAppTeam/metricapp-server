package metricapp.service.spec;

import java.util.List;

import metricapp.entity.event.Event;

public interface NotificationServiceInterface {
	
	public void addTopic(String topicName);
	
	public void removeTopic(String topicName);
	
	public boolean addSubscribers(String topicName, List<String> subscribers);
	
	public boolean removeSubscribers(String topicName, List<String> subscribers);
	
	//public boolean publish(String authorId, EventScope eventScope, String eventScopeId, ArtifactScope artifactScope, String artifactId, String description);
		
	public boolean publish(String topicName, Event event);

}
