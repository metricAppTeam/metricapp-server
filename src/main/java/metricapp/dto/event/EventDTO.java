package metricapp.dto.event;

import java.io.Serializable;

import lombok.Data;
import metricapp.entity.event.EventScope;

@Data
public class EventDTO implements Serializable {
	
	private static final long serialVersionUID = 8724934021086428683L;

	private String id;
	private Long creationDate;
	private String authorId;
	private EventScope scope;
	private String artifactId;	
	private String description;
	
}
