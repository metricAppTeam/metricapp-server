package metricapp.service.controller.OLD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.OLD.login.LoginCrudDTO;
import metricapp.dto.OLD.login.LoginDTO;
import metricapp.dto.user.UserCrudDTO;
import metricapp.dto.user.UserDTO;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.IDException;
import metricapp.exception.LoginException;
import metricapp.exception.NotFoundException;
import metricapp.service.repository.BusUserRepository;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.OLD.LoginCRUDInterface;
import metricapp.service.spec.repository.UserRepository;

@Service
public class LoginCRUDController implements LoginCRUDInterface {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BusUserRepository busUserRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;

	@Override
	public LoginCrudDTO createLogin(LoginDTO loginDTO) throws NotFoundException, BadInputException, LoginException, BusException, IDException{
		
		if (loginDTO.getUsername() == null) {
			throw new LoginException("Username field is empty");
		}
		if (loginDTO.getPassword() == null) {
			throw new LoginException("Password field is empty");
		}
		
		User user;
		
		try{
			user = userRepository.findUserByUsername(loginDTO.getUsername());
		}
		catch(Exception e){
			throw new IDException("User not be found");
		}
		
		User busUser;
		try{
			busUser = busUserRepository.findUserByUsername(loginDTO.getUsername());
		}
		catch(Exception e){
			throw new BusException("User not found in bus repository");
		}
		
		if(!busUser.getPassword().equals(loginDTO.getPassword()))
			throw new LoginException("Password is incorrect");
		user.setOnline("true");
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		userCrudDTO.setRequest("update User");
		userCrudDTO.addUserToList(
					modelMapperFactory.getLooseModelMapper().map(userRepository.save(user), UserDTO.class));
		loginDTO.setResponse("OK");
		
		LoginCrudDTO loginCrudDTO = new LoginCrudDTO();
		try{
			loginCrudDTO.addLoginToList(modelMapperFactory.getLooseModelMapper().map(loginDTO, LoginDTO.class));
			return loginCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("Impossible");
		}
	}
}