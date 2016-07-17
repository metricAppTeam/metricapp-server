package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.user.simple.UserSimpleCrudDTO;
import metricapp.dto.user.simple.UserSimpleDTO;
import metricapp.entity.user.UserSimple;
import metricapp.service.spec.controller.AuthCRUDInterface;
import metricapp.service.spec.controller.NotificationBoxCRUDInterface;
import metricapp.service.spec.repository.UserSimpleRepository;

@CrossOrigin
@RestController
@RequestMapping(("/simple_users"))
public class UserSimpleRestController {
	
	@Autowired
	private AuthCRUDInterface authController;
	
	@Autowired
	private UserSimpleRepository userRepo;
	
	@Autowired
	private NotificationBoxCRUDInterface nboxController;
	
	@RequestMapping(value = "/secure", method = RequestMethod.GET)
	public ResponseEntity<String> testAuthentication(
			@RequestHeader(name = "Authorization", defaultValue="NA") String auth) {
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.checkAuthorization(auth);
			
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserSimpleCrudDTO> createSimpleUser(
			@RequestBody UserSimpleDTO requestDTO) {
		
		UserSimpleCrudDTO responseDTO = new UserSimpleCrudDTO();
		
		try {
			if (requestDTO != null && requestDTO.getUsername() != null && requestDTO.getPassword() != null) {
				UserSimple user = new UserSimple();
				user.setUsername(requestDTO.getUsername());
				user.setPassword(requestDTO.getPassword());
				if (userRepo.insert(user) != null) {
					nboxController.createNotificationBoxForUser(user.getUsername());
					return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.BAD_REQUEST);
			}			
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
