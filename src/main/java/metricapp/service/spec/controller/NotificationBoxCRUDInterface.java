package metricapp.service.spec.controller;

import metricapp.dto.notification.box.NotificationBoxCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface NotificationBoxCRUDInterface {
	
	public NotificationBoxCrudDTO createNotificationBoxForUser(String username) throws BadInputException;

	public NotificationBoxCrudDTO getAllNotificationBoxes() throws NotFoundException;
	
	public NotificationBoxCrudDTO deleteAllNotificationBoxes();	
		
}
