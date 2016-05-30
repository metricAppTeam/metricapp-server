package metricapp.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricapp.entity.measurementGoal.Assumption;

public interface AssumptionRepository extends MongoRepository<Assumption, String>{

}
