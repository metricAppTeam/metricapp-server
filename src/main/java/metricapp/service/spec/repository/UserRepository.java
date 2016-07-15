package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.user.User;
@RepositoryRestResource(exported = false)
public interface UserRepository extends MongoRepository<User, String> {

	public User findUserByUsername(String username);
}
