package metricapp.entity.notification.box;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.entity.notification.Notification;
import metricapp.utility.RandomGenerator;

@Data
@TypeAlias("metricapp.NotificationBox")
@Document(collection = "notification-boxes")
public class NotificationBox {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String ownerId;
	@CreatedDate
	private LocalDate creationDate;
	@LastModifiedDate
	private LocalDate lastPushDate;
	@DBRef
	private List<Notification> notifications;

	public static NotificationBox randomNotificationBox(){
		NotificationBox box = new NotificationBox();
		
		LocalDate now = LocalDate.now();
		
		box.setId(RandomGenerator.randomString());		
		box.setCreationDate(RandomGenerator.randomLocalDate());
		box.setLastPushDate(RandomGenerator.randomLocalDate());
		box.setNotifications(new ArrayList<Notification>());
		for (int i = 0; i < RandomGenerator.randomInt(); i++) {
			Notification notification = Notification.randomNotification();
			if (notification.getCreationDate().isAfter(now)) {
				notification.setRead(false);
			}
			box.getNotifications().add(notification);
		}

		return box;
	}

}
