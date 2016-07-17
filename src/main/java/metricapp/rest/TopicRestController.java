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
import metricapp.service.spec.controller.TopicCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/topics"))
public class TopicRestController {
	
	@Autowired
	private TopicCRUDInterface topicController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<TopicCrudDTO> getTopic(
			@RequestHeader(value = "auth",	defaultValue = "NA") String auth,
			@RequestParam(value = "id", 	defaultValue = "NA") String id,
			@RequestParam(value = "name", 	defaultValue = "NA") String name) {
		
		TopicCrudDTO responseDTO = new TopicCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.UNAUTHORIZED);
			}	
			
			if (!id.equals("NA")) {
				responseDTO = topicController.getTopicById(id);
				return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!name.equals("NA")) {
				responseDTO = topicController.getTopicByName(name);
				return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.OK);
			}	
			
			return new ResponseEntity<TopicCrudDTO>(HttpStatus.BAD_REQUEST);
			
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TopicCrudDTO> createTopic(
			@RequestHeader(value = "auth",	defaultValue = "NA") String auth,
			@RequestBody TopicDTO requestDTO) {
		
		TopicCrudDTO responseDTO = new TopicCrudDTO();
		
		try {			
			if (auth.equals("NA")) {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.UNAUTHORIZED);
			}			
			return new ResponseEntity<TopicCrudDTO>(topicController.createTopic(requestDTO), HttpStatus.CREATED);
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PATCH)
	public ResponseEntity<TopicCrudDTO> patchTopic(
			@RequestHeader(value = "auth",	defaultValue = "NA") String auth,
			@RequestParam(value = "action",	defaultValue = "NA") String action,
			@RequestBody TopicDTO requestDTO) {
		
		TopicCrudDTO responseDTO = new TopicCrudDTO();			
		
		try {
			if (auth.equals("NA")) {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			if (requestDTO.getId() != null && !action.equals("NA")) {
				if (action.equals("subscribe")) {
					return new ResponseEntity<TopicCrudDTO>(topicController.patchTopicByIdAddSubscribers(requestDTO), HttpStatus.OK);
				} else if (action.equals("unsubscribe")) {
					return new ResponseEntity<TopicCrudDTO>(topicController.patchTopicByIdRemoveSubscribers(requestDTO), HttpStatus.OK);
				} else {
					return new ResponseEntity<TopicCrudDTO>(HttpStatus.BAD_REQUEST);
				}
			} else if (requestDTO.getName() != null && !action.equals("NA")) {
				if (action.equals("subscribe")) {
					return new ResponseEntity<TopicCrudDTO>(topicController.patchTopicByNameAddSubscribers(requestDTO), HttpStatus.OK);
				} else if (action.equals("unsubscribe")) {
					return new ResponseEntity<TopicCrudDTO>(topicController.patchTopicByNameRemoveSubscribers(requestDTO), HttpStatus.OK);
				} else {
					return new ResponseEntity<TopicCrudDTO>(HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.BAD_REQUEST);
			}			
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (DBException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.CONFLICT);
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<TopicCrudDTO> deleteTopic(
			@RequestHeader(value = "auth",	defaultValue = "NA") String auth,
			@RequestParam(value = "id", 	defaultValue = "NA") String id,
			@RequestParam(value = "name", 	defaultValue = "NA") String name) {
		
		TopicCrudDTO responseDTO = new TopicCrudDTO();
		
		try {
			if (auth.equals("NA")) {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			if (!id.equals("NA")) {
				topicController.deleteTopicById(id);
			}
			if (!name.equals("NA")) {
				topicController.deleteTopicByName(name);
			} else {
				return new ResponseEntity<TopicCrudDTO>(HttpStatus.BAD_REQUEST);
			}			
		} catch (BadInputException e) {
			e.printStackTrace();
			responseDTO.setError(e.getMessage());
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<TopicCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<TopicCrudDTO>(HttpStatus.OK);
	}	

}
