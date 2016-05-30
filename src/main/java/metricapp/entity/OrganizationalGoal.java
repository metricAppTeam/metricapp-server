package metricapp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class OrganizationalGoal extends AbstractGoal {
	private String type;
	private String object;
	private String magnitude;
	private String timeframe;
	private String organizationalScope;
	private String focus;
	private String constraints;
	private String relationship;
	
}
