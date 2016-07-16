package metricapp.dto.notification.box;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;
import metricapp.entity.notification.Notification;

@Getter
@Setter()
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class NotificationBoxDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 7188943069727689057L;
	
	public String id;
	public String ownerId;
	public LocalDate creationDate;
	private LocalDate lastPushDate;
	private List<Notification> notifications;
	
	public NotificationBoxDTO() {
		super();
	}
	
}
