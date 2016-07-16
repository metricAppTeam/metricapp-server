package metricapp.dto.logout;

import org.modelmapper.PropertyMap;

import metricapp.entity.auth.Login;

public class LogoutMap extends PropertyMap<Login, LogoutDTO>{

	@Override
	protected void configure() {
		
		map().setResponse(source.getResponse());
	}

}