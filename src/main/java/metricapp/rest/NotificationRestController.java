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
import metricapp.service.spec.controller.NotificationCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/notifications"))
public class NotificationRestController {
	
	@Autowired
	private AuthCRUDInterface authController;
	
	@Autowired
	private NotificationCRUDInterface notificationController;	
	
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public ResponseEntity<NotificationCrudDTO> getNewNotifications(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
		
		if (auth.equals("NA")) {
			return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
		}
		
		String authUsername = authController.authenticate(auth);
		
		responseDTO = notificationController.getNewNotificationsForUser(authUsername);
		
		return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);			
		
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<NotificationCrudDTO> getAllNotifications(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
			
			responseDTO = notificationController.getAllNotifications();
			
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);			
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<NotificationCrudDTO> getNotification(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestParam(value = "id", 			defaultValue = "NA") String id,
			@RequestParam(value = "authorId", 		defaultValue = "NA") String authorId,
			@RequestParam(value = "eventScope", 	defaultValue = "NA") String eventScope,
			@RequestParam(value = "eventScopeId", 	defaultValue = "NA") String eventScopeId,
			@RequestParam(value = "artifactScope", 	defaultValue = "NA") String artifactScope,
			@RequestParam(value = "artifactId", 	defaultValue = "NA") String artifactId,
			@RequestParam(value = "read", 			defaultValue = "NA") String read,
			@RequestParam(value = "from", 			defaultValue = "NA") String from,
			@RequestParam(value = "size", 			defaultValue = "NA") String size) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			String authUsername = authController.authenticate(auth);
			
			if (!id.equals("NA")) {
				responseDTO = notificationController.getNotificationForUserById(authUsername, id);				
			} else if (!authorId.equals("NA")) {
				responseDTO = notificationController.getNotificationsForUserByAuthorId(authUsername, authorId);
			} else if (!eventScope.equals("NA")) {
				responseDTO = notificationController.getNotificationsForUserByEventScope(authUsername, eventScope);
			} else if (!eventScopeId.equals("NA")) {
				responseDTO = notificationController.getNotificationsForUserByEventScopeId(authUsername, eventScopeId);
			} else if (!artifactScope.equals("NA")) {
				responseDTO = notificationController.getNotificationsForUserByArtifactScope(authUsername, artifactScope);
			} else if (!artifactId.equals("NA")) {
				responseDTO = notificationController.getNotificationsForUserByArtifactId(authUsername, artifactId);
			} else if (!read.equals("NA")) {
				responseDTO = notificationController.getNotificationsForUserByRead(authUsername, read);
			} else if (!from.equals("NA") && !size.equals("NA")) {
				responseDTO = notificationController.getNotificationsForUserPage(authUsername, from, size);
			} else {
				responseDTO = notificationController.getAllNotificationsForUser(authUsername);
			}
			
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);			
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
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
			
			String authUsername = authController.authenticate(auth);
			
			responseDTO = notificationController.patchNotificationForUser(authUsername, requestDTO);
			
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);	
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
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
			
			String authUsername = authController.authenticate(auth);
			
			if (id.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.BAD_REQUEST);
			}
			
			responseDTO = notificationController.deleteNotificationForUserById(authUsername, id);
			
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			e.printStackTrace();
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@RequestMapping(value = "/all", method = RequestMethod.DELETE)
	public ResponseEntity<NotificationCrudDTO> deleteAllNotifications(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth) {
		
		NotificationCrudDTO responseDTO = new NotificationCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<NotificationCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
			
			responseDTO =  notificationController.deleteAllNotifications();
			
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			e.printStackTrace();
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
