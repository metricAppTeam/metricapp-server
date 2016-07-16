package metricapp.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.login.LoginCrudDTO;
import metricapp.dto.login.LoginDTO;
import metricapp.dto.user.UserCrudDTO;
import metricapp.dto.user.UserDTO;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.LoginException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.LoginCRUDInterface;
import metricapp.service.spec.repository.UserRepository;

@Service
public class LoginCRUDController implements LoginCRUDInterface {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
		
	@Override
	public LoginCrudDTO getLogin(String username, String password) throws NotFoundException, BadInputException, DBException, LoginException{
		
		if(username == null){
			throw new BadInputException("Id cannot be null");
		}
		if(password == null){
			throw new BadInputException("Password cannot be null");
		}
		
		User user;
		
		if(userRepository.exists(username))
			user = userRepository.findUserByUsername(username);
		
		else{
			throw new NotFoundException("User not be found");
		}
		
		if(!user.getPassword().equals(password))
			throw new LoginException("Password is incorrect");
		
		user.setOnline("true");
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		userCrudDTO.setRequest("update User");
		userCrudDTO.addUserToList(
					modelMapperFactory.getLooseModelMapper().map(userRepository.save(user), UserDTO.class));
		
		LoginDTO newLogin = new LoginDTO();
		newLogin.response =  "OK";
		
		LoginCrudDTO loginCrudDTO = new LoginCrudDTO();
		try{
			loginCrudDTO.addLoginToList(modelMapperFactory.getLooseModelMapper().map(newLogin, LoginDTO.class));
			return loginCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("Impossible");
		}
	}
}