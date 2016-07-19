package metricapp.service.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.EventScope;
import metricapp.entity.notification.Notification;
import metricapp.entity.notification.box.NotificationBox;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.NotificationCRUDInterface;
import metricapp.service.spec.repository.NotificationBoxRepository;
import metricapp.service.spec.repository.NotificationRepository;
import metricapp.service.spec.repository.UserRepository;

@Service
public class NotificationCRUDController implements NotificationCRUDInterface {

	@Autowired
	private NotificationRepository notificationRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NotificationBoxRepository nboxRepo;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public NotificationCrudDTO createNotificationForUser(String username, @Nonnull NotificationDTO dto) throws BadInputException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (dto.getId() != null) {
			throw new BadInputException("Notification id cannot be set manually");
		}
		
		if (dto.getCreationDate() != null) {
			throw new BadInputException("Notification creation date cannot be set manually");
		}
		
		if (dto.getAuthorId() == null) {
			throw new BadInputException("Notification must have an author id");
		}
		
		if (dto.getEventScope() == null) {
			throw new BadInputException("Notification must have an event scope");
		}
		
		if (dto.getEventScopeId() == null) {
			throw new BadInputException("Notification must have an event scope id");
		}
		
		if (dto.getArtifactScope() == null) {
			throw new BadInputException("Notification must have an artifact scope");
		}
		
		if (dto.getArtifactId() == null) {
			throw new BadInputException("Notification must have an artifact id");
		}
		
		if (dto.getDescription() == null) {
			throw new BadInputException("Notification must have a description");
		}
		
		if (dto.isRead()) {
			throw new BadInputException("Notification cannot be created in read state");
		}
		
		Notification notification = modelMapperFactory.getStandardModelMapper().map(dto, Notification.class);
		notification.setCreationDate(Calendar.getInstance().getTimeInMillis());
		notification.setRead(false);
		
		notification = notificationRepo.insert(notification);
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("CREATE Notification WITH id=" + notification.getId());
		crud.addNotification(notification, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public NotificationCrudDTO getAllNotifications() throws NotFoundException {
		List<Notification> notifications = notificationRepo.findAll();
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification");
		}
		*/
		
		String request = "GET ALL Notifications";
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);
		
