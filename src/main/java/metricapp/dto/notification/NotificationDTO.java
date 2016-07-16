package metricapp.dto.notification;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;
import metricapp.entity.notification.NotificationScope;


@Getter
@Setter()
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class

NotificationDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -2073939437602304884L;
	
	public String id;
	public LocalDate creationDate;
	public String authorId;
	public NotificationScope scope;
	public String artifactId;	
	public String description;	
	public boolean isRead;
	
	public NotificationDTO() {
		super();
	}
	
	

	
}
