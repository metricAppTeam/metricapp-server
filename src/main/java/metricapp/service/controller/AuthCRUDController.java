package metricapp.service.controller;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.credentials.CredentialsDTO;
import metricapp.dto.user.UserCrudDTO;
import metricapp.dto.user.UserDTO;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.UnauthorizedException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.AuthCRUDInterface;
import metricapp.service.spec.repository.BusUserRepositoryInterface;
import metricapp.service.spec.repository.UserRepository;

@Service
public class AuthCRUDController implements AuthCRUDInterface {
	
	@Autowired
	private BusUserRepositoryInterface userBusRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public UserCrudDTO login(CredentialsDTO credentialsDTO) throws BadInputException, UnauthorizedException, BusException {
		String username = credentialsDTO.getUsername();
		String password = credentialsDTO.getPassword();
		
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (password == null) {
			throw new BadInputException("Password cannot be null");
		}
		
		User user = userRepo.findUserByUsername(username);
		
		if (user == null) {
			throw new UnauthorizedException("User cannot be found in local repository");
		}
		
		User busUser;
		try {
			busUser = userBusRepo.findUserByUsername(username);
		} catch(Exception e) {
			throw new BusException("User not found in bus repository");
		}
		
		if(!busUser.getPassword().equals(password)) {
			throw new UnauthorizedException("User password is not correct");
		}
			
		user.setOnline("true");
		
		UserCrudDTO crud = new UserCrudDTO();
		crud.setRequest("LOGIN user WITH username=" + username + "&password=" + password);
		crud.addUserToList(modelMapperFactory.getLooseModelMapper().map(userRepo.save(user), UserDTO.class));
		
		return crud;		
	}

	@Override
	public UserCrudDTO logout(String authUsername) throws BadInputException, UnauthorizedException {
		if (authUsername == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		User user = userRepo.findUserByUsername(authUsername);
		
		if (user == null) {
			throw new UnauthorizedException("User cannot be found in local repository with username=" + authUsername);
		}
		
		user.setOnline("false");
		
		UserCrudDTO crud = new UserCrudDTO();
		crud.setRequest("LOGOUT user WITH username=" + authUsername);
		crud.addUserToList(modelMapperFactory.getLooseModelMapper().map(userRepo.save(user), UserDTO.class));
		
		return crud;
	}

	@Override
	public String authenticate(String auth) throws BadInputException, UnauthorizedException {
		if (auth == null) {
			throw new BadInputException("Auth token cannot be null");
		}
		
		if (!auth.startsWith("Basic")) {
			throw new BadInputException("Auth must start with authentication protocol name");
		}
		
		String b64Credentials = auth.substring("Basic".length()).trim();
		final String[] credentials = new String(Base64.getDecoder().decode(b64Credentials), 
				Charset.forName("UTF-8")).split(":", 2);
	    final String uname = credentials[0];
	    final String psswd = credentials[1];
	    
	    User user = userRepo.findUserByUsername(uname);
	    
	    if (user != null && user.getPassword().equals(psswd)) {
	    	return uname;
	    } else {
	    	throw new UnauthorizedException();
	    }		
	}		

}
	