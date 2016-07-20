package metricapp.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.entity.event.Event;
import metricapp.entity.notification.Notification;
import metricapp.entity.notification.box.NotificationBox;
import metricapp.entity.topic.Topic;
import metricapp.service.spec.NotificationServiceInterface;
import metricapp.service.spec.repository.EventRepository;
import metricapp.service.spec.repository.NotificationBoxRepository;
import metricapp.service.spec.repository.TopicRepository;

@Service
public class NotificationService implements NotificationServiceInterface {
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private TopicRepository topicRepo;
	
	@Autowired 
	private NotificationBoxRepository nboxRepo;
	
	@Autowired
	private void setInstance(){
		instance = this;
	}
	
	public static NotificationServiceInterface getInstance(){
		return instance;
	}
	
	public static NotificationServiceInterface instance;

	@Override
	public void addTopic(String topicName) {
		Topic topic = new Topic();
		topic.setName(topicName);
		topic.setSubscribers(new ArrayList<String>());
		
		topicRepo.save(topic);
	}

	@Override
	public void removeTopic(String topicName) {	
		topicRepo.deleteTopicByName(topicName);
	}

	@Override
	public boolean addSubscribers(String topicName, List<String> subscribers) {
		Topic topic = topicRepo.findTopicByName(topicName);
		
		if (topic == null) {
			return false;
		}
		
		topic.getSubscribers().addAll(subscribers);
		
		topicRepo.save(topic);

		return true;
	}

	@Override
	public boolean removeSubscribers(String topicName, List<String> subscribers) {
		Topic topic = topicRepo.findTopicByName(topicName);
		
		if (topic == null) {
			return false;
		}
		
		for (String subscriber : subscribers) {
			topic.getSubscribers().remove(subscriber);
		}
		
		topicRepo.save(topic);

		return true;
	}
	
	@Override
	public boolean publish(String topicName, Event event) {
		Topic topic = topicRepo.findTopicByName(topicName);
		
		if (topic == null) {
			return false;
		}
		
		event.setCreationDate(Calendar.getInstance().getTimeInMillis());
		event = eventRepo.insert(event);
		
		for (String subscriber : topic.getSubscribers()) {
			if (event.getAuthorId() != null) {
				if (subscriber.equals(event.getAuthorId())) {
					continue;
				}
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

	

}
