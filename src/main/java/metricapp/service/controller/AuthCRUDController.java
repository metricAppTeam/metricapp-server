package metricapp.service.controller;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.entity.user.UserSimple;
import metricapp.exception.BadInputException;
import metricapp.exception.UnauthorizedException;
import metricapp.service.spec.controller.AuthCRUDInterface;
import metricapp.service.spec.repository.UserSimpleRepository;

@Service
public class AuthCRUDController implements AuthCRUDInterface {
	
	@Autowired
	private UserSimpleRepository userRepo;

	@Override
	public void authenticate(String auth) throws BadInputException, UnauthorizedException {
		if (auth == null) {
			throw new BadInputException("Auth cannot be null");
		}
		
		if (!auth.startsWith("Basic")) {
			throw new BadInputException("Auth must start with authentication protocol name");
		}
		
		String b64Credentials = auth.substring("Basic".length()).trim();
		final String[] credentials = new String(Base64.getDecoder().decode(b64Credentials), 
				Charset.forName("UTF-8")).split(":", 2);
	    final String uname = credentials[0];
	    final String psswd = credentials[1];
	    
	    UserSimple user = userRepo.findByUsername(uname);
	    
	    if (user != null && user.getPassword().equals(psswd)) {
	    	return;
	    } else {
	    	throw new UnauthorizedException();
	    }		
	}	

}
	