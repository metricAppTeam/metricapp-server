package metricapp.service.spec.repository.OLD;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.OLD.user.simple.UserSimple;

@RepositoryRestResource(exported = false)
public interface UserSimpleRepository extends MongoRepository<UserSimple, String> {
	
	public UserSimple findByUsername(String username);
	
}
