package metricapp.service.spec.controller;

import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.dto.notification.box.NotificationBoxCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface NotificationBoxCRUDInterface {
	
	public NotificationCrudDTO getAllNotificationsForUser(String username) throws BadInputException;
	public NotificationBoxCrudDTO createNotificationBoxForUser(String username) throws BadInputException;
	public NotificationCrudDTO getNotificationForUserById(String username, String id) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByAuthorId(String username, String authorId) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByScope(String username, String scope) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByArtifactId(String username, String scope) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByRead(String username, String read) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserFromTo(String username, String from, String to) throws BadInputException, NotFoundException;
	public NotificationCrudDTO patchNotificationBoxForUser(String username, NotificationDTO dto) throws BadInputException, NotFoundException;
	public NotificationCrudDTO deleteNotificationForUserById(String username, String id) throws BadInputException;
	
		
}
