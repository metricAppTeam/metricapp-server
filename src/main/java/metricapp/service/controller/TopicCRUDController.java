package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;

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
		crud.addTopicToList(modelMapperFactory.getStandardModelMapper().map(topic, TopicDTO.class));
		
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
		crud.addTopicToList(modelMapperFactory.getStandardModelMapper().map(topic, TopicDTO.class));
		
		return crud;
	}

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
		
		dto.setCreationDate(LocalDate.now());
		dto.setSubscribers(new ArrayList<String>());
		Topic newTopic = modelMapperFactory.getStandardModelMapper().map(dto, Topic.class);
		
		TopicCrudDTO dtoCRUD = new TopicCrudDTO();
		dtoCRUD.setRequest("CREATE Topic");
		dtoCRUD.addTopicToList(modelMapperFactory.getStandardModelMapper().map(topicRepo.insert(newTopic), TopicDTO.class));
		
		return dtoCRUD;
	}

	@Override
	public TopicCrudDTO patchTopicByIdAddSubscribers(@Nonnull TopicDTO dto) throws BadInputException, NotFoundException {
		String id = dto.getId();
		if (id == null) {
			throw new BadInputException("Topic id cannot be null");
		}		
		
		Topic topic = topicRepo.findTopicById(id);
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with id=" + id);
		}
		
		topic.getSubscribers().addAll(dto.getSubscribers());
		
		TopicCrudDTO dtoCRUD = new TopicCrudDTO();
		dtoCRUD.setRequest("PATCH (subscribers+=" + dto.getSubscribers() + ") Topic WITH id=" + id);		
		dtoCRUD.addTopicToList(modelMapperFactory.getStandardModelMapper().map(topicRepo.save(topic), TopicDTO.class));
		
		return dtoCRUD;
	}
	
	@Override
	public TopicCrudDTO patchTopicByIdRemoveSubscribers(@Nonnull TopicDTO dto) throws BadInputException, NotFoundException {
		String id = dto.getId();
		if (id == null) {
			throw new BadInputException("Topic id cannot be null");
		}
		
		Topic topic = topicRepo.findTopicById(id);
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with id=" + id);
		}
		
		topic.getSubscribers().removeAll(dto.getSubscribers());
		
		TopicCrudDTO dtoCRUD = new TopicCrudDTO();
		dtoCRUD.setRequest("PATCH (subscribers-=" + dto.getSubscribers() + ") Topic WITH id=" + id);		
		dtoCRUD.addTopicToList(modelMapperFactory.getStandardModelMapper().map(topicRepo.save(topic), TopicDTO.class));
		
		return dtoCRUD;
	}
	
	@Override
	public TopicCrudDTO patchTopicByNameAddSubscribers(@Nonnull TopicDTO dto) throws BadInputException, NotFoundException {
		String name = dto.getName();
		if (name == null) {
			throw new BadInputException("Topic name cannot be null");
		}		
		
		Topic topic = topicRepo.findTopicByName(name);
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with name=" + name);
		}
		
		topic.getSubscribers().addAll(dto.getSubscribers());
		
		TopicCrudDTO dtoCRUD = new TopicCrudDTO();
		dtoCRUD.setRequest("PATCH (subscribers+=" + dto.getSubscribers() + ") Topic WITH name=" + name);		
		dtoCRUD.addTopicToList(modelMapperFactory.getStandardModelMapper().map(topicRepo.save(topic), TopicDTO.class));
		
		return dtoCRUD;
	}
	
	@Override
	public TopicCrudDTO patchTopicByNameRemoveSubscribers(@Nonnull TopicDTO dto) throws BadInputException, NotFoundException {
		String id = dto.getId();
		if (id == null) {
			throw new BadInputException("Topic id cannot be null");
		}
		
		Topic topic = topicRepo.findTopicById(id);
		if (topic == null) {
			throw new NotFoundException("Cannot find Topic with id=" + id);
		}
		
		topic.getSubscribers().removeAll(dto.getSubscribers());
		
		TopicCrudDTO dtoCRUD = new TopicCrudDTO();
		dtoCRUD.setRequest("PATCH (subscribers-=" + dto.getSubscribers() + ") Topic WITH id=" + id);		
		dtoCRUD.addTopicToList(modelMapperFactory.getStandardModelMapper().map(topicRepo.save(topic), TopicDTO.class));
		
		return dtoCRUD;
	}

	@Override
	public void deleteTopicById(String id) throws BadInputException {
		if (id == null) {
			throw new BadInputException("Topic id cannot be null");
		}
		
		topicRepo.delete(id);
	}

	@Override
	public void deleteTopicByName(String name) throws BadInputException {
		if (name == null) {
			throw new BadInputException("Topic name cannot be null");
		}
		
		Topic topic = topicRepo.findTopicByName(name);
		
		if (topic != null) {
			topicRepo.delete(topic);
		}		
	}

}
