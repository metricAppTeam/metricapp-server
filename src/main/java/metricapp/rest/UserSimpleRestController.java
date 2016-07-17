package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.notification.box.NotificationBoxCrudDTO;
import metricapp.entity.user.UserSimple;
import metricapp.service.spec.controller.NotificationBoxCRUDInterface;
import metricapp.service.spec.repository.UserSimpleRepository;

@CrossOrigin
@RestController
@RequestMapping(("/simple_users"))
public class UserSimpleRestController {
	
	@Autowired
	private UserSimpleRepository userSimpleRepository;
	
	@Autowired
	private NotificationBoxCRUDInterface notificationboxCRUDController;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<NotificationBoxCrudDTO> createSimpleUser(
			@RequestBody String username) {
		
		NotificationBoxCrudDTO responseDTO = new NotificationBoxCrudDTO();
		
		try {
			if (username != null) {
				UserSimple user = new UserSimple();
				user.setUsername(username);
				UserSimple newUser = userSimpleRepository.insert(user);				
				if (newUser != null) {
					return new ResponseEntity<NotificationBoxCrudDTO>(
							notificationboxCRUDController.createNotificationBoxForUser(username), 
							HttpStatus.CREATED);
				} else {
					return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
			}			
		} catch (Exception e){
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
