package metricapp.service.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.entity.question.Question;
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
	public QuestionCrudDTO createQuestion(QuestionDTO questionDTO) {
		Question newQuestion = modelMapperFactory.getLooseModelMapper().map(questionDTO, Question.class);
		
		questionRepository.save(newQuestion);
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		
		questionCrudDTO.addQuestionToList(questionDTO);
		
		return questionCrudDTO;
	}

	@Override
	public QuestionCrudDTO updateQuestion(QuestionDTO questionDTO) {
		Question updatedQuestion = modelMapperFactory.getLooseModelMapper().map(questionDTO, Question.class);
		
		if(questionRepository.exists(updatedQuestion.getId())){
			QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
			questionRepository.save(updatedQuestion);
			
			questionCrudDTO.addQuestionToList(questionDTO);
			return questionCrudDTO;
		}
		else{
			return null;
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