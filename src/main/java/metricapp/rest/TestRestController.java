package metricapp.rest;

import java.nio.charset.Charset;
import java.util.Base64;

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

@CrossOrigin
@RestController
@RequestMapping(("/test"))
public class TestRestController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UserSimpleCrudDTO> testGET(
			@RequestHeader(name = "Authorization", defaultValue="NA") String auth) {
		
		UserSimpleCrudDTO responseDTO = new UserSimpleCrudDTO();
		
		try {			
			responseDTO.setMessage("Hello World");
			return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserSimpleCrudDTO> testPOST(
			@RequestHeader(name = "Authorization", defaultValue="NA") String auth,
			@RequestBody UserSimpleDTO requestDTO) {
		
		UserSimpleCrudDTO responseDTO = new UserSimpleCrudDTO();
		
		try {			
			responseDTO.setMessage("Received: " + requestDTO.getUsername() + " " + requestDTO.getPassword());
			return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/restricted", method = RequestMethod.POST)
	public ResponseEntity<UserSimpleCrudDTO> testPOSTRestricted(
			@RequestHeader(name = "Authorization", defaultValue="NA") String auth,
			@RequestBody UserSimpleDTO requestDTO) {
		
		UserSimpleCrudDTO responseDTO = new UserSimpleCrudDTO();
		
		try {						
			if (auth.equals("NA")) {
				return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
			} else {
				String b64Credentials = auth.substring("Basic".length()).trim();
				final String[] credentials = new String(Base64.getDecoder().decode(b64Credentials), 
						Charset.forName("UTF-8")).split(":", 2);
			    final String uname = credentials[0];
			    final String psswd = credentials[1];
			    responseDTO.setMessage("Cannot login with credentials: " + uname + " " + psswd);
			    if (!uname.equals("gmarciani") || !psswd.equals("password")) {
			    	return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
			    }
			    responseDTO.setMessage("Received: " + requestDTO.getUsername() + " " + requestDTO.getPassword() + 
			    		" logged as " + uname + " " + psswd);
			}			
			return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.OK);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserSimpleCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
