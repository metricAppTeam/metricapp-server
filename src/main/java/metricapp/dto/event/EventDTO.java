package metricapp.dto.event;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;
import metricapp.entity.event.EventScope;

@Getter
@Setter()
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class EventDTO extends DTO implements Serializable {
	
	private static final long serialVersionUID = 8724934021086428683L;

	private String id;
	private LocalDate creationDate;
	private String authorId;
	private EventScope scope;
	private String artifactId;	
	private String description;	
	
	public EventDTO() {
		super();
	}
	
}
