package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.login.LoginCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.LoginException;
import metricapp.exception.NotFoundException;
import metricapp.service.controller.LoginCRUDController;

@CrossOrigin 
@RestController
@RequestMapping(("/login"))

public class LoginRestController {
	
	@Autowired 
	LoginCRUDController loginCRUDController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<LoginCrudDTO> getLoginDTO(
			@RequestParam(value="username", defaultValue="NA") String username,
			@RequestParam(value="password", defaultValue="NA") String password){
		
		LoginCrudDTO loginCrudDTO = new LoginCrudDTO();
		
		try{
			if(!username.equals("NA") && !password.equals("NA")){
				loginCrudDTO = loginCRUDController.getLogin(username,password);
			}
			else{
				loginCrudDTO.setError("No parameters given");
				return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.OK);
		} catch (BadInputException e){
			loginCrudDTO.setError("No user have been found");
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e){
			loginCrudDTO.setError("No user have been found");
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.NOT_FOUND);
		} catch (LoginException e){
			loginCrudDTO.setError("Incorrect password");
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e){
			loginCrudDTO.setError("Server Error");
			e.printStackTrace();
			
			return new ResponseEntity<LoginCrudDTO>(loginCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
