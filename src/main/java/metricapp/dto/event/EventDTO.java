package metricapp.dto.event;

import java.io.Serializable;

import lombok.Data;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.EventPhase;

@Data
public class EventDTO implements Serializable {
	
	private static final long serialVersionUID = 8724934021086428683L;

	public String id;
	public Long creationDate;
	public EventPhase eventPhase;
	public String authorId;
	public ArtifactScope artifactScope;
	public String artifactId;	
	public String description;
	
}
