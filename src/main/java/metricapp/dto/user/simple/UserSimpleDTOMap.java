package metricapp.dto.user.simple;

import org.modelmapper.PropertyMap;

import metricapp.entity.user.UserSimple;

public class UserSimpleDTOMap extends PropertyMap<UserSimpleDTO, UserSimple> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setUsername(source.getUsername());
		map().setPassword(source.getPassword());
	}

}
