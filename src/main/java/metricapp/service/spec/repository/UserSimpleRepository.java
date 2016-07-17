package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import metricapp.entity.user.UserSimple;

@RepositoryRestResource(exported = false)
public interface UserSimpleRepository extends MongoRepository<UserSimple, String> {
	
	public UserSimple findByUsername(String username);
	
	public UserSimple findByUsernameAndPassword(String username, String password);
}
