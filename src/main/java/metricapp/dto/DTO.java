package metricapp.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;	//id of the user managing CRUD
	public String message;
	public String error;
}
