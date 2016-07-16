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
import metricapp.service.spec.controller.EventCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/events"))
public class EventRestController {
	
	@Autowired
	private EventCRUDInterface eventCRUDController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EventCrudDTO> getEvent(
			@RequestHeader(value = "username",	defaultValue = "NA") String username,
			@RequestParam(value = "id", 		defaultValue = "NA") String id,
			@RequestParam(value = "authorId", 	defaultValue = "NA") String authorId,
			@RequestParam(value = "scope", 		defaultValue = "NA") String scope,
			@RequestParam(value = "artifactId", defaultValue = "NA") String artifactId) {
		
		EventCrudDTO responseDTO = new EventCrudDTO();
		
		try {
			if (username.equals("NA")) {
				return new ResponseEntity<EventCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			if (!id.equals("NA")) {
				responseDTO = eventCRUDController.getEventById(id);
				return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!authorId.equals("NA")) {
				responseDTO = eventCRUDController.getEventByAuthorId(authorId);
				return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!scope.equals("NA")) {
				responseDTO = eventCRUDController.getEventByScope(scope);
				return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!artifactId.equals("NA")) {
				responseDTO = eventCRUDController.getEventByArtifactId(artifactId);
				return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			return new ResponseEntity<EventCrudDTO>(HttpStatus.BAD_REQUEST);
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EventCrudDTO> createEvent(
			@RequestHeader(value = "username",	defaultValue = "NA") String username,
			@RequestBody EventDTO requestDTO) {
		
		EventCrudDTO responseDTO = new EventCrudDTO();
		
		try {
			if (username.equals("NA")) {
				return new ResponseEntity<EventCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<EventCrudDTO>(eventCRUDController.createEvent(requestDTO), HttpStatus.CREATED);
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e){
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<EventCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

}
