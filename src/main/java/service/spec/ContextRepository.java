package service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import entity.measurementGoal.Context;

public interface ContextRepository extends MongoRepository<Context, String>{

}
