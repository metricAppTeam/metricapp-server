package metricapp.dto.externalElements;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.dto.DTO;

@Data
@EqualsAndHashCode(callSuper=true)
public class AssumptionDTO extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3143418665363643027L;

	private String title;
	private String description;
	private String criticallyValue;
	private String validationState;
	private String invalidationReason;
	private String instanceProjectId;
}
