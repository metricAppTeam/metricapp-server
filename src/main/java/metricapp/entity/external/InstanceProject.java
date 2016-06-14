package metricapp.entity.external;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.Element;

@Data
@EqualsAndHashCode(callSuper=true)
public class InstanceProject extends Element {

	private String title;
	private String description;
	private String status;
	private String iteration;
	private String projectManagerId;
	private String rootElementId;

}
