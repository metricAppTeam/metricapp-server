package metricator.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricator.entity.measurementGoal.InterpretationModel;

public interface InterpretationModelRepository extends MongoRepository<InterpretationModel, String>{

}
