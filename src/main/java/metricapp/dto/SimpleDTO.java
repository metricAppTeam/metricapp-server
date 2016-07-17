package metricapp.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SimpleDTO implements Serializable {

	private static final long serialVersionUID = 2437950231479936890L;
	
	private String request;
	private String message;

}
