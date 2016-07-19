package metricapp.dto.notification.box;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import metricapp.entity.notification.Notification;

@Data
public class NotificationBoxDTO implements Serializable {

	private static final long serialVersionUID = 7188943069727689057L;
	
	public String id;
	public String ownerId;
	private List<Notification> notifications;
	
}
