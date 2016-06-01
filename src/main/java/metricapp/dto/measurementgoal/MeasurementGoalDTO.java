package metricapp.dto.measurementgoal;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import metricapp.dto.GoalDTO;
import metricapp.dto.MetadataDTO;


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
	private InterpretationModelDTO interpretationModel;
	public MetadataDTO metadata;

	public void setFunctionJavascript(String javascript){
		try{
			this.getInterpretationModel().setFunctionJavascript(javascript);
		}
		catch(NullPointerException exception){
			this.setInterpretationModel(new InterpretationModelDTO());
			this.getInterpretationModel().setFunctionJavascript(javascript);
		}
	}
	
	public void setQueryNoSQL(String query){
		try{
			this.getInterpretationModel().setQueryNoSQL(query);
		}
		catch(NullPointerException exception){
			this.setInterpretationModel(new InterpretationModelDTO());
			this.getInterpretationModel().setQueryNoSQL(query);
		}
	}
	
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
