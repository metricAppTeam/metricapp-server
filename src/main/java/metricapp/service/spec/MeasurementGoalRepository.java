package metricapp.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricapp.entity.measurementGoal.MeasurementGoal;

public interface MeasurementGoalRepository extends MongoRepository<MeasurementGoal, String>{
	
	
	
}
