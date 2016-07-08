package metricapp.service.spec.controller;

import java.io.IOException;

import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
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
	public QuestionDTO getQuestionByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException, BusException, IOException;
	public QuestionCrudDTO getQuestionCrudDTOByIdLastApprovedVersion(String id)
			throws BadInputException, NotFoundException, BusException, IOException;
	long countQuestionByState(String state, String userId) throws BadInputException, NotFoundException;
	QuestionCrudDTO getQuestionByState(String state, String userId) throws NotFoundException, BadInputException;
	
}
