package metricapp.dto.login;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;

@Getter
@Setter()
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class LoginDTO extends DTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6615246884703099064L;
	
	public String response;
	public String username;
	public String password;
	
}
