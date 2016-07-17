package metricapp.dto.event;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class EventCrudDTO extends SimpleDTO {
	
	private static final long serialVersionUID = -6469593544299734647L;
	
	private long count;	
	public ArrayList<EventDTO> eventsDTO;	
	
	public EventCrudDTO() {
		this.setEventsDTO(new ArrayList<EventDTO>());
	}
	
	public void addEventToList(EventDTO event) {
		try {
			this.eventsDTO.add(event);
		} catch(NullPointerException e) {
			this.eventsDTO = new ArrayList<EventDTO>();
			this.eventsDTO.add(event);
		} finally {
			this.count++;
		}
	}
}
