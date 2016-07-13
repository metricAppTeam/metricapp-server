package metricapp.service.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.entity.external.PointerBus;
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
		
		Question last = busApprovedElementRepository.getLastApprovedElement(id, Question.class, Entity.Question);
		
		return modelMapperFactory.getLooseModelMapper().map(last, QuestionDTO.class);
	}
	
	@Override
	public ArrayList<QuestionDTO> getQuestionsByPointerBusList(List<PointerBus> list){
		ArrayList<QuestionDTO> questionsDTO = new ArrayList<QuestionDTO>();
        Iterator<PointerBus> itM = list.iterator();
        while(itM.hasNext()){
        	try {
				questionsDTO.add(this.getQuestionByIdLastApprovedVersion(itM.next().getInstance()));
			} catch (Exception e) {
			}	
        }
        return questionsDTO;
	}
	
	@Override
	public QuestionCrudDTO getQuestionCrudDTOByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException, BusException, IOException {
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		questionCrudDTO.addQuestionToList(getQuestionByIdLastApprovedVersion(id));
		return questionCrudDTO;
		
	}

	@Override
	public QuestionCrudDTO getQuestionByQuestionerId(String id) throws BadInputException, NotFoundException {
		
		if(id == null){
			throw new BadInputException("id cannot be null");
		}
		
		ArrayList<Question> questionList = questionRepository.findQuestionByQuestionerId(id);
		
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
		ArrayList<Question> questionList = questionRepository.findQuestionByFocusLike(focus);
		
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
		ArrayList<Question> questionList = questionRepository.findQuestionBySubjectLike(subject);
		
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
		
		if (questionDTO.getQuestionerId() == null) {
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
		
		if(/*newQuestion.getQuestionerId() != null &&*/newQuestion.getState() == State.Created){
			questionRepository.save(newQuestion);
			
			Question updateQuestion = questionRepository.findQuestionById(newQuestion.getId());
			updateQuestion.setState(State.OnUpdate);
			
			try {
				stateTransition(newQuestion, updateQuestion);
			} catch (IllegalStateTransitionException | NotFoundException e) {
				e.printStackTrace();
			}
			
			questionCrudDTO.addQuestionToList(
					modelMapperFactory.getLooseModelMapper().map(questionRepository.save(updateQuestion), QuestionDTO.class));
		}
		
		else{
			questionCrudDTO.addQuestionToList(
					modelMapperFactory.getLooseModelMapper().map(questionRepository.save(newQuestion), QuestionDTO.class));
		}
		
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
	public QuestionCrudDTO getRecentQuestions(String questionerId){
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
	public long countQuestionByState(String state, String userId) throws BadInputException, NotFoundException{
		if (state == null) {
			throw new BadInputException("State cannot be null");
		}
		return questionRepository.countByStateAndQuestionerId(State.valueOf(state), userId);
	}
	
	@Override
	public QuestionCrudDTO getQuestionByStateAndQuestionerId(String state, String userId) throws NotFoundException, BadInputException {
		if (state == null) {
			throw new BadInputException("Questioner state cannot be null");
		}
		ArrayList<Question> questions = questionRepository.findByStateAndQuestionerId(State.valueOf(state),userId);
		if (questions.size() == 0) {
			throw new NotFoundException("State " + state + " has no Questions");
		}
		
		QuestionCrudDTO dto = new QuestionCrudDTO();
		dto.setRequest("Question of " + userId);
		Iterator<Question> questionIter = questions.iterator();
		while (questionIter.hasNext()) {
			dto.addQuestionToList(modelMapperFactory.getStandardModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return dto;
	}
	
	@Override
	public QuestionCrudDTO getQuestionByState(String state) throws NotFoundException, BadInputException {
		if (state == null) {
			throw new BadInputException("MeasurementGoal state cannot be null");
		}
		ArrayList<Question> questions = questionRepository.findByState(State.valueOf(state));
		if (questions.size() == 0) {
			throw new NotFoundException("State " + state + " has no Questions");
		}
		
		QuestionCrudDTO dto = new QuestionCrudDTO();
		Iterator<Question> questionIter = questions.iterator();
		while (questionIter.hasNext()) {
			dto.addQuestionToList(modelMapperFactory.getStandardModelMapper().map(questionIter.next(), QuestionDTO.class));
		}
		
		return dto;
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