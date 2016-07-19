package metricapp.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.event.EventCrudDTO;
import metricapp.dto.event.EventDTO;
import metricapp.dto.topic.TopicCrudDTO;
import metricapp.dto.topic.TopicDTO;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.EventScope;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.NotificationServiceInterface;
import metricapp.service.spec.controller.EventCRUDInterface;
import metricapp.service.spec.controller.TopicCRUDInterface;

@Service
public class NotificationService implements NotificationServiceInterface {
	
	@Autowired
	private TopicCRUDInterface topicController;
	
	@Autowired
	private EventCRUDInterface eventController;

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

}
