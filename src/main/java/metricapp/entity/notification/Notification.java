package metricapp.entity.notification;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventScope;
import metricapp.utility.RandomGenerator;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@TypeAlias("metricapp.Notification")
@Document(collection = "notifications")
public class Notification extends Event {	
	
	@Indexed
	private String recipient;
	private boolean read;
	
	public static Notification fromEvent(Event event, String recipient) {
		Notification notification = new Notification();
		notification.setId(event.getId());
		notification.setCreationDate(event.getCreationDate());
		notification.setAuthorId(event.getAuthorId());
		notification.setScope(event.getScope());
		notification.setArtifactId(event.getArtifactId());
		notification.setDescription(event.getDescription());
		notification.setRecipient(recipient);
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
		notification.setRecipient(RandomGenerator.randomString());
		notification.setRead(RandomGenerator.randomBoolean());

		return notification;
	}
	
}
