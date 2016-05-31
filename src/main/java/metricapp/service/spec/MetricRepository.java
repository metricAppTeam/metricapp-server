package metricapp.service.spec;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricapp.entity.metric.Metric;

public interface MetricRepository extends MongoRepository<Metric, String>{
	
	public Metric findMetricById(String id);
	
	public Metric findMetricByIdAndVersion(String id, String version);
	
	public ArrayList<Metric> findMetricByMetricatorId(String id);
}
