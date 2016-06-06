package metricapp.service.spec;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

import metricapp.entity.question.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {

	public Question findQuestionById(String id);
	
	public ArrayList<Question> findQuestionByCreatorId(String id);
	
	public ArrayList<Question> findQuestionByFocus(String focus);
	
	public ArrayList<Question> findQuestionBySubject(String subject);
}
