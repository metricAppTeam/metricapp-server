package metricapp.service.spec.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.State;
import metricapp.entity.question.Question;
@RepositoryRestResource(exported = false)
public interface QuestionRepository extends MongoRepository<Question, String> {

	public Question findQuestionById(String id);
	
	public ArrayList<Question> findQuestionByQuestionerId(String id);
	
	public ArrayList<Question> findQuestionByFocusLike(String focus);
	
	public ArrayList<Question> findQuestionBySubjectLike(String subject);
	
	@Query("{'tags': { $in : [?0] } }")
	public ArrayList<Question> findQuestionByTag(String tag);
	
	public Long countByStateAndQuestionerId(State state, String id);
	
	public ArrayList<Question> findByStateAndQuestionerId(State state, String id);

	public ArrayList<Question> findByState(State state);

}
