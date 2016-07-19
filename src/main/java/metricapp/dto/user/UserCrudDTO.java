package metricapp.dto.user;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.MessageDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class UserCrudDTO extends MessageDTO {
	
	private static final long serialVersionUID = 7861018504763722282L;

	private long count;
	
	
	private List<UserDTO> userList;
	
	public UserCrudDTO(){
		this.setUserList(new ArrayList<UserDTO>());
	}
	
	public void addUserToList(UserDTO userDTO){
		try{
			userList.add(userDTO);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
