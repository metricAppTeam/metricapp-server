package metricapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MeasurementGoalCrudDTO extends GoalDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@JsonInclude(Include.NON_NULL)
	private String userId;	//id of the user managing CRUD
	
	private String id;
	
	private String object;
	
	private String purpose;
	
	private String qualityFocus;
	
	private String viewPoint;
	
	
	
}
