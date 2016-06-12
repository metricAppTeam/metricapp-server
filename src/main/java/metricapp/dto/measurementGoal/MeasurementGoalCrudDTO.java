package metricapp.dto.measurementGoal;

import java.io.Serializable;
import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import metricapp.dto.MessageDTO;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class MeasurementGoalCrudDTO extends MessageDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1980306938916100958L;
	
	private List<MeasurementGoalDTO> measurementGoals; 
	
	private String scope;
	
	private String userIdCrud;
	
	private OrganizationalGoalDTO organizationalGoal;
	
}
