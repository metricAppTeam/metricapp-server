package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.topic.TopicCrudDTO;
import metricapp.dto.topic.TopicDTO;
import metricapp.entity.topic.Topic;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.TopicCRUDInterface;
import metricapp.service.spec.repository.TopicRepository;

@Service
public class TopicCRUDController implements TopicCRUDInterface {

	@Autowired
	private TopicRepository topicRepo;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public TopicCrudDTO createTopic(@Nonnull TopicDTO dto) throws BadInputException {
		
		if (dto.getId() != null) {
			throw new BadInputException("Topic id cannot be set manually");
		}
		
		if (dto.getCreationDate() != null) {
			throw new BadInputException("Topic creation date canot be set manually");
		}
		
		if (dto.getName() == null) {
			throw new BadInputException("Topic name cannot be null");
		}		
		
		Topic topic = modelMapperFactory.getStandardModelMapper().map(dto, Topic.class);
		topic.setCreationDate(LocalDate.now());
		topic.setSubscribers(new ArrayList<String>());
		
		topic = topicRepo.insert(topic);
		
		TopicCrudDTO crud = new TopicCrudDTO();
		crud.setRequest("CREATE Topic WITH id=" + topic.getId());
		crud.addTopic(topic, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public TopicCrudDTO getAllTopics() throws NotFoundException {		
		List<Topic> topics = topicRepo.findAll();
		
		if (topics.isEmpty()) {
			throw new NotFoundException("Cannot find Topic");
		}
		
		TopicCrudDTO crud = new TopicCrudDTO();		
		crud.setRequest("GET ALL Topics");
		crud.addAllTopic(topics, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public TopicCrudDTO getTopicById(String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Topic id cannot be null");
		}
		
		Topic topic = topicRepo.findTopicById(id);
		
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with id=" + id);
		}
		
		TopicCrudDTO crud = new TopicCrudDTO();
		crud.setRequest("GET Topic WITH id=" + id);
		crud.addTopic(topic, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public TopicCrudDTO getTopicByName(String name) throws BadInputException, NotFoundException {
		if (name == null) {
			throw new BadInputException("Topic name cannot be null");
		}
		
		Topic topic = topicRepo.findTopicByName(name);
		
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with name=" + name);
		}
		
		TopicCrudDTO crud = new TopicCrudDTO();
		crud.setRequest("GET Topic WITH name=" + name);
		crud.addTopic(topic, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}	

	@Override
	public TopicCrudDTO patchTopicAddSubscribers(@Nonnull TopicDTO dto) throws BadInputException, NotFoundException {
		Topic topic;
		if (dto.getId() != null) {
			topic = topicRepo.findTopicById(dto.getId());			
		} else if (dto.getName() != null) {
			topic = topicRepo.findTopicByName(dto.getName());
		} else {
			throw new BadInputException("Topic id or name cannot be null");
		}
		
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with " + ((dto.getId()!=null)?"id="+dto.getId():"name="+dto.getName()));
		}
		
		topic.getSubscribers().addAll(dto.getSubscribers());
		
		topic = topicRepo.save(topic);
		
		TopicCrudDTO crud = new TopicCrudDTO();
		crud.setRequest("PATCH (subscribers+=" + dto.getSubscribers() + ") Topic WITH " + ((dto.getId()!=null)?"id="+dto.getId():"name="+dto.getName()));		
		crud.addTopic(topic, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public TopicCrudDTO patchTopicRemoveSubscribers(@Nonnull TopicDTO dto) throws BadInputException, NotFoundException {
		Topic topic;
		if (dto.getId() != null) {
			topic = topicRepo.findTopicById(dto.getId());			
		} else if (dto.getName() != null) {
			topic = topicRepo.findTopicByName(dto.getName());
		} else {
			throw new BadInputException("Topic id or name cannot be null");
		}
		
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with " + ((dto.getId()!=null)?"id="+dto.getId():"name="+dto.getName()));
		}
		
		topic.getSubscribers().removeAll(dto.getSubscribers());
		
		topic = topicRepo.save(topic);
		
		TopicCrudDTO crud = new TopicCrudDTO();
		crud.setRequest("PATCH (subscribers+=" + dto.getSubscribers() + ") Topic WITH " + ((dto.getId()!=null)?"id="+dto.getId():"name="+dto.getName()));	
		crud.addTopic(topic, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	@Override
	public TopicCrudDTO deleteTopicById(String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Topic id cannot be null");
		}
		
		Topic topic = topicRepo.deleteTopicById(id);
		
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with id=" + id);
		}
		
		TopicCrudDTO crud = new TopicCrudDTO();
		crud.setRequest("DELETE Topic WITH id=" + id);
		crud.addTopic(topic, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	@Override
	public TopicCrudDTO deleteTopicByName(String name) throws BadInputException, NotFoundException {
		if (name == null) {
			throw new BadInputException("Topic name cannot be null");
		}
		
		Topic topic = topicRepo.deleteTopicByName(name);
		
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with name=" + name);
		}
		
		TopicCrudDTO crud = new TopicCrudDTO();
		crud.setRequest("DELETE Topic WITH name=" + name);
		crud.addTopic(topic, modelMapperFactory.getStandardModelMapper());
		
		return crud;	
	}	

}
