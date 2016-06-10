package metricapp.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class MessageDTO extends ResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2799684989672361863L;
	private String userId;
	private String message;
	private String request;
	private String error;
}
