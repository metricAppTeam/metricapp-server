package metricapp.service.spec.controller;

import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface NotificationCRUDInterface {
	
	public NotificationCrudDTO createNotificationForUser(String username, NotificationDTO dto) throws BadInputException;
	
	public NotificationCrudDTO getAllNotifications() throws NotFoundException;
	
	public NotificationCrudDTO getAllNotificationsForUser(String username) throws BadInputException, NotFoundException;	
	public NotificationCrudDTO getNotificationForUserById(String username, String id) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByAuthorId(String username, String authorId) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByEventScope(String username, String eventScope) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByEventScopeId(String username, String eventScopeId) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByArtifactScope(String username, String artifactScope) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByArtifactId(String username, String artifactId) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserByRead(String username, String read) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationsForUserPage(String username, String from, String size) throws BadInputException, NotFoundException;
	
	public NotificationCrudDTO getNewNotificationsForUser(String authUsername) throws BadInputException, NotFoundException;
	
	public NotificationCrudDTO patchNotificationForUser(String username, NotificationDTO dto) throws BadInputException, NotFoundException;
	
	public NotificationCrudDTO deleteNotificationForUserById(String username, String id) throws BadInputException;
	
	public NotificationCrudDTO deleteAllNotifications();
	
		
}
