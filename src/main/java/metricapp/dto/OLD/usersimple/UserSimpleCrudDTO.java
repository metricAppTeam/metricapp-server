package metricapp.dto.OLD;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;
import metricapp.entity.OLD.user.simple.UserSimple;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class UserSimpleCrudDTO extends SimpleDTO {
	
	private static final long serialVersionUID = 1893420940900870595L;
	
	private long count;	
	public ArrayList<UserSimpleDTO> usersSimpleDTO;	
	
	public UserSimpleCrudDTO() {
		this.setUsersSimpleDTO(new ArrayList<UserSimpleDTO>());
	}
	
	public void addUserDTO(UserSimpleDTO user) {
		if (this.usersSimpleDTO == null) {
			this.usersSimpleDTO = new ArrayList<UserSimpleDTO>();
		}
		this.usersSimpleDTO.add(user);		
		this.count = this.usersSimpleDTO.size();
	}
	
	public void addAllUserSimpleDTO(List<UserSimpleDTO> users) {
		if (this.usersSimpleDTO == null) {
			this.usersSimpleDTO = new ArrayList<UserSimpleDTO>();
		}
		for (UserSimpleDTO user : users) {
			this.usersSimpleDTO.add(user);
		}
		this.count = this.usersSimpleDTO.size();
	}
	
	public void addUserSimple(UserSimple user, ModelMapper mapper) {
		if (this.usersSimpleDTO == null) {
			this.usersSimpleDTO = new ArrayList<UserSimpleDTO>();
		}
		UserSimpleDTO userDTO = mapper.map(user, UserSimpleDTO.class);
		this.usersSimpleDTO.add(userDTO);		
		this.count = this.usersSimpleDTO.size();
	}
	
	public void addAllUserSimple(List<UserSimple> users, ModelMapper mapper) {
		if (this.usersSimpleDTO == null) {
			this.usersSimpleDTO = new ArrayList<UserSimpleDTO>();
		}
		for (UserSimple user : users) {
			UserSimpleDTO userDTO = mapper.map(user, UserSimpleDTO.class);
			this.usersSimpleDTO.add(userDTO);
		}
		this.count = this.usersSimpleDTO.size();
	}
}
