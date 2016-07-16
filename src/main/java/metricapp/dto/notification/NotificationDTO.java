package metricapp.dto.notification;

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
public class NotificationDTO extends DTO implements Serializable {
	
	private static final long serialVersionUID = -3515906896966614256L;
	
	public String id;
	public LocalDate creationDate;
	public String authorId;
	public EventScope scope;
	public String artifactId;	
	public String description;	
	public boolean read;
	
	public NotificationDTO() {
		super();
	}
	
}
