package metricator.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public String message;
	public String error;
}
