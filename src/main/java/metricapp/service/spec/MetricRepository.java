package metricapp.service.spec;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricapp.entity.metric.Metric;

public interface MetricRepository extends MongoRepository<Metric, String>{

}
