package metricapp.entity.external;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.Element;

@Data
@EqualsAndHashCode(callSuper=true)
public class Assumption extends Element {

	private String title;
	private String description;
	private String criticallyValue;
	private String validationState;
	private String invalidationReason;
	private String instanceProjectId;
	
	//creatorId e creationDate is in Element
	
}