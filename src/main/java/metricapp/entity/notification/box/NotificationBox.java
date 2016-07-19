package metricapp.entity.notification.box;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.entity.notification.Notification;
import metricapp.utility.RandomGenerator;

@Data
@TypeAlias("metricapp.NotificationBox")
@Document(collection = "inboxes")
public class NotificationBox {
	
	@Id
	private String id;
	private String ownerId;
	private List<Notification> notifications;

	public static NotificationBox randomNotificationBox(){
		NotificationBox box = new NotificationBox();
		
		long now = Calendar.getInstance().getTimeInMillis();
		
		box.setId(RandomGenerator.randomString());		
		box.setNotifications(new ArrayList<Notification>());
		for (int i = 0; i < RandomGenerator.randomInt(); i++) {
			Notification notification = Notification.randomNotification();
			if (notification.getCreationDate() > now) {
				notification.setRead(false);
			}
			box.getNotifications().add(notification);
		}

		return box;
	}

}
