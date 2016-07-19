package metricapp.entity.event;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metricapp.utility.RandomGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("metricapp.Event")
@Document(collection = "events")
public class Event {
	
	@Id
	private String id;
	@CreatedDate
	private Long creationDate;
	private String authorId;
	private EventScope eventScope;
	private String eventScopeId;	
	private ArtifactScope artifactScope;
	private String artifactId;
	private String description;	
	
	public String getTopicName() {
		return this.getEventScope() + "-" + this.getEventScopeId();
	}
	
	public static Event randomEvent() {
		Event event = new Event();
		event.setId(RandomGenerator.randomString());
		event.setCreationDate(RandomGenerator.randomLong());
		event.setAuthorId(RandomGenerator.randomString());
		event.setEventScope(RandomGenerator.randomEnum(EventScope.class));
		event.setEventScopeId(RandomGenerator.randomString());
		event.setArtifactScope(RandomGenerator.randomEnum(ArtifactScope.class));
		event.setArtifactId(RandomGenerator.randomString());
		event.setDescription(RandomGenerator.randomString());
		return event;
	}

}
