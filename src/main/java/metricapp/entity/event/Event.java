package metricapp.entity.event;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@TypeAlias("metricapp.Event")
@Document(collection = "events")
public class Event {
	
	@Id
	private String id;
	@CreatedDate
	private LocalDate creationDate;
	private String authorId;
	private EventScope scope;
	private String artifactId;	
	private String description;	
	
	public String getTopicName() {
		return this.getScope() + "-" + this.getArtifactId();
	}

}
