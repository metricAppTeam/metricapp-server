package metricapp.service.spec.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.event.EventScope;
import metricapp.entity.notification.Notification;

@RepositoryRestResource(exported = false)
public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	public Notification findNotificationById(String id);
	
	public List<Notification> findNotificationByAuthorId(String authorId);
	
	public List<Notification> findNotificationByScope(EventScope scope);
	
	public List<Notification> findNotificationByArtifactId(String artifactId);
	
}
