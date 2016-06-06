package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.measurementGoal.MeasurementGoal;
@RepositoryRestResource(exported = false)
public interface MeasurementGoalRepository extends MongoRepository<MeasurementGoal, String>{
	
	
	
}
