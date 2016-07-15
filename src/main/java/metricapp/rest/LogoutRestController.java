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
import metricapp.dto.logout.LogoutCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.LoginException;
import metricapp.exception.NotFoundException;
import metricapp.service.controller.LoginCRUDController;
import metricapp.service.controller.LogoutCRUDController;

@CrossOrigin 
@RestController
@RequestMapping(("/logout"))

public class LogoutRestController {
	
	@Autowired 
	LogoutCRUDController logoutCRUDController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<LogoutCrudDTO> getLogoutDTO(
			@RequestParam(value="username", defaultValue="NA") String username){
		
		LogoutCrudDTO logoutCrudDTO = new LogoutCrudDTO();
		
		try{
			if(!username.equals("NA")){
				logoutCrudDTO = logoutCRUDController.getLogout(username);
			}
			else{
				logoutCrudDTO.setError("No parameters given");
				return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.OK);
		} catch (BadInputException e){
			logoutCrudDTO.setError("No user have been found");
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e){
			logoutCrudDTO.setError("No user have been found");
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.NOT_FOUND);
		} catch (LoginException e){
			logoutCrudDTO.setError("Incorrect password");
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e){
			logoutCrudDTO.setError("Server Error");
			e.printStackTrace();
			
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
