package metricapp.entity.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationPointerBus extends PointerBus{

	public NotificationPointerBus() {
		super();
	}
	
	public String operation;
	public String phase;

}
