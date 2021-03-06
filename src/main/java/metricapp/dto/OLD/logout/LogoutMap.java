package metricapp.dto.OLD.logout;

import org.modelmapper.PropertyMap;

import metricapp.entity.OLD.auth.Login;

public class LogoutMap extends PropertyMap<Login, LogoutDTO>{

	@Override
	protected void configure() {
		
		map().setResponse(source.getResponse());
		map().setUsername(source.getUsername());
	}

}
