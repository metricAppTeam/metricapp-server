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
	private EventPhase eventPhase;
	private String authorId;	
	private ArtifactScope artifactScope;
	private String artifactId;
	private String description;
	
	public Event(EventPhase eventPhase, String authorId, ArtifactScope artifactScope, String artifactId, String description) {
		this.eventPhase = eventPhase;
		this.authorId = authorId;
		this.artifactScope = artifactScope;
		this.artifactId = artifactId;
		this.description = description;
	}
	
	/*
	public Event(String authorId, EventScope eventScope, ArtifactScope artifactScope, String artifactId, String description) {
		this.authorId = authorId;
		this.eventScope = eventScope;
		this.artifactScope = artifactScope;
		this.artifactId = artifactId;		
		this.description = description;
	}
	
	public Event(String authorId, ArtifactScope artifactScope, String artifactId, String description) {
		this.authorId = authorId;
		this.eventScope = eventScope;
		this.artifactScope = artifactScope;
		this.artifactId = artifactId;		
		this.description = description;
	}
	
	public Event(ArtifactScope artifactScope, String artifactId, String description) {
		this.artifactScope = artifactScope;
		this.artifactId = artifactId;		
		this.description = description;
	}
	
	public Event(EventScope eventScope, String description) {
		this.eventScope = eventScope;	
		this.description = description;
	}
	*/
	/*
	public String getTopicName() {
		return this.getEventScope() + "-" + this.getEventScopeId();
	}
	*/
	
	public static Event randomEvent() {
		Event event = new Event();
		event.setId(RandomGenerator.randomString());
		event.setCreationDate(RandomGenerator.randomLong());
		event.setEventPhase(RandomGenerator.randomEnum(EventPhase.class));
		event.setAuthorId(RandomGenerator.randomString());;
		event.setArtifactScope(RandomGenerator.randomEnum(ArtifactScope.class));
		event.setArtifactId(RandomGenerator.randomString());
		event.setDescription(RandomGenerator.randomString());
		return event;
	}

}
