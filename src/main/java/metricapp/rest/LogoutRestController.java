package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.logout.LogoutCrudDTO;
import metricapp.dto.logout.LogoutDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.IDException;
import metricapp.service.controller.LogoutCRUDController;

@CrossOrigin 
@RestController
@RequestMapping(("/logout"))

public class LogoutRestController {
	
	@Autowired 
	LogoutCRUDController logoutCRUDController;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<LogoutCrudDTO> createLogoutDTO(@RequestBody LogoutDTO logoutDTO){
		LogoutCrudDTO logoutCrudDTO = new LogoutCrudDTO();
		try{
			logoutCrudDTO = logoutCRUDController.createLogout(logoutDTO);
		
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.CREATED);			
		} catch (BadInputException e){
			logoutCrudDTO.setError("Bad Request");
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.BAD_REQUEST);
		}catch (IDException e){
			logoutCrudDTO.setError("Incorrect Username");
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logoutCrudDTO.setError("Server Error");
			return new ResponseEntity<LogoutCrudDTO>(logoutCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
