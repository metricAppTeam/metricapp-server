package metricator.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricator.entity.measurementGoal.Context;

public interface ContextRepository extends MongoRepository<Context, String>{

}
