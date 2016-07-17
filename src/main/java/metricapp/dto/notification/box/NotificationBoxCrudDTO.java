package metricapp.dto.notification.box;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class NotificationBoxCrudDTO extends SimpleDTO {

	private static final long serialVersionUID = -197438466044460306L;
	
	private long count;	
	public ArrayList<NotificationBoxDTO> notificationBoxesDTO;	
	
	public NotificationBoxCrudDTO() {
		this.setNotificationBoxesDTO(new ArrayList<NotificationBoxDTO>());
	}
	
	public void addNotificationBoxToList(NotificationBoxDTO notificationbox) {
		try {
			this.notificationBoxesDTO.add(notificationbox);
		} catch(NullPointerException e) {
			this.notificationBoxesDTO = new ArrayList<NotificationBoxDTO>();
			this.notificationBoxesDTO.add(notificationbox);
		} finally {
			this.count++;
		}	
	}
}
