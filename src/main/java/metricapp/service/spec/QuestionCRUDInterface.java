package metricapp.service.spec;

import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;

public interface QuestionCRUDInterface {
	public QuestionCrudDTO getQuestionById(String id);
	public QuestionCrudDTO getQuestionByCreatorId(String id);
	public QuestionCrudDTO getQuestionByFocus(String focus);
	public QuestionCrudDTO getQuestionBySubject(String subject);
	//public QuestionCrudDTO getQuestionOfUser(String userId); //Sarebbe by questionerId ?
	public QuestionCrudDTO createQuestion(QuestionDTO dto);
	public QuestionCrudDTO updateQuestion(QuestionDTO dto);
	public boolean deleteQuestionById(String id); 
}
