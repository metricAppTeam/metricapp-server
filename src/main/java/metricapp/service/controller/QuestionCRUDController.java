package metricapp.service.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.entity.question.Question;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.QuestionCRUDInterface;
import metricapp.service.spec.repository.BusApprovedElementInterface;
import metricapp.service.spec.repository.QuestionRepository;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

@Service
public class QuestionCRUDController implements QuestionCRUDInterface {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory; 
	
	@Autowired
	private BusApprovedElementInterface busApprovedElementRepository;

	private static final int LIMIT = 10;
	
	@Override
	public QuestionCrudDTO getQuestionById(String id) throws NotFoundException, BadInputException {
		
		if(id == null){
			throw new BadInputException("Id cannot be null");
		}
		
		Question question = questionRepository.findQuestionById(id);
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		try{
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(question, QuestionDTO.class));
			return questionCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("No Questions found");
		}
	}
	
	@Override
	public QuestionDTO getQuestionByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException, BusException, IOException {
		if (id == null) {
			throw new BadInputException("Question id cannot be null");
		}
		
		Question last = busApprovedElementRepository.getLastApprovedElement(id, Question.class);
		
		return modelMapperFactory.getLooseModelMapper().map(last, QuestionDTO.class);
	}
	
	@Override
	public QuestionCrudDTO getQuestionCrudDTOByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException, BusException, IOException {
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		questionCrudDTO.addQuestionToList(getQuestionByIdLastApprovedVersion(id));
		return questionCrudDTO;
		
	}

	@Override
	public QuestionCrudDTO getQuestionByCreatorId(String id) throws BadInputException, NotFoundException {
		
		if(id == null){
			throw new BadInputException("id cannot be null");
		}
		
		ArrayList<Question> questionList = questionRepository.findQuestionByCreatorId(id);
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		Iterator<Question> questionIter = questionList.iterator();
		
		if(!questionIter.hasNext()){
			throw new NotFoundException("No Questions found for questioner " + id);
		}
		
		while(questionIter.hasNext()){
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return questionCrudDTO;
	}
	
	public QuestionCrudDTO getQuestionByTag(String tag) throws NotFoundException {
		ArrayList<Question> questionList = questionRepository.findQuestionByTag(tag);
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		Iterator<Question> questionIter = questionList.iterator();
		
		if(!questionIter.hasNext()){
			throw new NotFoundException("No question for this focus");
		}
		
		while(questionIter.hasNext()){
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return questionCrudDTO;
	}
	
	@Override
	public QuestionCrudDTO getQuestionByFocus(String focus) throws NotFoundException {
		ArrayList<Question> questionList = questionRepository.findQuestionByFocus(focus);
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		Iterator<Question> questionIter = questionList.iterator();
		
		if(!questionIter.hasNext()){
			throw new NotFoundException("No question for this focus");
		}
		
		while(questionIter.hasNext()){
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return questionCrudDTO;
	}
	
	@Override
	public QuestionCrudDTO getQuestionBySubject(String subject) throws NotFoundException {
		ArrayList<Question> questionList = questionRepository.findQuestionBySubject(subject);
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		Iterator<Question> questionIter = questionList.iterator();
		
		if(!questionIter.hasNext()){
			throw new NotFoundException("No question for this subject");
		}
		
		while(questionIter.hasNext()){
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return questionCrudDTO;
	}
	
	@Override
	public QuestionCrudDTO createQuestion(QuestionDTO questionDTO) throws BadInputException{
		
		if (questionDTO.getMetadata().getCreatorId() == null) {
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
	public QuestionCrudDTO updateQuestion(QuestionDTO questionDTO) 
			throws BadInputException, NotFoundException, IllegalStateTransitionException, DBException{
		
		if(questionDTO.getMetadata().getId() == null){
			throw new BadInputException("Id cannot be null");
		}
		
		questionDTO.getMetadata().setLastVersionDate(LocalDate.now());
		
		Question updatedQuestion = modelMapperFactory.getLooseModelMapper().map(questionDTO, Question.class);
		
		Question oldQuestion = questionRepository.findQuestionById(questionDTO.getMetadata().getId());
		
		stateTransition(oldQuestion, updatedQuestion);
		System.out.println(updatedQuestion.getVersion());
		
		if(questionRepository.exists(updatedQuestion.getId())){
			QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
			try{
				questionRepository.save(updatedQuestion);
			}
			catch(Exception e){
				throw new DBException("Error in saving question in repository");
			}
						
			questionDTO = modelMapperFactory.getLooseModelMapper()
					.map(questionRepository.findQuestionById(questionDTO.getMetadata().getId()), QuestionDTO.class);

			System.out.println(questionDTO.getMetadata().getVersion());
			questionCrudDTO.addQuestionToList(questionDTO);
			return questionCrudDTO;
		}
		else{
			throw new NotFoundException("Question not found");
		}
		
	}
	
	@Override
	public QuestionCrudDTO getAllQuestions(){
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		
		Iterator<Question> questionIter = questionRepository.findAll().iterator();
		
		while(questionIter.hasNext()){
			
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
			
		}	
		
		return questionCrudDTO;
	}
	
	@Override
	public QuestionCrudDTO getRecentQuestions(String creatorId){
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		
		Sort sort = new Sort(Sort.Direction.DESC, "lastVersionDate");
		
		Iterator<Question> questionIter = questionRepository.findAll(sort).iterator();
		
		int counter = 0;
		while(questionIter.hasNext() && counter < LIMIT){
			questionCrudDTO.addQuestionToList(modelMapperFactory.getLooseModelMapper().map(questionIter.next(), QuestionDTO.class));
			counter++;
		}
		
		return questionCrudDTO;
	}

	@Override
	public void deleteQuestionById(String id) throws IllegalStateTransitionException, NotFoundException {
				
		if(questionRepository.exists(id)){
			if (!questionRepository.findQuestionById(id).getState().equals(State.Suspended)) {
				throw new IllegalStateTransitionException("A question must be Suspended before deletion");
			}

			questionRepository.delete(id);
		}
		else{
			throw new NotFoundException("The question does not exists");
		}

	}

	@Override
	public void deleteAllQuestions() {
		questionRepository.deleteAll();
	}
	
	private void stateTransition(Question oldQuestion, Question newQuestion)
			throws IllegalStateTransitionException, NotFoundException {
		
		if (!oldQuestion.getState().equals(newQuestion.getState())) {
			try {
				AbstractStateTransitionFactory.getFactory(Entity.Question).transition(oldQuestion, newQuestion).execute();
			} catch (Exception e) {
				throw new IllegalStateTransitionException(e);
			}
	 	}
		
	}

	

}