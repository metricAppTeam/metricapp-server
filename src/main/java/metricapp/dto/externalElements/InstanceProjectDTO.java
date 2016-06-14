package metricapp.dto.externalElements;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.dto.DTO;

@Data
@EqualsAndHashCode(callSuper=true)
public class InstanceProjectDTO extends DTO {/**
	 * 
	 */
	private static final long serialVersionUID = 8287320925598981438L;

	private String title;
	private String description;
	private String status;
	private String iteration;
	private String projectManagerId;
	private String rootElementId;

}
