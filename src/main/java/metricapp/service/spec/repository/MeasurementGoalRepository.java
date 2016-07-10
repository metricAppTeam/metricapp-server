package metricapp.service.spec.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.State;
import metricapp.entity.measurementGoal.MeasurementGoal;

@RepositoryRestResource(exported = false)
public interface MeasurementGoalRepository extends MongoRepository<MeasurementGoal, String>{
	
	public MeasurementGoal findById(String id);
	
	public MeasurementGoal findByIdAndVersion(String id, String version);
		
	public ArrayList<MeasurementGoal> findByMetricatorId(String id);
	
	public Long countByStateAndMetricatorId(State state, String id);
	
	public ArrayList<MeasurementGoal> findByStateAndMetricatorId(State state, String id);
	
	@Query("{'questionerId': { $in : [?0] } }")
	public ArrayList<MeasurementGoal> findByQuestionerId(String questionerId);	
}
