package metricapp.dto.user.simple;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;

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
	
	public void addTopicToList(UserSimpleDTO userSimple) {
		try {
			this.usersSimpleDTO.add(userSimple);
		} catch(NullPointerException e) {
			this.usersSimpleDTO = new ArrayList<UserSimpleDTO>();
			this.usersSimpleDTO.add(userSimple);
		} finally {
			this.count++;
		}		
	}
}
