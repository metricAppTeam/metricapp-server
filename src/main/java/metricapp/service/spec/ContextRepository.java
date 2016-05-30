package metricapp.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricapp.entity.measurementGoal.Context;

public interface ContextRepository extends MongoRepository<Context, String>{

}
