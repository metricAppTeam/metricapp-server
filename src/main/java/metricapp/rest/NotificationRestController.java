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

import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.NotificationCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/notifications"))
public class NotificationRestController {
	
	@Autowired
	private NotificationCRUDInterface notificationCRUDController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<NotificationCrudDTO> getNotification(
			@RequestHeader(value = "username",	defaultValue = "NA") String username,
			@RequestParam(value = "id", 		defaultValue = "NA") String id,
			@RequestParam(value = "authorId", 	defaultValue = "NA") String authorId,
			@RequestParam(value = "scope", 		defaultValue = "NA") String scope,
			@RequestParam(value = "artifactId", defaultValue = "NA") String artifactId) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
			if (username.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			if (!id.equals("NA")) {
				responseDTO = notificationCRUDController.getNotificationById(username, id);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!authorId.equals("NA")) {
				responseDTO = notificationCRUDController.getNotificationByAuthorId(username, authorId);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!scope.equals("NA")) {
				responseDTO = notificationCRUDController.getNotificationByScope(username, scope);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!artifactId.equals("NA")) {
				responseDTO = notificationCRUDController.getNotificationByArtifactId(username, artifactId);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			return new ResponseEntity<NotificationCrudDTO>(HttpStatus.BAD_REQUEST);
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<NotificationCrudDTO> createNotification(
			@RequestHeader(value = "username",	defaultValue = "NA") String username,
			@RequestBody NotificationDTO requestDTO) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
			if (username.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<NotificationCrudDTO>(notificationCRUDController.createNotification(username, requestDTO), HttpStatus.CREATED);
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e){
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<NotificationCrudDTO> putNotification(
			@RequestHeader(value = "username",	defaultValue = "NA") String username,
			@RequestBody NotificationDTO requestDTO) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();	
				
		try {
			if (username.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<NotificationCrudDTO>(notificationCRUDController.updateNotification(username, requestDTO), HttpStatus.OK);			
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		}catch (Exception e){
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PATCH)
	public ResponseEntity<NotificationCrudDTO> patchNotification(
			@RequestHeader(value = "username",	defaultValue = "NA") String username,
			@RequestParam(value = "id", 		defaultValue = "NA") String id,
			@RequestParam(value = "read", 		defaultValue = "NA") String read) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();	
		
		
		try {
			if (username.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			if (!id.equals("NA")) {
				if (read.equals("true")) {
					return new ResponseEntity<NotificationCrudDTO>(notificationCRUDController.patchNotificationReadById(username, id, true), HttpStatus.OK);
				} else {
					return new ResponseEntity<NotificationCrudDTO>(notificationCRUDController.patchNotificationReadById(username, id, false), HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.BAD_REQUEST);
			}			
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (DBException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.CONFLICT);
		} catch (Exception e){
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<NotificationCrudDTO> deleteNotification(
			@RequestHeader(value = "username",	defaultValue = "NA") String username,
			@RequestParam(value = "id", defaultValue = "NA") String id) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
			if (username.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			if (!id.equals("NA")) {
				notificationCRUDController.deleteNotificationById(username, id);
			} else {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.BAD_REQUEST);
			}			
		} catch (BadInputException e) {
			e.printStackTrace();
			responseDTO.setError(e.getMessage());
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e){
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<NotificationCrudDTO>(HttpStatus.OK);
	}	

}
