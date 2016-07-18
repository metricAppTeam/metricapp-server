package metricapp.dto.notification.box;


import org.modelmapper.PropertyMap;

import metricapp.entity.notification.box.NotificationBox;

public class NotificationBoxDTOMap extends PropertyMap<NotificationBoxDTO, NotificationBox> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setCreationDate(source.getCreationDate());
		map().setLastPushDate(source.getLastPushDate());
		map().setOwnerId(source.getOwnerId());
		map().setNotifications(source.getNotifications());
	}

}
