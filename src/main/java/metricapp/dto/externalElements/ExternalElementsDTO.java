package metricapp.dto.externalElements;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.dto.MessageDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.dto.question.QuestionDTO;

@Data
@EqualsAndHashCode(callSuper=true)
public class ExternalElementsDTO extends MessageDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4614666767287565932L;
	
	public String measurementGoalId;
	public ArrayList<AssumptionDTO> assumptions;
	public ArrayList<ContextFactorDTO> contextFactors;
	public OrganizationalGoalDTO organizationalGoal;
	public InstanceProjectDTO instanceProject; 
	public ArrayList<MetricDTO> metrics;
	public ArrayList<QuestionDTO> questions;
	
}
