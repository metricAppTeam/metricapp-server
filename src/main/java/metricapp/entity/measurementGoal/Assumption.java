package metricapp.entity.measurementGoal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.Element;

@Data
@Document
@EqualsAndHashCode(callSuper=false)
public class Assumption extends Element{


	
	private String description;
}
