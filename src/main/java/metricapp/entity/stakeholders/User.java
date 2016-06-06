package metricapp.entity.stakeholders;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends Person {
	
	private Credential credential;
	
	private Role role;
	
	private Profile profile;
}
