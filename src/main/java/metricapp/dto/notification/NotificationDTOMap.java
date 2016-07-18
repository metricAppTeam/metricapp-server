package metricapp.dto.notification;


import org.modelmapper.PropertyMap;
import metricapp.entity.notification.Notification;

public class NotificationDTOMap extends PropertyMap<NotificationDTO, Notification> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setEventId(source.getEventId());
		map().setCreationDate(source.getCreationDate());
		map().setAuthorId(source.getAuthorId());
		map().setScope(source.getScope());
		map().setArtifactId(source.getArtifactId());
		map().setDescription(source.getDescription());
		map().setRead(source.isRead());
	}

}
