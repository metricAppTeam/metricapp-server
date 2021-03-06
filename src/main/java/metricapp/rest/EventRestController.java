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

import metricapp.dto.event.EventCrudDTO;
import metricapp.dto.event.EventDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.exception.UnauthorizedException;
import metricapp.service.spec.controller.AuthCRUDInterface;
import metricapp.service.spec.controller.EventCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/events"))
public class EventRestController {
	
	@Autowired
	private AuthCRUDInterface authController;
	
	@Autowired
	private EventCRUDInterface eventController;	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<EventCrudDTO> getAllEvents() {
		
		EventCrudDTO responseDTO = new EventCrudDTO();
		
		try {
			
			responseDTO = eventController.getAllEvents();
			
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (NotFoundException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EventCrudDTO> getEvent(
			@RequestParam(value = "id", 		defaultValue = "NA") String id,
			@RequestParam(value = "authorId", 	defaultValue = "NA") String authorId,
			@RequestParam(value = "artifactScope", 	defaultValue = "NA") String artifactScope,
			@RequestParam(value = "artifactId", 	defaultValue = "NA") String artifactId) {
		
		EventCrudDTO responseDTO = new EventCrudDTO();
		
		try {
						
			if (!id.equals("NA")) {
				responseDTO = eventController.getEventById(id);
			} else if (!authorId.equals("NA")) {
				responseDTO = eventController.getEventsByAuthorId(authorId);
			} else if (!artifactScope.equals("NA")) {
				responseDTO = eventController.getEventsByArtifactScope(artifactScope);
			} else if (!artifactId.equals("NA")) {
				responseDTO = eventController.getEventsByArtifactId(artifactId);
			} else {
				responseDTO = eventController.getAllEvents();
			}
			
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EventCrudDTO> createEvent(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestParam(value = "topicName", defaultValue = "NA") String topicName,
			@RequestBody EventDTO requestDTO) {
		
		EventCrudDTO responseDTO = new EventCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<EventCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			String username = authController.authenticate(auth);
			
			if (topicName.equals("NA")) {
				responseDTO.setMessage("topicName cannot be null");
				return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
			}
			
			responseDTO = eventController.createEvent(username, topicName, requestDTO);
			
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.CREATED);
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@RequestMapping(value = "/all", method = RequestMethod.DELETE)
	public ResponseEntity<EventCrudDTO> deleteAllEvent() {
		
		EventCrudDTO responseDTO = new EventCrudDTO();
		
		try {
			
			responseDTO = eventController.deleteAllEvents();
			
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

}
