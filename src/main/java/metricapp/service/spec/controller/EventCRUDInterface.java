package metricapp.service.spec.controller;

import metricapp.dto.event.EventCrudDTO;
import metricapp.dto.event.EventDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface EventCRUDInterface {
	
	public EventCrudDTO createEvent(String username, EventDTO dto) throws BadInputException;
	
	public EventCrudDTO getAllEvents() throws NotFoundException;
	public EventCrudDTO getEventById(String id) throws BadInputException, NotFoundException;
	public EventCrudDTO getEventsByAuthorId(String authorId) throws BadInputException, NotFoundException;
	public EventCrudDTO getEventsByEventScope(String eventScope) throws BadInputException, NotFoundException;
	public EventCrudDTO getEventsByEventScopeId(String eventScopeId) throws BadInputException, NotFoundException;
	public EventCrudDTO getEventsByArtifactScope(String artifactScope) throws BadInputException, NotFoundException;
	public EventCrudDTO getEventsByArtifactId(String artifactId) throws BadInputException, NotFoundException;
	
	public EventCrudDTO deleteAllEvents();
	
}
