package metricapp.entity.event;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.utility.RandomGenerator;

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
	
	public static Event randomEvent() {
		Event event = new Event();
		event.setId(RandomGenerator.randomString());
		event.setCreationDate(RandomGenerator.randomLocalDate());
		event.setAuthorId(RandomGenerator.randomString());
		event.setScope(RandomGenerator.randomEnum(EventScope.class));
		event.setArtifactId(RandomGenerator.randomString());
		event.setDescription(RandomGenerator.randomString());
		return event;
	}

}
