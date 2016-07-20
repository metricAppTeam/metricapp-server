package metricapp.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.topic.TopicCrudDTO;
import metricapp.dto.topic.TopicDTO;
import metricapp.entity.event.Event;
import metricapp.entity.notification.Notification;
import metricapp.entity.notification.box.NotificationBox;
import metricapp.entity.topic.Topic;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.NotificationServiceInterface;
import metricapp.service.spec.controller.EventCRUDInterface;
import metricapp.service.spec.controller.TopicCRUDInterface;
import metricapp.service.spec.repository.EventRepository;
import metricapp.service.spec.repository.NotificationBoxRepository;
import metricapp.service.spec.repository.TopicRepository;

@Service
public class NotificationService implements NotificationServiceInterface {
	
	@Autowired
	private TopicCRUDInterface topicController;
	
	@Autowired
	private EventCRUDInterface eventController;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private TopicRepository topicRepo;
	
	@Autowired 
	private NotificationBoxRepository nboxRepo;

	@Override
	public boolean addTopic(String topicName) {
		TopicDTO topicDTO = new TopicDTO();
		topicDTO.setName(topicName);		
		TopicCrudDTO crud = null;
		
		try {
			crud = topicController.createTopic(topicDTO);
		} catch (BadInputException e) {
			e.printStackTrace();
		}
		
		return crud != null;
	}

	@Override
	public boolean removeTopic(String topicName) {		
		TopicCrudDTO crud = null;
		
		try {
			crud = topicController.deleteTopicByName(topicName);
		} catch (BadInputException | NotFoundException e) {
			e.printStackTrace();
		}
		
		return crud != null;
	}

	@Override
	public boolean addSubscribers(String topicName, List<String> subscribers) {
		TopicDTO topicDTO = new TopicDTO();
		topicDTO.setName(topicName);
		topicDTO.setSubscribers(subscribers);
		
		TopicCrudDTO crud = null;
		try {
			crud = topicController.patchTopicAddSubscribers(topicDTO);
		} catch (BadInputException | NotFoundException | DBException e) {
			e.printStackTrace();
		}
		
		return crud != null;
	}

	@Override
	public boolean removeSubscribers(String topicName, List<String> subscribers) {
		TopicDTO topicDTO = new TopicDTO();
		topicDTO.setName(topicName);
		topicDTO.setSubscribers(subscribers);
		
		TopicCrudDTO crud = null;
		try {
			crud = topicController.patchTopicRemoveSubscribers(topicDTO);
		} catch (BadInputException | NotFoundException | DBException e) {
			e.printStackTrace();
		}
		
		return crud != null;
	}
/*
	@Override
	public boolean publish(String authorId, EventScope eventScope, String eventScopeId, ArtifactScope artifactScope, String artifactId, String description) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setAuthorId(authorId);
		eventDTO.setEventScope(eventScope);
		eventDTO.setEventScopeId(eventScopeId);
		eventDTO.setArtifactScope(artifactScope);
		eventDTO.setArtifactId(artifactId);
		eventDTO.setDescription(description);
		
		EventCrudDTO crud = null;
		try {
			crud = eventController.createEvent(authorId, eventDTO);
		} catch (BadInputException e) {
			e.printStackTrace();
		}
		
		return crud != null;
	}
	
	public boolean publish(String topicName, EventScope eventScope, String description) {
		Event event = new Event();
		event.setEventScope(eventScope);
		event.setDescription(description);
		event.setCreationDate(Calendar.getInstance().getTimeInMillis());		
		
		Topic topic = topicRepo.findTopicByName(topicName);
		
		if (topic == null) {
			return false;
		}
		
		event = eventRepo.insert(event);
		
		for (String subscriber : topic.getSubscribers()) {
			if (subscriber.equals(event.getAuthorId())) continue;
			Notification notification = Notification.fromEvent(event, subscriber);
			NotificationBox nbox = nboxRepo.findByOwnerId(subscriber);
			if (nbox == null) {			
				nbox = new NotificationBox();
				nbox.setOwnerId(subscriber);
				nbox.setNotifications(new ArrayList<Notification>());
			}
			nbox.getNotifications().add(notification);
			nboxRepo.save(nbox);
		}
		
		return true;
	}
	*/

	@Override
	public boolean publish(String topicName, Event event) {
		Topic topic = topicRepo.findTopicByName(topicName);
		
		if (topic == null) {
			return false;
		}
		
		event = eventRepo.insert(event);
		
		for (String subscriber : topic.getSubscribers()) {
			if (event.getAuthorId() != null) {
				if (subscriber.equals(event.getAuthorId())) continue;
			}			
			Notification notification = Notification.fromEvent(event, subscriber);
			NotificationBox nbox = nboxRepo.findByOwnerId(subscriber);
			if (nbox == null) {			
				nbox = new NotificationBox();
				nbox.setOwnerId(subscriber);
				nbox.setNotifications(new ArrayList<Notification>());
			}
			nbox.getNotifications().add(notification);
			nboxRepo.save(nbox);
		}
		
		return true;
	}

}
