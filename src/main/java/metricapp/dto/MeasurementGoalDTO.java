package metricapp.dto;

import java.util.List;

public class MeasurementGoalDTO {
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
	
	public class InterpretationModelDTO{
		public String functionJavascript;
		public String queryParameter;
	}
}
