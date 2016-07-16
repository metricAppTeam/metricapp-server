package metricapp.entity.notification;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import metricapp.utility.RandomGenerator;

@Data
@Document
public class Notification {	
	
	@Id
	private String id;
	@CreatedDate
	private LocalDate creationDate;
	private String authorId;
	private NotificationScope scope;
	private String artifactId;	
	private String description;	
	private boolean isRead;

	public static Notification randomNotification(){
		Notification notification = new Notification();
		
		notification.setId(RandomGenerator.randomString());		
		notification.setCreationDate(RandomGenerator.randomLocalDate());
		notification.setScope(RandomGenerator.randomEnum(NotificationScope.class));
		notification.setArtifactId(RandomGenerator.randomString());
		notification.setDescription(RandomGenerator.randomString());
		notification.setAuthorId(RandomGenerator.randomString());
		notification.setRead(RandomGenerator.randomBoolean());

		return notification;
	}

	
}
