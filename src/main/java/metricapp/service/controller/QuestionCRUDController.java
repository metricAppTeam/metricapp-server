package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.entity.Entity;
import metricapp.entity.metric.Metric;
import metricapp.entity.question.Question;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.QuestionCRUDInterface;
import metricapp.service.spec.repository.QuestionRepository;

@Service
public class QuestionCRUDController implements QuestionCRUDInterface {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory; 
	
	@Override
	public QuestionCrudDTO getQuestionById(String id) {
		Question question = questionRepository.findQuestionById(id);
		try{
			QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
			
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(question, QuestionDTO.class));
			return questionCrudDTO;
		}
		catch(IllegalArgumentException e){
			System.err.println("No Questions found");
		}
		
		return null;
	}

	@Override
	public QuestionCrudDTO getQuestionByCreatorId(String id) {
		ArrayList<Question> questionList = questionRepository.findQuestionByCreatorId(id);
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		Iterator<Question> questionIter = questionList.iterator();
		
		if(!questionIter.hasNext()){
			return null;
		}
		
		while(questionIter.hasNext()){
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return questionCrudDTO;
	}
	
	@Override
	public QuestionCrudDTO getQuestionByFocus(String focus) {
		ArrayList<Question> questionList = questionRepository.findQuestionByFocus(focus);
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		Iterator<Question> questionIter = questionList.iterator();
		
		if(!questionIter.hasNext()){
			return null;
		}
		
		while(questionIter.hasNext()){
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return questionCrudDTO;
	}
	
	@Override
	public QuestionCrudDTO getQuestionBySubject(String subject) {
		ArrayList<Question> questionList = questionRepository.findQuestionBySubject(subject);
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		Iterator<Question> questionIter = questionList.iterator();
		
		if(!questionIter.hasNext()){
			return null;
		}
		
		while(questionIter.hasNext()){
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return questionCrudDTO;
	}
	
	@Override
	public QuestionCrudDTO createQuestion(QuestionDTO questionDTO) throws BadInputException{
		
		if (questionDTO.getMetadata().getCreatorId() == null) {
			System.out.println(questionDTO.getMetadata().getCreatorId());
			throw new BadInputException("Bad Input");
		}
		if (questionDTO.getMetadata().getId() != null) {
			throw new BadInputException("New Metrics cannot have ID");
		}
		questionDTO.getMetadata().setCreationDate(LocalDate.now());
		questionDTO.getMetadata().setLastVersionDate(LocalDate.now());
		Question newQuestion = modelMapperFactory.getLooseModelMapper().map(questionDTO, Question.class);

		newQuestion.setCreationDate(LocalDate.now());
		newQuestion.setLastVersionDate(LocalDate.now());
		newQuestion.setEntityType(Entity.Question);
		newQuestion.setVersion("0");
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		
		questionCrudDTO.addQuestionToList(
				modelMapperFactory.getLooseModelMapper().map(questionRepository.save(newQuestion), QuestionDTO.class));
		
		return questionCrudDTO;
	}

	@Override
	public QuestionCrudDTO updateQuestion(QuestionDTO questionDTO) throws BadInputException, NotFoundException {
		
		if(questionDTO.getMetadata().getId() == null){
			throw new BadInputException("Id cannot be null");
		}
		
		questionDTO.getMetadata().setLastVersionDate(LocalDate.now());
	
		Question updatedQuestion = modelMapperFactory.getLooseModelMapper().map(questionDTO, Question.class);
		
		if(questionRepository.exists(updatedQuestion.getId())){
			QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
			questionRepository.save(updatedQuestion);
			
			questionCrudDTO.addQuestionToList(questionDTO);
			return questionCrudDTO;
		}
		else{
			throw new NotFoundException("Question not found");
		}
		
	}

	@Override
	public boolean deleteQuestionById(String id) {
		if(questionRepository.exists(id)){
			questionRepository.delete(id);
			return true;
		}
		else{
			return false;
		}

	}

	@Override
	public void deleteAllQuestions() {
		questionRepository.deleteAll();
		
	}


}