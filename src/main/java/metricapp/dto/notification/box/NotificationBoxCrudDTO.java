package metricapp.dto.notification.box;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;
import metricapp.entity.notification.box.NotificationBox;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class NotificationBoxCrudDTO extends SimpleDTO {

	private static final long serialVersionUID = -197438466044460306L;
	
	private long count;	
	public ArrayList<NotificationBoxDTO> nboxesDTO;	
	
	public NotificationBoxCrudDTO() {
		this.setNboxesDTO(new ArrayList<NotificationBoxDTO>());
	}
	
	public void addNotificatioBoxDTO(NotificationBoxDTO nbox) {
		if (this.nboxesDTO == null) {
			this.nboxesDTO = new ArrayList<NotificationBoxDTO>();
		}
		this.nboxesDTO.add(nbox);		
		this.count = this.nboxesDTO.size();
	}
	
	public void addAllNotificationBoxDTO(List<NotificationBoxDTO> nboxes) {
		if (this.nboxesDTO == null) {
			this.nboxesDTO = new ArrayList<NotificationBoxDTO>();
		}
		for (NotificationBoxDTO nbox : nboxes) {
			this.nboxesDTO.add(nbox);
		}
		this.count = this.nboxesDTO.size();
	}
	
	public void addNotificationBox(NotificationBox nbox, ModelMapper mapper) {
		if (this.nboxesDTO == null) {
			this.nboxesDTO = new ArrayList<NotificationBoxDTO>();
		}
		NotificationBoxDTO nboxDTO = mapper.map(nbox, NotificationBoxDTO.class);
		this.nboxesDTO.add(nboxDTO);		
		this.count = this.nboxesDTO.size();
	}
	
	public void addAllNotificationBox(List<NotificationBox> nboxes, ModelMapper mapper) {
		if (this.nboxesDTO == null) {
			this.nboxesDTO = new ArrayList<NotificationBoxDTO>();
		}
		for (NotificationBox nbox : nboxes) {
			NotificationBoxDTO nboxDTO = mapper.map(nbox, NotificationBoxDTO.class);
			this.nboxesDTO.add(nboxDTO);
		}
		this.count = this.nboxesDTO.size();
	}
}
