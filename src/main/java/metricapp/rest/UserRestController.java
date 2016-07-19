package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.user.UserCrudDTO;
import metricapp.dto.user.UserDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.DBException;
import metricapp.exception.IDException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.controller.UserCRUDController;

@CrossOrigin 
@RestController
@RequestMapping(("/users"))
public class UserRestController {
	
	@Autowired 
	UserCRUDController userCRUDController;
	
	/**
	 * Get Method for search user by username
	 * @param username
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UserCrudDTO> getUserDTO(
			@RequestParam(value="username", defaultValue="NA") String username){
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		
		try{
			if(!username.equals("NA") && !username.equals("all")){
				userCrudDTO = userCRUDController.getUserByUsername(username);
			}
			else if(username.equals("all")){
				userCrudDTO = userCRUDController.getAllUsers();
			}
			else{
				userCrudDTO.setError("No parameters given");
				return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.OK);
			
		} catch (BadInputException e){
			userCrudDTO.setError("No user have been found");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e){
			userCrudDTO.setError("No user have been found");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e){
			userCrudDTO.setError("Server Error");
			e.printStackTrace();
			
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UserCrudDTO> updateUserDTO(@RequestBody UserDTO userDTO){
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		try {
			userCrudDTO = userCRUDController.updateUser(userDTO);
			
			if(userCrudDTO == null){
				return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
			}
			else{
				return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.OK);
			}
			
		} catch (BadInputException e) {
			userCrudDTO.setError("Bad Input");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
		}catch (IDException e) {
			userCrudDTO.setError("Username error");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			userCrudDTO.setError("Not Found");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.NOT_FOUND);
		} catch (IllegalStateTransitionException e) {
			userCrudDTO.setError("Illegal state transition");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.FORBIDDEN);
		} catch (DBException e) {
			userCrudDTO.setError("DB_Exception");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.CONFLICT);
		} catch (Exception e){
			userCrudDTO.setError("Internal Server Error");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="/bus",method = RequestMethod.GET)
	public ResponseEntity<UserCrudDTO> getUserOnBus(
			@RequestParam(value="username") String username){
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		
		try{
			if(!username.equals("NA") && !username.equals("all")){
				userCrudDTO = userCRUDController.getBusUserByUsername(username);
			}
			else if(username.equals("all")){
				userCrudDTO = userCRUDController.getAllUsers();
			}
			else{
				userCrudDTO.setError("No parameters given");
				return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.OK);
		
		} catch (BadInputException e){
			userCrudDTO.setError("No user have been found");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e){
			userCrudDTO.setError("No user have been found");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e){
			userCrudDTO.setError("Server Error");
			e.printStackTrace();
		}	
		
		return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserCrudDTO> createUserDTO(@RequestBody UserDTO userDTO){
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		try{
			userCrudDTO = userCRUDController.createUser(userDTO);
			System.out.println("Sending result OK");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.CREATED);			
		} catch (BadInputException e){
			//userCrudDTO.setError("Bad Request: fields are empty?");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (IDException e) {
				userCrudDTO.setError("Username already in use");
				return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (BusException e) {
			e.printStackTrace();
			userCrudDTO.setError("Bus Exception "+ e.toString());
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.CONFLICT);		
		} catch (DBException e) {
			userCrudDTO.setError("DB_Exception");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.CONFLICT);
		} catch (Exception e) {
			userCrudDTO.setError("Server Error");
			return new ResponseEntity<UserCrudDTO>(userCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
