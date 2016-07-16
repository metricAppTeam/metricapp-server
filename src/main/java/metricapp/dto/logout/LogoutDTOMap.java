package metricapp.dto.logout;

import org.modelmapper.PropertyMap;

import metricapp.entity.auth.Login;

public class LogoutDTOMap extends PropertyMap<LogoutDTO, Login>{

	@Override
	protected void configure() {
		
		map().setResponse(source.getResponse());
	}

}
