package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.measurementGoal.Assumption;
@RepositoryRestResource(exported = false)
public interface AssumptionRepository extends MongoRepository<Assumption, String>{

}
