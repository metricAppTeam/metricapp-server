package metricapp.dto.notification;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.MessageDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class NotificationCrudDTO extends MessageDTO {
	
	private static final long serialVersionUID = 8336783368496627133L;
	
	private long count;	
	public ArrayList<NotificationDTO> notificationsDTO;
	
	
	public NotificationCrudDTO() {
		this.setNotificationsDTO(new ArrayList<NotificationDTO>());
	}
	
	public void addNotificationToList(NotificationDTO notification) {
		try {
			this.notificationsDTO.add(notification);
		} catch(NullPointerException e){
			this.notificationsDTO = new ArrayList<NotificationDTO>();
			this.notificationsDTO.add(notification);
		}		
	}
}
