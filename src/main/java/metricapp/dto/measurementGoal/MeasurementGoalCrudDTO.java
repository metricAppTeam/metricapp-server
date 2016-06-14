package metricapp.dto.measurementGoal;

import java.io.Serializable;
import java.util.ArrayList;
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
	

	public MeasurementGoalCrudDTO(){
		this.setMeasurementGoals(new ArrayList<MeasurementGoalDTO>());;
	}
	
	public void addMeasurementGoalToList(MeasurementGoalDTO measurementGoal){
		try{
			this.measurementGoals.add(measurementGoal);
		}
		catch(NullPointerException e){
			this.measurementGoals = new ArrayList<MeasurementGoalDTO>();
			this.measurementGoals.add(measurementGoal);
		}
		
	}
	
	
	
}
