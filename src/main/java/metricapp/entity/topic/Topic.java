package metricapp.entity.topic;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.entity.stakeholders.User;

@Data
@TypeAlias("metricapp.Topic")
@Document(collection = "topics")
public class Topic {
	
	@Id
	private String id;
	@CreatedDate
	private LocalDate creationDate;
	@Indexed(unique = true)
	private String name;
	@DBRef
	private List<User> subscribers;

}
