package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.event.EventCrudDTO;
import metricapp.dto.event.EventDTO;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventScope;
import metricapp.entity.notification.Notification;
import metricapp.entity.notification.box.NotificationBox;
import metricapp.entity.topic.Topic;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.EventCRUDInterface;
import metricapp.service.spec.repository.EventRepository;
import metricapp.service.spec.repository.NotificationBoxRepository;
import metricapp.service.spec.repository.NotificationRepository;
import metricapp.service.spec.repository.TopicRepository;

@Service
public class EventCRUDController implements EventCRUDInterface {

	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private TopicRepository topicRepo;
	
	@Autowired 
	private NotificationBoxRepository nboxRepo;

	@Autowired 
	private NotificationRepository notificationRepo;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public EventCrudDTO getAllEvents() {
		List<Event> events = eventRepo.findAll();
		
		EventCrudDTO crud = new EventCrudDTO();
		
		crud.setRequest("GET ALL Events");
		for (Event event : events) {
			crud.addEventToList(modelMapperFactory.getStandardModelMapper().map(event, EventDTO.class));
		}
		
		return crud;
	}
	
	@Override
	public EventCrudDTO getEventById(String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Event id cannot be null");
		}
		
		Event event = eventRepo.findEventById(id);
		
		if (event == null) {
			throw new NotFoundException("Cannot find Event with id=" + id);
		}
		
		EventCrudDTO crud = new EventCrudDTO();
		
		crud.setRequest("GET Event WITH id=" + id);
		crud.addEventToList(modelMapperFactory.getStandardModelMapper().map(event, EventDTO.class));
		
		return crud;
	}

	
	@Override
	public EventCrudDTO getEventByAuthorId(String authorId) throws BadInputException, NotFoundException {
		if (authorId == null) {
			throw new BadInputException("Event authorId cannot be null");
		}
		
		List<Event> events = eventRepo.findEventByAuthorId(authorId);
		
		if (events.size() == 0) {
			throw new NotFoundException("Cannot find Event with authorId=" + authorId);
		}
		
		EventCrudDTO crud = new EventCrudDTO();
		crud.setRequest("GET Event WITH authorId=" + authorId);
		
		for (Event event : events) {
			crud.addEventToList(modelMapperFactory.getStandardModelMapper().map(event, EventDTO.class));
		}		
		
		return crud;
	}

	@Override
	public EventCrudDTO getEventByScope(String scope) throws BadInputException, NotFoundException {
		if (scope == null) {
			throw new BadInputException("Event scope cannot be null");
		}
		
		List<Event> events = eventRepo.findEventByScope(EventScope.valueOf(scope));
		
		if (events.size() == 0) {
			throw new NotFoundException("Cannot find Event with scope=" + scope);
		}
		
		EventCrudDTO crud = new EventCrudDTO();
		crud.setRequest("GET Event WITH scope=" + scope);
		
		for (Event event : events) {
			crud.addEventToList(modelMapperFactory.getStandardModelMapper().map(event, EventDTO.class));
		}		
		
		return crud;
	}

	@Override
	public EventCrudDTO getEventByArtifactId(String artifactId) throws BadInputException, NotFoundException {
		if (artifactId == null) {
			throw new BadInputException("Event artifactId cannot be null");
		}
		
		List<Event> events = eventRepo.findEventByArtifactId(artifactId);
		
		if (events.size() == 0) {
			throw new NotFoundException("Cannot find Event with artifactId=" + artifactId);
		}
		
		EventCrudDTO crud = new EventCrudDTO();
		crud.setRequest("GET Event WITH artifactId=" + artifactId);
		
		for (Event event : events) {
			crud.addEventToList(modelMapperFactory.getStandardModelMapper().map(event, EventDTO.class));
		}		
		
		return crud;
	}

	@Override
	public EventCrudDTO createEvent(@Nonnull EventDTO dto) throws BadInputException {
		
		if (dto.getId() != null) {
			throw new BadInputException("Event id cannot be set manually");
		}
		
		if (dto.getCreationDate() != null) {
			throw new BadInputException("Event creation date cannot be set manually");
		}
		
		if (dto.getAuthorId() == null || dto.getScope() == null || 
				dto.getArtifactId() == null || dto.getDescription() == null) {
			throw new BadInputException("Event must have an author id, a scope, an artifact id and a description");
		}		
		
		Event event = modelMapperFactory.getStandardModelMapper().map(dto, Event.class);
		
		event.setCreationDate(LocalDate.now());
		
		event = eventRepo.insert(event);
		
		String topicName = event.getTopicName();
		
		Topic topic = topicRepo.findTopicByName(topicName);
		
		if (topic == null) {
			topic = new Topic();
			topic.setName(topicName);
			topic.setCreationDate(LocalDate.now());
			topic.setSubscribers(new ArrayList<String>());
			topicRepo.insert(topic);
			//implement topic queue
		} else {
			for (String subscriber : topic.getSubscribers()) {
				Notification notification = Notification.fromEvent(event, subscriber);
				NotificationBox nbox = nboxRepo.findByOwnerId(subscriber);
				if (nbox != null) {
					nbox.getNotifications().add(notification);
					nbox.setLastPushDate(LocalDate.now());
					nboxRepo.save(nbox);
					notificationRepo.save(notification);
				}
			}
		}		
		
		EventCrudDTO dtoCRUD = new EventCrudDTO();
		dtoCRUD.setRequest("CREATE Event");
		dtoCRUD.addEventToList(modelMapperFactory.getStandardModelMapper().map(event, EventDTO.class));
		
		return dtoCRUD;
	}

}
