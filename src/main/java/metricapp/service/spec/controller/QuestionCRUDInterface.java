package metricapp.service.spec.controller;

import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface QuestionCRUDInterface {
	public QuestionCrudDTO getQuestionById(String id) throws BadInputException, NotFoundException;
	public QuestionCrudDTO getQuestionByCreatorId(String id);
	public QuestionCrudDTO getQuestionByFocus(String focus);
	public QuestionCrudDTO getQuestionBySubject(String subject);
	public QuestionCrudDTO createQuestion(QuestionDTO dto) throws BadInputException;
	public QuestionCrudDTO updateQuestion(QuestionDTO dto) throws BadInputException, NotFoundException;
	public boolean deleteQuestionById(String id) throws BadInputException, NotFoundException;
	public void deleteAllQuestions();
	
}
