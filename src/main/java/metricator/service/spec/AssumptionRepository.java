package metricator.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricator.entity.measurementGoal.Assumption;

public interface AssumptionRepository extends MongoRepository<Assumption, String>{

}