package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.topic.TopicCrudDTO;
import metricapp.dto.topic.TopicDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.NotFoundException;
import metricapp.exception.UnauthorizedException;
import metricapp.service.spec.controller.AuthCRUDInterface;
import metricapp.service.spec.controller.TopicCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/topics"))
public class TopicRestController {
	
	@Autowired
	private AuthCRUDInterface authController;
	
	@Autowired
	private TopicCRUDInterface topicController;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<TopicCrudDTO> getTopic(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestParam(value = "id", 	defaultValue = "NA") String id,
			@RequestParam(value = "name", 	defaultValue = "NA") String name) {
		
		TopicCrudDTO responseDTO = new TopicCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
			
			if (!id.equals("NA")) {
				responseDTO = topicController.getTopicById(id);
			} else if (!name.equals("NA")) {
				responseDTO = topicController.getTopicByName(name);
			} else {
				responseDTO = topicController.getAllTopics();
			}			

			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TopicCrudDTO> createTopic(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestBody TopicDTO requestDTO) {
		
		TopicCrudDTO responseDTO = new TopicCrudDTO();
		
		try {			
			
			if (auth.equals("NA")) {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.UNAUTHORIZED);
			}			
			
			authController.authenticate(auth);
			
			responseDTO = topicController.createTopic(requestDTO);
			
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.CREATED);
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PATCH)
	public ResponseEntity<TopicCrudDTO> patchTopic(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestParam(value = "action",	defaultValue = "NA") String action,
			@RequestBody TopicDTO requestDTO) {
		
		TopicCrudDTO responseDTO = new TopicCrudDTO();			
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
			
			if (!action.equals("NA")) {
				if (action.equals("subscribe")) {
					responseDTO = topicController.patchTopicAddSubscribers(requestDTO);
				} else if (action.equals("unsubscribe")) {
					responseDTO = topicController.patchTopicRemoveSubscribers(requestDTO);
				} else {
					return new ResponseEntity<TopicCrudDTO>(HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (DBException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.CONFLICT);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<TopicCrudDTO> deleteTopic(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestParam(value = "id", 	defaultValue = "NA") String id,
			@RequestParam(value = "name", 	defaultValue = "NA") String name) {
		
		TopicCrudDTO responseDTO = new TopicCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
			
			if (!id.equals("NA")) {
				topicController.deleteTopicById(id);
			} else if (!name.equals("NA")) {
				topicController.deleteTopicByName(name);
			} else {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<TopicCrudDTO>(HttpStatus.OK);			
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			e.printStackTrace();
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}	

}
