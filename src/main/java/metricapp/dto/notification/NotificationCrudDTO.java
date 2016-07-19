package metricapp.dto.notification;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;
import metricapp.entity.notification.Notification;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class NotificationCrudDTO extends SimpleDTO {
	
	private static final long serialVersionUID = 8336783368496627133L;
	
	private long count;	
	public ArrayList<NotificationDTO> notificationsDTO;	
	
	public NotificationCrudDTO() {
		this.setNotificationsDTO(new ArrayList<NotificationDTO>());
	}
	
	public void addNotificationDTO(NotificationDTO notification) {
		if (this.notificationsDTO == null) {
			this.notificationsDTO = new ArrayList<NotificationDTO>();
		}
		this.notificationsDTO.add(notification);		
		this.count = this.notificationsDTO.size();
	}
	
	public void addAllNotificationDTO(List<NotificationDTO> notifications) {
		if (this.notificationsDTO == null) {
			this.notificationsDTO = new ArrayList<NotificationDTO>();
		}
		for (NotificationDTO notification : notifications) {
			this.notificationsDTO.add(notification);
		}
		this.count = this.notificationsDTO.size();
	}
	
	public void addNotification(Notification notification, ModelMapper mapper) {
		if (this.notificationsDTO == null) {
			this.notificationsDTO = new ArrayList<NotificationDTO>();
		}
		NotificationDTO notificationDTO = mapper.map(notification, NotificationDTO.class);
		this.notificationsDTO.add(notificationDTO);		
		this.count = this.notificationsDTO.size();
	}
	
	public void addAllNotification(List<Notification> notifications, ModelMapper mapper) {
		if (this.notificationsDTO == null) {
			this.notificationsDTO = new ArrayList<NotificationDTO>();
		}
		for (Notification notification : notifications) {
			NotificationDTO notificationDTO = mapper.map(notification, NotificationDTO.class);
			this.notificationsDTO.add(notificationDTO);
		}
		this.count = this.notificationsDTO.size();
	}
	
}
