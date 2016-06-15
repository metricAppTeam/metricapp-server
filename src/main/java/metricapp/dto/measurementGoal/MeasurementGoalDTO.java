package metricapp.dto.measurementGoal;


import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import metricapp.dto.GoalDTO;
import metricapp.dto.MetadataDTO;
import metricapp.dto.measurementGoal.InterpretationModelDTO;
import metricapp.entity.external.PointerBus;


@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class MeasurementGoalDTO extends GoalDTO{
	/**
	 *  */
	private static final long serialVersionUID = 1L;
	public String id;
	public String name;
	public String object;
	public String viewPoint;
	public String focus;
	public String purpose;
	public PointerBus OrganizationalGoalId;
	public List<PointerBus> metrics;
	public List<PointerBus> questions;
	public String metricatorId;
	public List<String> questionersId;
	public List<PointerBus> contextFactors;
	public List<PointerBus> assumptions;
	public InterpretationModelDTO interpretationModel;
	public MetadataDTO metadata;
}

