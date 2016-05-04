package metricator.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricator.entity.measurementGoal.MeasurementGoal;

public interface MeasurementGoalRepository extends MongoRepository<MeasurementGoal, String>{
	
	
	
}
