package metricapp.service.spec.controller;

import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;

public interface QuestionCRUDInterface {
	public QuestionCrudDTO getQuestionById(String id);
	public QuestionCrudDTO getQuestionByCreatorId(String id);
	public QuestionCrudDTO getQuestionByFocus(String focus);
	public QuestionCrudDTO getQuestionBySubject(String subject);
	public QuestionCrudDTO createQuestion(QuestionDTO dto);
	public QuestionCrudDTO updateQuestion(QuestionDTO dto);
	public boolean deleteQuestionById(String id); 
	public void deleteAllQuestions();
	
}
