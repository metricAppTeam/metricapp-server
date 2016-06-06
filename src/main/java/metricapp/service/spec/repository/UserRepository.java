package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.stakeholders.Person;
@RepositoryRestResource(exported = false)
public interface UserRepository extends MongoRepository<Person, String>  {

}
