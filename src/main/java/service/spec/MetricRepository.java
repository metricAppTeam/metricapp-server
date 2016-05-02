package service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import entity.measurementGoal.Metric;

public interface MetricRepository extends MongoRepository<Metric, String>{

}
