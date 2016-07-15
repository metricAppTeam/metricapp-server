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
	
	@Query(value = "{'questionersId': { $in : [?0] }, 'state' : ?1 }", count = true)
	public Long countByQuestionerIdAndState(String questionerId, State state);
	
	@Query("{'questionersId': { $in : [?0] } }")
	public ArrayList<MeasurementGoal> findByQuestionerId(String questionerId);
	
	public ArrayList<MeasurementGoal> findByQualityFocusLike(String qualityFocus);
	
	public ArrayList<MeasurementGoal> findByViewPointLike(String viewPoint);
	
	@Query("{'tags': { $in : [?0] } }")
	public ArrayList<MeasurementGoal> findByTag(String tag);
	
	public ArrayList<MeasurementGoal> findByPurposeLike(String purpose);
	
	public ArrayList<MeasurementGoal> findByObjectLike(String object);
	
	public ArrayList<MeasurementGoal> findAll();
	
	
}
