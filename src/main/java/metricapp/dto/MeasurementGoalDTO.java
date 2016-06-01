package metricapp.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper=false)
public class MeasurementGoalDTO extends GoalDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	public String name;
	public String object;
	public String viewPoint;
	public String focus;
	public String OrganizationalGoalId;
	public List<String> metricIdList;
	public List<String> questionIdList;
	public String metricatorId;
	public List<String> questionerIdList;
	public List<String> contextFactorIdList;
	public List<String> assumptionIdList;
	public InterpretationModelDTO interpretationModel;
	public MetadataDTO metadata;

	@Data
	@NoArgsConstructor
	public class InterpretationModelDTO implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public String functionJavascript;
		public String queryNoSQL;
	
	}
	
	public void setInterpretationModel(InterpretationModelDTO interpretationModelDTO){
		this.interpretationModel = new InterpretationModelDTO();
	}
}
