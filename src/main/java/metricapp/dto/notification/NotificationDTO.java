package metricapp.dto.notification;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.EventPhase;

@Data
public class NotificationDTO implements Serializable {
	
	private static final long serialVersionUID = -3515906896966614256L;
	
	public String id;
	public String eventId;
	public Long creationDate;
	public EventPhase eventPhase;
	public String authorId;
	public ArtifactScope artifactScope;
	public String artifactId;
	public String description;	
	public String recipient;
	public boolean read;
	
	public Map<String, String> metadata;
	
}
