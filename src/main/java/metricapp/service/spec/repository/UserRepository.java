package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.dto.user.User;
@RepositoryRestResource(exported = false)
public interface UserRepository extends MongoRepository<User, String>  {

}
