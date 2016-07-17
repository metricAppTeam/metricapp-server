package metricapp.dto.event;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;
import metricapp.dto.event.EventDTO;
import metricapp.entity.event.Event;

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
	
	public void addEventDTO(EventDTO event) {
		if (this.eventsDTO == null) {
			this.eventsDTO = new ArrayList<EventDTO>();
		}
		this.eventsDTO.add(event);		
		this.count = this.eventsDTO.size();
	}
	
	public void addAllEventDTO(List<EventDTO> events) {
		if (this.eventsDTO == null) {
			this.eventsDTO = new ArrayList<EventDTO>();
		}
		for (EventDTO event : events) {
			this.eventsDTO.add(event);
		}
		this.count = this.eventsDTO.size();
	}
	
	public void addEvent(Event event, ModelMapper mapper) {
		if (this.eventsDTO == null) {
			this.eventsDTO = new ArrayList<EventDTO>();
		}
		EventDTO eventDTO = mapper.map(event, EventDTO.class);
		this.eventsDTO.add(eventDTO);		
		this.count = this.eventsDTO.size();
	}
	
	public void addAllEvent(List<Event> events, ModelMapper mapper) {
		if (this.eventsDTO == null) {
			this.eventsDTO = new ArrayList<EventDTO>();
		}
		for (Event event : events) {
			EventDTO eventDTO = mapper.map(event, EventDTO.class);
			this.eventsDTO.add(eventDTO);
		}
		this.count = this.eventsDTO.size();
	}
}
