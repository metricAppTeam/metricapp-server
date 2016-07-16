package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.login.LoginCrudDTO;
import metricapp.dto.login.LoginDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.LoginException;
import metricapp.service.controller.LoginCRUDController;

@CrossOrigin 
@RestController
@RequestMapping(("/login"))

public class LoginRestController {
	
	@Autowired 
	LoginCRUDController loginCRUDController;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<LoginCrudDTO> createLoginDTO(@RequestBody LoginDTO loginDTO){
		LoginCrudDTO loginCrudDTO = new LoginCrudDTO();
		try{
			loginCrudDTO = loginCRUDController.createLogin(loginDTO);
			
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.CREATED);			
		} catch (BadInputException e){
			loginCrudDTO.setError("Bad Request");
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.BAD_REQUEST);
		}catch (LoginException e){
			loginCrudDTO.setError("Incorrect password");
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			loginCrudDTO.setError("Server Error");
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
