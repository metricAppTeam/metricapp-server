package service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import entity.measurementGoal.MeasurementGoal;

public interface MeasurementGoalRepository extends MongoRepository<MeasurementGoal, String>{

}
