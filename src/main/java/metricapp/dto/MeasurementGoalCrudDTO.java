package metricapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class MeasurementGoalCrudDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@JsonInclude(Include.NON_NULL)
	
	private String id;
	
	private String object;
	
	private String purpose;
	
	private String qualityFocus;
	
	private String viewPoint;
	
	
	
}
