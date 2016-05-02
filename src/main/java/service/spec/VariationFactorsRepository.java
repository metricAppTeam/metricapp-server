package service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import entity.measurementGoal.VariationFactors;

public interface VariationFactorsRepository extends MongoRepository<VariationFactors, String>{

}
