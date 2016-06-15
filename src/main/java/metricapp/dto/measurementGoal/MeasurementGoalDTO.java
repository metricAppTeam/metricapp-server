package metricapp.dto.measurementGoal;


import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import metricapp.dto.GoalDTO;
import metricapp.dto.MetadataDTO;
import metricapp.dto.bus.PointerBusDTO;
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
	public String OrganizationalGoalId;
	public List<PointerBusDTO> metrics;
	public List<PointerBusDTO> questions;
	public String metricatorId;
	public List<String> questionersId;
	public List<PointerBusDTO> contextFactors;
	public List<PointerBusDTO> assumptions;
	public InterpretationModelDTO interpretationModel;
	public MetadataDTO metadata;
}

