package metricapp.entity.measurementGoal;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.Element;

@Data
@EqualsAndHashCode(callSuper=false)
@Document
public class Context extends Element{

	
	
	private String description;
}
