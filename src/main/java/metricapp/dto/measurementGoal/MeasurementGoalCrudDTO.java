package metricapp.dto.measurementGoal;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MeasurementGoalCrudDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1980306938916100958L;

	/**
	 * 
	 */
	
	
	
	@JsonInclude(Include.NON_NULL)
	
	private String id;
	
	private String object;
	
	private String purpose;
	
	private String qualityFocus;
	
	private String viewPoint;
	
	
	
}
