package metricapp.entity.external;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.Element;

@Data
@EqualsAndHashCode(callSuper=true)
public class ContextFactor extends Element {

	private String title;
	private String description;
	private String criticallyValue;
	private String decayReason;
	private String instanceProjectId;
	private String contextFactorState;
	
	//creatorId, state creationDate is in Element

}
