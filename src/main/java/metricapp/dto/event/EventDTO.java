package metricapp.dto.event;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import metricapp.entity.event.EventScope;

@Data
public class EventDTO implements Serializable {
	
	private static final long serialVersionUID = 8724934021086428683L;

	private String id;
	private LocalDate creationDate;
	private String authorId;
	private EventScope scope;
	private String artifactId;	
	private String description;
	
}
