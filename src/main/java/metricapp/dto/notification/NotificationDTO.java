package metricapp.dto.notification;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import metricapp.entity.event.EventScope;

@Data
public class NotificationDTO implements Serializable {
	
	private static final long serialVersionUID = -3515906896966614256L;
	
	public String id;
	public LocalDate creationDate;
	public String authorId;
	public EventScope scope;
	public String artifactId;	
	public String description;	
	public boolean read;
	
}
