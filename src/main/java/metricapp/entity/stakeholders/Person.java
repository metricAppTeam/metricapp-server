package metricapp.entity.stakeholders;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Person {
	@Id
	private String id;
	
	private String name;
}
