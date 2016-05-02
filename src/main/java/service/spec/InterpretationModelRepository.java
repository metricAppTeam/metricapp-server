package service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import entity.measurementGoal.InterpretationModel;

public interface InterpretationModelRepository extends MongoRepository<InterpretationModel, String>{

}
