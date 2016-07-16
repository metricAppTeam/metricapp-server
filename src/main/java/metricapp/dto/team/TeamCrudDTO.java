package metricapp.dto.team;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.MessageDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class TeamCrudDTO extends MessageDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7861018504763722282L;

	/**
	 * 
	 */
	private long count;
	
	
	private List<TeamDTO> teamList;
	
	public TeamCrudDTO(){
		this.setTeamList(new ArrayList<TeamDTO>());
	}
	
	public void addTeamToList(TeamDTO teamDTO){
		try{
			teamList.add(teamDTO);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
