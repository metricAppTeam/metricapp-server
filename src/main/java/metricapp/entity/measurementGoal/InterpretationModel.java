package metricapp.entity.measurementGoal;


import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public class InterpretationModel {
	
	private String functionJavascript;
	private String queryNoSQL;
}
