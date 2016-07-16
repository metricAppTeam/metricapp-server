package metricapp.service.spec.controller;

import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.NotFoundException;

public interface NotificationCRUDInterface {
	
	public NotificationCrudDTO getNotificationById(String username, String id) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationByAuthorId(String username, String authorId) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationByScope(String username, String scope) throws BadInputException, NotFoundException;
	public NotificationCrudDTO getNotificationByArtifactId(String username, String artifactId) throws BadInputException, NotFoundException;
	public NotificationCrudDTO createNotification(String username, NotificationDTO dto) throws BadInputException;
	public NotificationCrudDTO updateNotification(String username, NotificationDTO dto) throws BadInputException, NotFoundException;	
	public NotificationCrudDTO patchNotificationReadById(String username, String id, boolean read) throws BadInputException, NotFoundException, DBException;
	public void deleteNotificationById(String username, String id) throws BadInputException;
		
}
