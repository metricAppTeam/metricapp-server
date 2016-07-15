package metricapp.dto.team;

import java.io.Serializable;
import java.time.LocalDate;
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
	public String grid_name;
	public LocalDate ts_create;
	public LocalDate ts_update;
	public ArrayList<User> expert;
	public ArrayList<User> questioners;
	public ArrayList<User> metricators;
	
}
