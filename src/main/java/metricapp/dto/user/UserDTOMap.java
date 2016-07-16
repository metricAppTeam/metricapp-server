package metricapp.dto.user;

import org.modelmapper.PropertyMap;

import metricapp.entity.stakeholders.User;

public class UserDTOMap extends PropertyMap<UserDTO, User>{

	@Override
	protected void configure() {
		map().setUsername(source.getUsername());
		map().setPassword(source.getPassword());
		map().setRole(source.getRole());
		map().setFirstname(source.getFirstname());
		map().setLastname(source.getLastname());
		map().setGender(source.getGender());
		map().setBirthday(source.getBirthday());
		map().setEmail(source.getEmail());		
		map().setPic(source.getPicture());
		map().setMobile(source.getMobile());
		map().setOnline(source.getOnline());
		map().setWebsite(source.getWebsite());
	}

}
