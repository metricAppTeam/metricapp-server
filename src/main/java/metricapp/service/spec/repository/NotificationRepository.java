package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import metricapp.entity.notification.Notification;

@RepositoryRestResource(exported = false)
public interface NotificationRepository extends MongoRepository<Notification, String> {
	
	public Notification findNotificationById(String id);
	
}
