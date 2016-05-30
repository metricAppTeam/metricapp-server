package metricapp.entity.measurementGoal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class InterpretationModel {
	
	private String functionJavascript;
	private String queryNoSQL;
}
