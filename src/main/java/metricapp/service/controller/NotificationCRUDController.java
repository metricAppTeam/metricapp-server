package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.dto.notification.box.NotificationBoxCrudDTO;
import metricapp.entity.event.EventScope;
import metricapp.entity.notification.Notification;
import metricapp.entity.notification.box.NotificationBox;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.NotificationCRUDInterface;
import metricapp.service.spec.repository.NotificationBoxRepository;
import metricapp.service.spec.repository.NotificationRepository;

@Service
public class NotificationCRUDController implements NotificationCRUDInterface {

	@Autowired
	private NotificationRepository notificationRepo;
	
	@Autowired
	private NotificationBoxRepository nboxRepo;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public NotificationBoxCrudDTO createNotificationBoxForUser(String username) throws BadInputException {
		if (username == null) {
			throw new BadInputException("NotificationBox username cannot be null");
		}
		
		NotificationBox nbox = new NotificationBox();
		nbox.setCreationDate(LocalDate.now());
		nbox.setLastPushDate(LocalDate.now());
		nbox.setOwnerId(username);
		nbox.setNotifications(new ArrayList<Notification>());
		
		nbox = nboxRepo.insert(nbox);
		
		NotificationBoxCrudDTO crud = new NotificationBoxCrudDTO();		
		crud.setRequest("CREATE NotificationBox WITH username=" + username);
		crud.addNotificationBox(nbox, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public NotificationCrudDTO createNotificationForUser(String username, @Nonnull NotificationDTO dto) throws BadInputException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (dto.getId() != null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		if (dto.getCreationDate() != null) {
			throw new BadInputException("New Notification cannot have a creation date");
		}
		
		if (dto.getAuthorId() == null || dto.getScope() == null || 
				dto.getArtifactId() == null || dto.getDescription() == null) {
			throw new BadInputException("New Notification must have an author id, a scope, an artifact id and a description");
		}
		
		if (dto.isRead()) {
			throw new BadInputException("New Notification cannot be in read state");
		}
		
		Notification notification = modelMapperFactory.getStandardModelMapper().map(dto, Notification.class);
		notification.setCreationDate(LocalDate.now());
		notification.setRead(false);
		
		notification = notificationRepo.insert(notification);
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("CREATE Notification WITH id=" + notification.getId());
		crud.addNotification(notification, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public NotificationCrudDTO getAllNotificationsForUser(String username) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		List<Notification> notifications = notificationRepo.findNotificationByRecipient(username);
		
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification WITH recipient=" + username);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();		
		crud.setRequest("GET Notification WITH recipient=" + username);
		crud.addAllNotification(notifications, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationForUserById(String username, String id) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		Notification notification = notificationRepo.findNotificationById(id);
		
		if (notification == null) {
			throw new NotFoundException("Cannot find Notification with id=" + id);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();		
		crud.setRequest("GET Notification WITH id=" + id);
		crud.addNotification(notification, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public NotificationCrudDTO getNotificationsForUserByAuthorId(String username, String authorId) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (authorId == null) {
			throw new BadInputException("Notification authorId cannot be null");
		}
		
		List<Notification> notifications = notificationRepo.findNotificationByAuthorId(authorId);
		
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with authorId=" + authorId);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("GET Notification WITH authorId=" + authorId);
		crud.addAllNotification(notifications, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByScope(String username, String scope) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (scope == null) {
			throw new BadInputException("Notification scope cannot be null");
		}
		
		List<Notification> notifications = notificationRepo.findNotificationByScope(EventScope.valueOf(scope));
		
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with scope=" + scope);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("GET Notification WITH scope=" + scope);
		crud.addAllNotification(notifications, modelMapperFactory.getStandardModelMapper());	
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByArtifactId(String username, String artifactId) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (artifactId == null) {
			throw new BadInputException("Notification artifactId cannot be null");
		}
		
		List<Notification> notifications = notificationRepo.findNotificationByArtifactId(artifactId);
		
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with artifactId=" + artifactId);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("GET Notification WITH artifactId=" + artifactId);
		crud.addAllNotification(notifications, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByRead(String username, String read) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (read == null) {
			throw new BadInputException("Notification scope cannot be null");
		}
		
		List<Notification> notifications;
		if (Boolean.getBoolean(read) == true) {
			notifications = notificationRepo.findNotificationByReadIsTrue();
		} else {
			notifications = notificationRepo.findNotificationByReadIsFalse();
		}		
		
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with read=" + read);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("GET Notification WITH read=" + read);
		crud.addAllNotification(notifications, modelMapperFactory.getStandardModelMapper());	
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserPage(String username, String from, String size) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (from == null) {
			throw new BadInputException("From cannot be null");
		}
		
		if (size == null) {
			throw new BadInputException("Size cannot be null");
		}
		
		PageRequest page = new PageRequest(Integer.valueOf(from), Integer.valueOf(size), Sort.Direction.DESC, "creationDate");
		Page<Notification> slice = notificationRepo.findNotificationByRecipient(username, page);
		List<Notification> notifications = slice.getContent();		
		
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with from=" + from + "&size=" + size);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("GET Notification WITH from=" + from + "&size=" + size);
		crud.addAllNotification(notifications, modelMapperFactory.getStandardModelMapper());	
		
		return crud;
	}	
	
	@Override
	public NotificationCrudDTO patchNotificationForUser(String username, NotificationDTO dto) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		String id = dto.getId();
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		Notification notification = notificationRepo.findNotificationById(id);
		
		if (notification == null) {
			throw new NotFoundException("Cannot find Notification with id=" + id);
		}
		
		notification.setRead(dto.isRead());
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("PATCH (read=" + dto.isRead() + ") Notification WITH id=" + id);		
		crud.addNotification(notification, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	@Override
	public void deleteNotificationForUserById(String username, String id) throws BadInputException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		notificationRepo.delete(id);
	}	

}
