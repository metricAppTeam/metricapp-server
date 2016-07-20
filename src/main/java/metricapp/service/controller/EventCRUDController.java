package metricapp.service.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.event.EventCrudDTO;
import metricapp.dto.event.EventDTO;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.entity.notification.Notification;
import metricapp.entity.notification.box.NotificationBox;
import metricapp.entity.topic.Topic;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.EventCRUDInterface;
import metricapp.service.spec.repository.EventRepository;
import metricapp.service.spec.repository.NotificationBoxRepository;
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
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public EventCrudDTO createEvent(String username, String topicName, @Nonnull EventDTO dto) throws BadInputException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (topicName == null) {
			throw new BadInputException("Topic name cannot be null");
		}
		
		if (dto.getId() != null) {
			throw new BadInputException("Event id cannot be set manually");
		}
		
		if (dto.getCreationDate() != null) {
			throw new BadInputException("Event creation date cannot be set manually");
		}
		
		if (dto.getEventPhase().equals(EventPhase.PHASE2_2) && dto.getAuthorId() == null) {
			throw new BadInputException("Event from Phase2.2 must have an author id");
		}
		
		if (dto.getArtifactScope() == null) {
			throw new BadInputException("Event must have an artifact scope");
		}
		
		if (dto.getArtifactId() == null) {
			throw new BadInputException("Event must have an artifact id");
		}
		
		if (dto.getDescription() == null) {
			throw new BadInputException("Event must have a description");
		}	
		
		Event event = modelMapperFactory.getStandardModelMapper().map(dto, Event.class);		
		event.setCreationDate(Calendar.getInstance().getTimeInMillis());
		
		event = eventRepo.insert(event);
		
		Topic topic = topicRepo.findTopicByName(topicName);
		
		if (topic == null) {
			topic = new Topic();
			topic.setName(topicName);
			topic.setCreationDate(Calendar.getInstance().getTimeInMillis());
			topic.setSubscribers(new ArrayList<String>());
			topicRepo.insert(topic);
			//implement topic queue
		} else {
			for (String subscriber : topic.getSubscribers()) {
				if (event.getAuthorId() != null) {
					if (subscriber.equals(event.getAuthorId())) continue;
				}				
				Notification notification = Notification.fromEvent(event, subscriber);
				NotificationBox nbox = nboxRepo.findByOwnerId(subscriber);
				// MODIFIED FOR BACKWARD COMPATIBILITY (TEMPORARY)
				if (nbox == null) {			
					//throw new NotFoundException("The user with username " + username + " does not have any nbox");
					nbox = new NotificationBox();
					nbox.setOwnerId(subscriber);
					nbox.setNotifications(new ArrayList<Notification>());
				}
				nbox.getNotifications().add(notification);
				nboxRepo.save(nbox);
			}
		}		
		
		EventCrudDTO crud = new EventCrudDTO();
		crud.setRequest("CREATE Event WITH id=" + event.getId());
		crud.addEvent(event, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public EventCrudDTO getAllEvents() throws NotFoundException {
		List<Event> events = eventRepo.findAll();
		
		if (events.isEmpty()) {
			throw new NotFoundException("Cannot find Event");
		}
		
		EventCrudDTO crud = new EventCrudDTO();		
		crud.setRequest("GET ALL Event");
		crud.addAllEvent(events, modelMapperFactory.getStandardModelMapper());
		
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
		crud.addEvent(event, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	
	@Override
	public EventCrudDTO getEventsByAuthorId(String authorId) throws BadInputException, NotFoundException {
		if (authorId == null) {
			throw new BadInputException("Event authorId cannot be null");
		}
		
		List<Event> events = eventRepo.findEventByAuthorId(authorId);
		
		if (events.isEmpty()) {
			throw new NotFoundException("Cannot find Event with authorId=" + authorId);
		}
		
		EventCrudDTO crud = new EventCrudDTO();
		crud.setRequest("GET Event WITH authorId=" + authorId);
		crud.addAllEvent(events, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	@Override
	public EventCrudDTO getEventsByArtifactScope(String artifactScope) throws BadInputException, NotFoundException {
		if (artifactScope == null) {
			throw new BadInputException("Event artifactScope cannot be null");
		}
		
		List<Event> events = eventRepo.findEventByArtifactScope(ArtifactScope.valueOf(artifactScope));
		
		if (events.isEmpty()) {
			throw new NotFoundException("Cannot find Event with artifactScope=" + artifactScope);
		}
		
		EventCrudDTO crud = new EventCrudDTO();
		crud.setRequest("GET Event WITH artifactScope=" + artifactScope);
		crud.addAllEvent(events, modelMapperFactory.getStandardModelMapper());		
		
		return crud;
	}

	@Override
	public EventCrudDTO getEventsByArtifactId(String artifactId) throws BadInputException, NotFoundException {
		if (artifactId == null) {
			throw new BadInputException("Event artifactId cannot be null");
		}
		
		List<Event> events = eventRepo.findEventByArtifactId(artifactId);
		
		if (events.isEmpty()) {
			throw new NotFoundException("Cannot find Event with artifactId=" + artifactId);
		}
		
		EventCrudDTO crud = new EventCrudDTO();
		crud.setRequest("GET Event WITH artifactId=" + artifactId);
		crud.addAllEvent(events, modelMapperFactory.getStandardModelMapper());		
		
		return crud;
	}

	@Override
	public EventCrudDTO deleteAllEvents() {
		eventRepo.deleteAll();
		
		EventCrudDTO crud = new EventCrudDTO();
		crud.setRequest("DELETE ALL Events");
		
		return crud;
		
	}

}
