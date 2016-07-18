package metricapp.dto.user;

import org.modelmapper.PropertyMap;

import metricapp.entity.stakeholders.User;

public class UserMap extends PropertyMap<User, UserDTO>{

	@Override
	protected void configure() {
		
		map().setUsername(source.getUsername());
		map().setPassword(source.getPassword());
		
		map().setFirstname(source.getFirstname());
		map().setLastname(source.getLastname());
		map().setGender(source.getGender());
		map().setBirthday(source.getBirthdayAsString());
		
		map().setEmail(source.getEmail());
		map().setMobile(source.getMobile());
		map().setOnline(source.getOnline());
		
		map().setRole(source.getRole());
		map().setPic(source.getPic());
		map().setWebsite(source.getWebsite());
		map().setBio(source.getBio());
	}

}
