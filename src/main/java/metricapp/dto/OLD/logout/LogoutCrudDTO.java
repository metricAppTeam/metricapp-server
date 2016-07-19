package metricapp.dto.OLD.logout;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.MessageDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class LogoutCrudDTO extends MessageDTO{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3320621680576206150L;


	/**
	 * 
	 */
	private long count;
	
	
	private List<LogoutDTO> logoutList;
	
	public LogoutCrudDTO(){
		this.setLogoutList(new ArrayList<LogoutDTO>());
	}
	
	public void addLogoutToList(LogoutDTO logoutDTO){
		try{
			logoutList.add(logoutDTO);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
