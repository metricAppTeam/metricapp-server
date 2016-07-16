package metricapp.service.spec.controller;

import metricapp.dto.event.EventCrudDTO;
import metricapp.dto.event.EventDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface EventCRUDInterface {
	
	public EventCrudDTO getEventById(String id) throws BadInputException, NotFoundException;
	public EventCrudDTO getEventByAuthorId(String authorId) throws BadInputException, NotFoundException;
	public EventCrudDTO getEventByScope(String scope) throws BadInputException, NotFoundException;
	public EventCrudDTO getEventByArtifactId(String artifactId) throws BadInputException, NotFoundException;
	public EventCrudDTO createEvent(EventDTO dto) throws BadInputException;
	
}
