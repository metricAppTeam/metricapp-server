package metricator.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricator.entity.measurementGoal.Metric;

public interface MetricRepository extends MongoRepository<Metric, String>{

}
