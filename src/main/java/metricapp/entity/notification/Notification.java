package metricapp.entity.notification;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventScope;
import metricapp.utility.RandomGenerator;

@Data
@TypeAlias("metricapp.Notification")
@Document(collection = "notifications")
public class Notification {	
	
	@Id
	private String id;
	@CreatedDate
	private LocalDate creationDate;
	private String authorId;
	private EventScope scope;
	private String artifactId;	
	private String description;	
	private boolean read;
	
	public static Notification fromEvent(Event event) {
		Notification notification = new Notification();
		notification.setCreationDate(event.getCreationDate());
		notification.setAuthorId(event.getAuthorId());
		notification.setScope(event.getScope());
		notification.setArtifactId(event.getArtifactId());
		notification.setDescription(event.getDescription());
		notification.setRead(false);
		return notification;
	}

	public static Notification randomNotification(){
		Notification notification = new Notification();
		
		notification.setId(RandomGenerator.randomString());		
		notification.setCreationDate(RandomGenerator.randomLocalDate());
		notification.setAuthorId(RandomGenerator.randomString());
		notification.setScope(RandomGenerator.randomEnum(EventScope.class));
		notification.setArtifactId(RandomGenerator.randomString());
		notification.setDescription(RandomGenerator.randomString());		
		notification.setRead(RandomGenerator.randomBoolean());

		return notification;
	}
	
}
