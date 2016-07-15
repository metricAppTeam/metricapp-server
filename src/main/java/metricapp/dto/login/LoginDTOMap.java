package metricapp.dto.login;

import org.modelmapper.PropertyMap;

import metricapp.entity.auth.Login;

public class LoginDTOMap extends PropertyMap<LoginDTO, Login>{

	@Override
	protected void configure() {
		
		map().setResponse(source.getResponse());
	}

}
