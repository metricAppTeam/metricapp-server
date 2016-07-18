package metricapp.dto.team;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;
import metricapp.entity.stakeholders.User;

@Getter
@Setter()
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class TeamDTO extends DTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4204012738623169851L;
	
	public String id;
	public String name;
	public String gridName;
	//public String tsCreate;
	//public String tsUpdate;
	public ArrayList<User> expert;
	public ArrayList<User> questioners;
	public ArrayList<User> metricators;
	
	public User extrauser;
	
	
	
}
