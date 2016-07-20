package metricapp.service.spec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.event.ArtifactScope;
import metricapp.entity.notification.Notification;

@RepositoryRestResource(exported = false)
public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	public List<Notification> findNotificationByRecipient(String username);
	
	public Page<Notification> findNotificationByRecipient(String username, Pageable page);
	
	public Notification findNotificationByRecipientAndId(String username, String id);
	
	public List<Notification> findNotificationByRecipientAndAuthorId(String username, String authorId);
	
	public List<Notification> findNotificationByRecipientAndArtifactScope(String username, ArtifactScope artifactScope);	
	
	public List<Notification> findNotificationByRecipientAndArtifactId(String username, String artifactId);	
	
	public List<Notification> findNotificationByRecipientAndReadIsTrue(String username);
	
	public List<Notification> findNotificationByRecipientAndReadIsFalse(String username);
	
	public Notification deleteNotificationByRecipientAndId(String username, String id);
	
}
