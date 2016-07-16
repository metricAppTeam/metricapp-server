package metricapp.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.logout.LogoutCrudDTO;
import metricapp.dto.logout.LogoutDTO;
import metricapp.dto.user.UserCrudDTO;
import metricapp.dto.user.UserDTO;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.LoginException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.LogoutCRUDInterface;
import metricapp.service.spec.repository.UserRepository;

@Service
public class LogoutCRUDController implements LogoutCRUDInterface {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
		
	@Override
	public LogoutCrudDTO getLogout(String username) throws NotFoundException, BadInputException, DBException, LoginException{
		
		if(username == null){
			throw new BadInputException("Id cannot be null");
		}
		
		User user;
		
		if(userRepository.exists(username))
			user = userRepository.findUserByUsername(username);
		else{
			throw new NotFoundException("User not be found");
		}
		
		user.setOnline("false");
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		userCrudDTO.setRequest("update User");
		userCrudDTO.addUserToList(
					modelMapperFactory.getLooseModelMapper().map(userRepository.save(user), UserDTO.class));
		
		LogoutDTO newLogout = new LogoutDTO();
		newLogout.response =  "OK";
		
		LogoutCrudDTO logoutCrudDTO = new LogoutCrudDTO();
		try{
			logoutCrudDTO.addLogoutToList(modelMapperFactory.getLooseModelMapper().map(newLogout, LogoutDTO.class));
			return logoutCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("Impossible");
		}
	}
}