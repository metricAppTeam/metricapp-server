package metricapp.dto.OLD;

import org.modelmapper.PropertyMap;

import metricapp.entity.OLD.user.simple.UserSimple;

public class UserSimpleMap extends PropertyMap<UserSimple, UserSimpleDTO> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setUsername(source.getUsername());
		map().setPassword(source.getPassword());
	}
	
}
