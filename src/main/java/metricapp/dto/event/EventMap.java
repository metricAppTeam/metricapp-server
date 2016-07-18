package metricapp.dto.event;

import org.modelmapper.PropertyMap;

import metricapp.entity.event.Event;

public class EventMap extends PropertyMap<Event, EventDTO> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setCreationDate(source.getCreationDate());
		map().setAuthorId(source.getAuthorId());
		map().setScope(source.getScope());
		map().setArtifactId(source.getArtifactId());
		map().setDescription(source.getDescription());
	}
	
}
