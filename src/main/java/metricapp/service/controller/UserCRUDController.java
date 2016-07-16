package metricapp.service.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.user.UserCrudDTO;
import metricapp.dto.user.UserDTO;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.UserCRUDInterface;
import metricapp.service.spec.repository.UserRepository;

@Service
public class UserCRUDController implements UserCRUDInterface {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory; 
		
	@Override
	public UserCrudDTO getUserByUsername(String username) throws NotFoundException, BadInputException {
		
		if(username == null){
			throw new BadInputException("Id cannot be null");
		}
		
		User user = userRepository.findUserByUsername(username);
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		try{
			userCrudDTO.addUserToList(modelMapperFactory.getLooseModelMapper().map(user, UserDTO.class));
			return userCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("No User found");
		}
	}
	
	
	@Override
	public UserCrudDTO createUser(UserDTO userDTO) throws BadInputException{
		
		if (userDTO.getUsername() == null) {
			throw new BadInputException("Username field is empty");
		}
		if (userDTO.getPassword() == null) {
			throw new BadInputException("Password field is empty");
		}
		if (userDTO.getLastname() == null) {
			throw new BadInputException("Lastname field is empty");
		}
		if (userDTO.getFirstname() == null) {
			throw new BadInputException("Firstname field is empty");
		}
		if (userDTO.getRole() == null) {
			throw new BadInputException("Role field is empty");
		}/*
		if (userDTO.getEmail() == null) {
			throw new BadInputException("Email field is empty");
		}
		if (userDTO.getBirthday() == null) {
			throw new BadInputException("Birthday field is empty");
		}
		if (userDTO.getMobile() == null) {
			throw new BadInputException("Mobile field is empty");
		}
		if (userDTO.getOnline() == null) {
			throw new BadInputException("Online field is empty");
		}
		if (userDTO.getPicture() == null) {
			throw new BadInputException("Picture field is empty");
		}
		if (userDTO.getGender() == null) {
			throw new BadInputException("Gender field is empty");
		}*/
		
		User newUser = modelMapperFactory.getLooseModelMapper().map(userDTO, User.class);
		
		if(userRepository.exists(newUser.getUsername())){
			throw new BadInputException("Username it is already in use");
		}
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		userCrudDTO.setRequest("create User");
		userCrudDTO.addUserToList(
					modelMapperFactory.getLooseModelMapper().map(userRepository.save(newUser), UserDTO.class));
		return userCrudDTO;
	}

	@Override
	public UserCrudDTO updateUser(UserDTO userDTO)  
			throws BadInputException, NotFoundException, IllegalStateTransitionException, DBException{
			// DA FARE
		return null;
	}
	
	@Override
	public UserCrudDTO getAllUsers(){
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		
		Iterator<User> userIter = userRepository.findAll().iterator();
		
		while(userIter.hasNext()){
			
			userCrudDTO.addUserToList(modelMapperFactory.getLooseModelMapper().map(userIter.next(), UserDTO.class));
			
		}	
		return userCrudDTO;
	}	
}