package metricapp.dto.notification;

import java.util.HashMap;

import org.modelmapper.PropertyMap;

import metricapp.entity.notification.Notification;

public class NotificationMap extends PropertyMap<Notification, NotificationDTO> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setEventId(source.getEventId());
		map().setCreationDate(source.getCreationDate());
		map().setAuthorId(source.getAuthorId());
		map().setEventScope(source.getEventScope());
		map().setEventScopeId(source.getEventScopeId());
		map().setArtifactScope(source.getArtifactScope());
		map().setArtifactId(source.getArtifactId());
		map().setDescription(source.getDescription());
		map().setRead(source.isRead());
		map().setMetadata(new HashMap<String, String>());
	}
	
}
