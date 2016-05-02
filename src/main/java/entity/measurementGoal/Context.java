package entity.measurementGoal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Context {

	@Id
	private String id;
	
	@Version
	private Long version;
}
