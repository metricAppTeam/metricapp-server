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
import metricapp.service.spec.controller.NotificationBoxCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/notifications"))
public class NotificationBoxRestController {
	
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
			
			if (!id.equals("NA")) {
				responseDTO = nboxController.getNotificationForUserById(auth, id);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!authorId.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserByAuthorId(auth, authorId);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!scope.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserByScope(auth, scope);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!artifactId.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserByArtifactId(auth, artifactId);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!read.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserByRead(auth, read);
				return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			}
			
			if (!from.equals("NA") && !to.equals("NA")) {
				responseDTO = nboxController.getNotificationsForUserFromTo(auth, from, to);
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
	
	@RequestMapping(method = RequestMethod.PATCH)
	public ResponseEntity<NotificationCrudDTO> patchNotification(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestBody NotificationDTO requestDTO) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();		
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			return new ResponseEntity<NotificationCrudDTO>(nboxController.patchNotificationBoxForUser(auth, requestDTO), HttpStatus.OK);	
			
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e){
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
			
			if (!id.equals("NA")) {
				nboxController.deleteNotificationForUserById(auth, id);
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
