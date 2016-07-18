package metricapp.dto.notification;

import java.io.Serializable;

import lombok.Data;
import metricapp.entity.event.EventScope;

@Data
public class NotificationDTO implements Serializable {
	
	private static final long serialVersionUID = -3515906896966614256L;
	
	public String id;
	public String eventId;
	public Long creationDate;
	public String authorId;
	public EventScope scope;
	public String artifactId;	
	public String description;	
	public String recipient;
	public boolean read;
	
}
