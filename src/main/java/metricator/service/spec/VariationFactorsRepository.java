package metricator.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricator.entity.measurementGoal.VariationFactors;

public interface VariationFactorsRepository extends MongoRepository<VariationFactors, String>{

}
