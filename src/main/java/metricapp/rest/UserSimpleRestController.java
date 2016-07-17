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
	private UserSimpleRepository userRepo;
	
	@Autowired
	private NotificationBoxCRUDInterface nboxController;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<NotificationBoxCrudDTO> createSimpleUser(
			@RequestBody String username) {
		
		NotificationBoxCrudDTO responseDTO = new NotificationBoxCrudDTO();
		
		try {
			if (username != null) {
				UserSimple user = new UserSimple();
				user.setUsername(username);
				if (userRepo.insert(user) != null) {
					responseDTO = nboxController.createNotificationBoxForUser(username);
					return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
			}			
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
