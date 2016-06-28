package metricapp.service.spec.controller;

import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;

public interface QuestionCRUDInterface {
	public QuestionCrudDTO getQuestionById(String id) throws BadInputException, NotFoundException;
	public QuestionCrudDTO getQuestionByCreatorId(String id) throws BadInputException, NotFoundException;
	public QuestionCrudDTO getQuestionByFocus(String focus) throws NotFoundException;
	public QuestionCrudDTO getQuestionBySubject(String subject) throws NotFoundException;
	public QuestionCrudDTO getQuestionByTag(String tag) throws NotFoundException;
	public QuestionCrudDTO createQuestion(QuestionDTO dto) throws BadInputException;
	public QuestionCrudDTO updateQuestion(QuestionDTO dto) throws BadInputException, NotFoundException, IllegalStateTransitionException, DBException;
	public QuestionCrudDTO getAllQuestions();
	public QuestionCrudDTO getRecentQuestions(String creatorId);
	public void deleteQuestionById(String id) throws BadInputException, NotFoundException, IllegalStateTransitionException;
	public void deleteAllQuestions();
	
}
