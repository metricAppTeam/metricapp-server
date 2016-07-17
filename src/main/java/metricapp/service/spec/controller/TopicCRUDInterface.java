package metricapp.service.spec.controller;

import metricapp.dto.topic.TopicCrudDTO;
import metricapp.dto.topic.TopicDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.NotFoundException;

public interface TopicCRUDInterface {
	
	public TopicCrudDTO createTopic(TopicDTO dto) throws BadInputException;
	
	public TopicCrudDTO getAllTopics() throws NotFoundException;
	public TopicCrudDTO getTopicById(String id) throws BadInputException, NotFoundException;
	public TopicCrudDTO getTopicByName(String name) throws BadInputException, NotFoundException;
	
	public TopicCrudDTO patchTopicAddSubscribers(TopicDTO dto) throws BadInputException, NotFoundException, DBException;
	public TopicCrudDTO patchTopicRemoveSubscribers(TopicDTO dto) throws BadInputException, NotFoundException, DBException;
	
	public TopicCrudDTO deleteTopicById(String id) throws BadInputException, NotFoundException;
	public TopicCrudDTO deleteTopicByName(String name) throws BadInputException, NotFoundException;
		
}
