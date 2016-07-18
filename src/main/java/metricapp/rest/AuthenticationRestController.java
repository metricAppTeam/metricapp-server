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

import metricapp.dto.credentials.CredentialsDTO;
import metricapp.dto.user.UserCrudDTO;
import metricapp.exception.UnauthorizedException;
import metricapp.service.spec.controller.AuthCRUDInterface;

@CrossOrigin
@RestController
public class AuthenticationRestController {
	
	@Autowired
	private AuthCRUDInterface authController;
	
	@RequestMapping(value = "/login-gmarciani", method = RequestMethod.POST)
	public ResponseEntity<UserCrudDTO> login(@RequestBody CredentialsDTO credentialsDTO) {
		
		UserCrudDTO responseDTO = new UserCrudDTO();
		
		try {
			
			responseDTO = authController.login(credentialsDTO);
			return new ResponseEntity<UserCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/logout-gmarciani", method = RequestMethod.POST)
	public ResponseEntity<UserCrudDTO> logout(@RequestHeader(name = "Authorization", defaultValue="NA") String auth) {
		
		UserCrudDTO responseDTO = new UserCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<UserCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			String authUsername = authController.authenticate(auth);
			
			responseDTO = authController.logout(authUsername);
			
			return new ResponseEntity<UserCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
			
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
