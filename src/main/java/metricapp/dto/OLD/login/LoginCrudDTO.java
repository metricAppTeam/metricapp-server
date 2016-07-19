package metricapp.dto.OLD.login;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.MessageDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class LoginCrudDTO extends MessageDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2926844443273491505L;


	/**
	 * 
	 */
	private long count;
	
	
	private List<LoginDTO> loginList;
	
	public LoginCrudDTO(){
		this.setLoginList(new ArrayList<LoginDTO>());
	}
	
	public void addLoginToList(LoginDTO loginDTO){
		try{
			loginList.add(loginDTO);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
