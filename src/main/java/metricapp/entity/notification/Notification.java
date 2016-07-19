package metricapp.entity.notification;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventScope;
import metricapp.utility.RandomGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("metricapp.Notification")
@Document(collection = "notifications")
public class Notification {	
	
	@Id
	private String id;
	private String eventId;
	@CreatedDate
	private Long creationDate;
	private String authorId;
	private EventScope eventScope;
	private String eventScopeId;
	private ArtifactScope artifactScope;
	private String artifactId;	
	private String description;		
	@Indexed
	private String recipient;
	private boolean read;
	
	public static Notification fromEvent(Event event, String recipient) {
		Notification notification = new Notification();
		notification.setEventId(event.getId());
		notification.setCreationDate(event.getCreationDate());
		notification.setAuthorId(event.getAuthorId());
		notification.setEventScope(event.getEventScope());
		notification.setEventScopeId(event.getEventScopeId());
		notification.setArtifactScope(event.getArtifactScope());
		notification.setArtifactId(event.getArtifactId());
		notification.setDescription(event.getDescription());
		notification.setRecipient(recipient);
		notification.setRead(false);
		return notification;
	}

	public static Notification randomNotification(){
		Notification notification = new Notification();
		
		notification.setId(RandomGenerator.randomString());		
		notification.setEventId(RandomGenerator.randomString());
		notification.setCreationDate(RandomGenerator.randomLong());
		notification.setAuthorId(RandomGenerator.randomString());
		notification.setEventScope(RandomGenerator.randomEnum(EventScope.class));
		notification.setEventScopeId(RandomGenerator.randomString());
		notification.setArtifactScope(RandomGenerator.randomEnum(ArtifactScope.class));
		notification.setArtifactId(RandomGenerator.randomString());
		notification.setDescription(RandomGenerator.randomString());
		notification.setRecipient(RandomGenerator.randomString());
		notification.setRead(RandomGenerator.randomBoolean());

		return notification;
	}
	
}
