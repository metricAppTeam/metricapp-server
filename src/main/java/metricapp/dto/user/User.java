package metricapp.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class User {
	
	private Credential credential;
	
	private Role role;
	
	private Profile profile;
}
