package metricapp.service.controller.OLD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.OLD.logout.LogoutCrudDTO;
import metricapp.dto.OLD.logout.LogoutDTO;
import metricapp.dto.user.UserCrudDTO;
import metricapp.dto.user.UserDTO;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BadInputException;
import metricapp.exception.IDException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.OLD.LogoutCRUDInterface;
import metricapp.service.spec.repository.UserRepository;

@Service
public class LogoutCRUDController implements LogoutCRUDInterface {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
		
	@Override
	public LogoutCrudDTO createLogout(LogoutDTO logoutDTO) throws NotFoundException, BadInputException, IDException{
		
		if (logoutDTO.getUsername() == null) {
			throw new IDException("Username field is empty");
		}
		
		User user;
		
		if(userRepository.exists(logoutDTO.getUsername()))
			user = userRepository.findUserByUsername(logoutDTO.getUsername());
		else{
			throw new IDException("Username not be found");
		}
		
		user.setOnline("false");
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		userCrudDTO.setRequest("update User");
		userCrudDTO.addUserToList(
					modelMapperFactory.getLooseModelMapper().map(userRepository.save(user), UserDTO.class));
		
		logoutDTO.setResponse("OK");
		
		LogoutCrudDTO logoutCrudDTO = new LogoutCrudDTO();
		try{
			logoutCrudDTO.addLogoutToList(modelMapperFactory.getLooseModelMapper().map(logoutDTO, LogoutDTO.class));
			return logoutCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("Impossible");
		}
	}
}