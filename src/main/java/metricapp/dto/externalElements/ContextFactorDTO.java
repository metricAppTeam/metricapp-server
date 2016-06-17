package metricapp.dto.externalElements;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.dto.DTO;

@Data
@EqualsAndHashCode(callSuper=true)
public class ContextFactorDTO extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8463590138094142422L;

	private String title;
	private String description;
	private String criticallyValue;
	private String decayReason;
	private String instanceProjectId;
	private String contextFactorState;
}
