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
import metricapp.exception.NotFoundException;
import metricapp.exception.UnauthorizedException;
import metricapp.service.spec.controller.AuthCRUDInterface;
import metricapp.service.spec.controller.NotificationBoxCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/notifications"))
public class NotificationBoxRestController {
	
	@Autowired
	private AuthCRUDInterface authController;
	
	@Autowired
	private NotificationBoxCRUDInterface nboxController;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<NotificationCrudDTO> getNotification(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestParam(value = "id", 		defaultValue = "NA") String id,
			@RequestParam(value = "authorId", 	defaultValue = "NA") String authorId,
			@RequestParam(value = "scope", 		defaultValue = "NA") String scope,
			@RequestParam(value = "artifactId", defaultValue = "NA") String artifactId,
			@RequestParam(value = "read", 		defaultValue = "NA") String read,
			@RequestParam(value = "from", 		defaultValue = "NA") String from,
			@RequestParam(value = "to", 		defaultValue = "NA") String to) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
			
			if (!id.equals("NA")) {
				responseDTO = nboxController.getNotificationForUserById(auth, id);				
			} else if (!authorId.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserByAuthorId(auth, authorId);
			} else if (!scope.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserByScope(auth, scope);
			} else if (!artifactId.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserByArtifactId(auth, artifactId);
			} else if (!read.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserByRead(auth, read);
			} else if (!from.equals("NA") && !to.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserFromTo(auth, from, to);
			} else {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);			
			
		} catch (UnauthorizedException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
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
	
	@RequestMapping(method = RequestMethod.PATCH)
	public ResponseEntity<NotificationCrudDTO> patchNotification(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestBody NotificationDTO requestDTO) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();		
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
			
			responseDTO = nboxController.patchNotificationBoxForUser(auth, requestDTO);
			
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);	
			
		} catch (UnauthorizedException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
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
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<NotificationCrudDTO> deleteNotification(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestParam(value = "id", 	defaultValue = "NA") String id) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
			
			if (!id.equals("NA")) {
				responseDTO = nboxController.deleteNotificationForUserById(auth, id);
			} else {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.BAD_REQUEST);
			}	
			
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (UnauthorizedException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			e.printStackTrace();
			responseDTO.setError(e.getMessage());
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

}
