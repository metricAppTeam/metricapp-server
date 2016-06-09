package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.ResponseDTO;
import metricapp.dto.question.QuestionCrudDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.service.controller.QuestionCRUDController;

@RestController
@RequestMapping(("/question"))
public class QuestionRestController {
	
	@Autowired
	QuestionCRUDController questionCRUDController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<QuestionCrudDTO> getQuestionDTO(
			@RequestParam(value="id", defaultValue="NA") String id,
			@RequestParam(value="focus", defaultValue="NA") String focus,
			@RequestParam(value="subject", defaultValue="NA") String subject){
		
		QuestionCrudDTO questionCrudDTO = null;
		
		if(!id.equals("NA")){
			questionCrudDTO = questionCRUDController.getQuestionById(id);
		}
		
		else if(!focus.equals("NA")){
			questionCrudDTO = questionCRUDController.getQuestionByFocus(focus);
		}
		
		else if(!subject.equals("NA")){
			questionCrudDTO = questionCRUDController.getQuestionBySubject(subject);
		}
		
		if(questionCrudDTO == null){
			return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<QuestionCrudDTO>(questionCrudDTO, HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> deleteQuestionDTO(@RequestParam(value="id", defaultValue = "NA") String id){
		if(id.equals("NA") || !questionCRUDController.deleteQuestionById(id)){
			return new ResponseEntity<ResponseDTO>(HttpStatus.BAD_REQUEST);
		}
		else{
			return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> updateQuestionDTO(@RequestBody QuestionDTO questionDTO){
		
		QuestionCrudDTO questionCrudDTO = null;
		
		questionCrudDTO = questionCRUDController.updateQuestion(questionDTO);
		
		if(questionCrudDTO == null){
			return new ResponseEntity<ResponseDTO>(HttpStatus.BAD_REQUEST);
		}
		else{
			return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> createQuestionDTO(@RequestBody QuestionDTO questionDTO){
		
		questionCRUDController.createQuestion(questionDTO);
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
		
	}
}
