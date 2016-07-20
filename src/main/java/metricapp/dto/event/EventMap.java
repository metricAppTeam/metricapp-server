package metricapp.dto.event;

import org.modelmapper.PropertyMap;

import metricapp.entity.event.Event;

public class EventMap extends PropertyMap<Event, EventDTO> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setCreationDate(source.getCreationDate());
		map().setEventPhase(source.getEventPhase());
		map().setAuthorId(source.getAuthorId());
		map().setArtifactScope(source.getArtifactScope());
		map().setArtifactId(source.getArtifactId());
		map().setDescription(source.getDescription());
	}
	
}
