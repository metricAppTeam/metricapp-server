package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.notification.box.NotificationBox;

@RepositoryRestResource(exported = false)
public interface NotificationBoxRepository extends MongoRepository<NotificationBox, String> {
	
	public NotificationBox findByOwnerId(String owner);
	
}
