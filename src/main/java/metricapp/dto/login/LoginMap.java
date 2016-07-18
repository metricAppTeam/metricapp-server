package metricapp.dto.login;

import org.modelmapper.PropertyMap;

import metricapp.OLD.auth.Login;

public class LoginMap extends PropertyMap<Login, LoginDTO>{

	@Override
	protected void configure() {
		
		map().setResponse(source.getResponse());
		map().setPassword(source.getPassword());
		map().setUsername(source.getUsername());
	}

}
