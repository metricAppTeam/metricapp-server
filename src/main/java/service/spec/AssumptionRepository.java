package service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import entity.measurementGoal.Assumption;

public interface AssumptionRepository extends MongoRepository<Assumption, String>{

}
