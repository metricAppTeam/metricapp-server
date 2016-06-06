package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricapp.entity.stakeholders.Person;

public interface UserRepository extends MongoRepository<Person, String>  {

}
