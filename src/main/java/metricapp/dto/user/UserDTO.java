package metricapp.dto.user;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;
import metricapp.entity.stakeholders.Role;
import metricapp.entity.user.User.Gender;

@Getter
@Setter()
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class UserDTO extends DTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6615246884703099064L;
	
	public String username;
	public String password;
	public String firstname;
	public String lastname;
	public Gender gender;
	public LocalDate birthday;
	public String picture;
	public String website;
	public String email;
	public String mobile;
	public String online;
	public Role role;
	
}
