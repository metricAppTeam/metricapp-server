package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.controller.QuestionCRUDController;

@CrossOrigin
@RestController
@RequestMapping(("/question"))
public class QuestionRestController {
	
	@Autowired
	QuestionCRUDController questionCRUDController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<QuestionCrudDTO> getQuestionDTO(
			@RequestParam(value="id", defaultValue="NA") String id,
			@RequestParam(value="creatorId", defaultValue="NA") String creatorId,
			@RequestParam(value="focus", defaultValue="NA") String focus,
			@RequestParam(value="subject", defaultValue="NA") String subject){
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		
		try{
			if(!id.equals("NA") && !id.equals("all")){
				questionCrudDTO = questionCRUDController.getQuestionById(id);
			}
			else if(id.equals("all")){
				questionCrudDTO = questionCRUDController.getAllQuestions();
			}
			else if(!creatorId.equals("NA")){
				questionCrudDTO = questionCRUDController.getQuestionByCreatorId(creatorId);
			}
			else if(!focus.equals("NA")){
				questionCrudDTO = questionCRUDController.getQuestionByFocus(focus);
			}
			
			else if(!subject.equals("NA")){
				questionCrudDTO = questionCRUDController.getQuestionBySubject(subject);
			}
			else{
				questionCrudDTO.setError("No parameters given");
				return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.OK);
			
		} catch (BadInputException e){
			questionCrudDTO.setError("No questions have been found");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e){
			questionCrudDTO.setError("No questions have been found");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e){
			questionCrudDTO.setError("Server Error");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<QuestionCrudDTO> deleteQuestionDTO(@RequestParam(value="id", defaultValue = "NA") String id){
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		
		try {
			if(id.equals("NA")){
				return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.BAD_REQUEST);
			}
			else{
				questionCRUDController.deleteQuestionById(id);
				return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.OK);
			}
		} catch (IllegalStateTransitionException e) {
			questionCrudDTO.setError("Illegal State Transition");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.FORBIDDEN);
		} catch (NotFoundException e){
			questionCrudDTO.setError("Question not found in repository");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			questionCrudDTO.setError("Server Error");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<QuestionCrudDTO> updateQuestionDTO(@RequestBody QuestionDTO questionDTO){
		
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		try {
			questionCrudDTO = questionCRUDController.updateQuestion(questionDTO);
			
			if(questionCrudDTO == null){
				return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.BAD_REQUEST);
			}
			else{
				return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.OK);
			}
			
		} catch (BadInputException e) {
			questionCrudDTO.setError("Bad Input");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			questionCrudDTO.setError("Not Found");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.NOT_FOUND);
		} catch (IllegalStateTransitionException e) {
			questionCrudDTO.setError("Illegal state transition");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.FORBIDDEN);
		} catch (DBException e) {
			questionCrudDTO.setError("DB_Exception");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.CONFLICT);
		} catch (Exception e){
			questionCrudDTO.setError("Internal Server Error");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<QuestionCrudDTO> createQuestionDTO(@RequestBody QuestionDTO questionDTO){
		QuestionCrudDTO questionCrudDTO = new QuestionCrudDTO();
		System.out.println("Received new question");
		System.out.println(questionDTO.toString());
		try{
			questionCrudDTO = questionCRUDController.createQuestion(questionDTO);
			System.out.println("Sending result OK");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.CREATED);			
		} catch (BadInputException e){
			questionCrudDTO.setError("Bad Request");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			questionCrudDTO.setError("Server Error");
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
