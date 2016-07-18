package metricapp.service.spec;

import java.util.List;

import metricapp.entity.event.EventScope;

public interface NotificationServiceInterface {
	
	public boolean addTopic(String topicName);
	
	public boolean removeTopic(String topicName);
	
	public boolean addSubscribers(String topicName, List<String> subscribers);
	
	public boolean removeSubscribers(String topicName, List<String> subscribers);
	
	public boolean publish(String authorId, EventScope scope, String artifactId, String description);

}
