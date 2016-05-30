package metricapp.entity.measurementGoal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.entity.Element;

@Data
@Document
public class Assumption extends Element{


	
	private String description;
}