		return crud;
	}
	
	@Override
	public NotificationCrudDTO getAllNotificationsForUser(String username) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		List<Notification> notifications = notificationRepo.findNotificationByRecipient(username);
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification WITH recipient=" + username);
		}
		*/
		
		String request = "GET ALL Notification FOR user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);
		
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
		
		Notification notification = notificationRepo.findNotificationByRecipientAndId(username, id);
		
		if (notification == null) {
			throw new NotFoundException("Cannot find Notification with id=" + id);
		}
		
		String request = "GET Notification WITH id=" + id + " for user WITH username=" + username;
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest(request);
		crud.addNotificationDTO(this.generateCompositeDTO(notification));
		
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
		
		List<Notification> notifications = notificationRepo.findNotificationByRecipientAndAuthorId(username, authorId);
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with authorId=" + authorId);
		}
		*/
		
		String request = "GET Notification WITH authorId=" + authorId + " for user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByEventScope(String username, String eventScope) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (eventScope == null) {
			throw new BadInputException("Notification eventScope cannot be null");
		}
		
		List<Notification> notifications = notificationRepo.findNotificationByRecipientAndEventScope(username, EventScope.valueOf(eventScope));
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with eventScope=" + eventScope);
		}
		*/
		
		String request = "GET Notification WITH eventScope=" + eventScope + " for user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);
		
		return crud;
	}
	
	@Override
	public NotificationCrudDTO getNotificationsForUserByEventScopeId(String username, String eventScopeId) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (eventScopeId == null) {
			throw new BadInputException("Notification eventScopeId cannot be null");
		}
		
		List<Notification> notifications = notificationRepo.findNotificationByRecipientAndEventScopeId(username, eventScopeId);
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with eventScopeId=" + eventScopeId);
		}
		*/
		
		String request = "GET Notification WITH eventScopeId=" + eventScopeId + " for user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);	
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByArtifactScope(String username, String artifactScope) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (artifactScope == null) {
			throw new BadInputException("Notification artifactScope cannot be null");
		}
		
		List<Notification> notifications = notificationRepo.findNotificationByRecipientAndArtifactScope(username, ArtifactScope.valueOf(artifactScope));
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with artifactScope=" + artifactScope);
		}
		*/
		
		String request = "GET Notification WITH artifactScope=" + artifactScope + " for user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);		
		
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
		
		List<Notification> notifications = notificationRepo.findNotificationByRecipientAndArtifactId(username, artifactId);
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with artifactId=" + artifactId);
		}
		*/
		
		String request = "GET Notification WITH artifactId=" + artifactId + " for user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);	
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByRead(String username, String read) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (read == null) {
			throw new BadInputException("Notification read cannot be null");
		}
		
		List<Notification> notifications;
		if (Boolean.parseBoolean(read)) {
			notifications = notificationRepo.findNotificationByRecipientAndReadIsTrue(username);
		} else {
			notifications = notificationRepo.findNotificationByRecipientAndReadIsFalse(username);
		}		
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with read=" + read);
		}
		*/
		
		String request = "GET Notification WITH read=" + read + " for user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);		
		
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
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with from=" + from + "&size=" + size);
		}
		*/
		
		String request = "GET Notification WITH from=" + from + "&size=" + size + " for user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);
		
		return crud;
	}	
	
	@Override
	public NotificationCrudDTO getNewNotificationsForUser(String username) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		NotificationBox nbox = nboxRepo.findByOwnerId(username);
		
		// MODIFIED FOR BACKWARD COMPATIBILITY (TEMPORARY)
		if (nbox == null) {			
			//throw new NotFoundException("The user with username " + username + " does not have any nbox");
			NotificationBox newNBox = new NotificationBox();
			newNBox.setOwnerId(username);
			newNBox.setNotifications(new ArrayList<Notification>());
			nboxRepo.save(newNBox);
			String request = "GET Notification IN nbox FOR user WITH username=" + username;
			NotificationCrudDTO crud = new NotificationCrudDTO();
			crud.setRequest(request);
			crud.setMessage("New nbox created for user WITH username=" + username);
			return crud;
		}
		
		/* SILENT ERROR
		if (nbox.getNotifications().isEmpty()) {
			throw new NotFoundException("Cannot find Notification for user WITH userame=" + username);
		}
		*/
		
		List<Notification> newNotifications = new ArrayList<Notification>();		
		
		for (Notification newNotification : nbox.getNotifications()) {
			newNotifications.add(notificationRepo.save(newNotification));
		}
		
		nbox.getNotifications().clear();
		
		nboxRepo.save(nbox);		
		
		String request = "GET Notification IN nbox FOR user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, newNotifications);	
		
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
		
		List<Notification> notifications = new ArrayList<Notification>();
		
		if (id.equals("*")) {
			notifications = notificationRepo.findNotificationByRecipient(username);
		} else {
			Notification notification = notificationRepo.findNotificationByRecipientAndId(username, id);
			notifications.add(notification);
		}		
		
		/* SILENT ERROR
		if (notifications.isEmpty()) {
			throw new NotFoundException("Cannot find Notification with id=" + id);
		}
		*/
		
		for (Notification notification : notifications) {
			notification.setRead(dto.isRead());
			notificationRepo.save(notification);
		}		
		
		String request = "PATCH (read=" + dto.isRead() + ") Notification WITH id=" + id + " for user WITH username=" + username;
		NotificationCrudDTO crud = this.generateCompositeCrudDTO(request, notifications);
		
		return crud;
	}

	@Override
	public NotificationCrudDTO deleteNotificationForUserById(String username, String id) throws BadInputException {
		if (username == null) {
			throw new BadInputException("Username cannot be null");
		}
		
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		notificationRepo.deleteNotificationByRecipientAndId(username, id);
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("DELETE Notification WITH id=" + id);
		
		return crud;
	}

	@Override
	public NotificationCrudDTO deleteAllNotifications() {
		notificationRepo.deleteAll();
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("DELETE ALL Notifications");
		
		return crud;
	}	
	
	private NotificationCrudDTO generateCompositeCrudDTO(String request, List<Notification> notifications) {
		NotificationCrudDTO crud = new NotificationCrudDTO();		
		crud.setRequest(request);
		
		for (Notification notification : notifications) {
			NotificationDTO notificationDTO = this.generateCompositeDTO(notification);
			crud.addNotificationDTO(notificationDTO);
		}
		
		return crud;
	}
	
	private NotificationDTO generateCompositeDTO(Notification notification) {
		NotificationDTO dto = modelMapperFactory.getStandardModelMapper().map(notification, NotificationDTO.class);
		String username = notification.getAuthorId();
		User user = userRepo.findUserByUsername(username);
		if (user != null) {
			dto.getMetadata().put("userFirstname", user.getFirstname());
			dto.getMetadata().put("userLastname", user.getLastname());
			dto.getMetadata().put("userPicture", user.getPic());
		}
		return dto;
	}	

}
