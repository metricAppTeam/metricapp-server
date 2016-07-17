package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.topic.Topic;

@RepositoryRestResource(exported = false)
public interface TopicRepository extends MongoRepository<Topic, String> {
	
	public Topic findTopicById(String id);
	public Topic findTopicByName(String name);
	
	public Topic deleteTopicById(String id);
	public Topic deleteTopicByName(String name);
	
}
