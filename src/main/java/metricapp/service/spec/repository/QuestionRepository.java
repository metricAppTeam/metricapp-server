package metricapp.service.spec.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.question.Question;
@RepositoryRestResource(exported = false)
public interface QuestionRepository extends MongoRepository<Question, String> {

	public Question findQuestionById(String id);
	
	public ArrayList<Question> findQuestionByCreatorId(String id);
	
	public ArrayList<Question> findQuestionByFocus(String focus);
	
	public ArrayList<Question> findQuestionBySubject(String subject);
}
