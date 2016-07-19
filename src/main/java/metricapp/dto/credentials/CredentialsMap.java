package metricapp.dto.credentials;

import org.modelmapper.PropertyMap;

import metricapp.entity.credentials.Credentials;

public class CredentialsMap extends PropertyMap<Credentials, CredentialsDTO> {

	@Override
	protected void configure() {
		map().setUsername(source.getUsername());
		map().setPassword(source.getPassword());
	}
	
}
