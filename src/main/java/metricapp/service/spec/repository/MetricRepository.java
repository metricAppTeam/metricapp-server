package metricapp.service.spec.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.State;
import metricapp.entity.metric.Metric;

@RepositoryRestResource(exported = false)
public interface MetricRepository extends MongoRepository<Metric, String>{
	
	public Metric findMetricById(String id);
	
	public Metric findMetricByIdAndVersion(String id, String version);
	
	public ArrayList<Metric> findMetricByMetricatorId(String id);
	
	public Long countByStateAndMetricatorId(State state,String id);
	
	public ArrayList<Metric> findByStateAndMetricatorId(State state,String id);

	public ArrayList<Metric> findByState(State state);
	
}
