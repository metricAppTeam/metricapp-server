package metricapp.service.spec.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;

@RepositoryRestResource(exported = false)
public interface EventRepository extends MongoRepository<Event, String> {
	
	public Event findEventById(String id);
	
	public List<Event> findEventByAuthorId(String authorId);
	
	public List<Event> findEventByArtifactScope(ArtifactScope artifactScope);	
	
	public List<Event> findEventByArtifactId(String artifactId);
	
}
