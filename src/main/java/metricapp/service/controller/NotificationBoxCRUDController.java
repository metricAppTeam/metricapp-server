package metricapp.service.controller;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.entity.event.EventScope;
import metricapp.entity.notification.Notification;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.NotificationBoxCRUDInterface;
import metricapp.service.spec.controller.NotificationCRUDInterface;
import metricapp.service.spec.repository.NotificationBoxRepository;
import metricapp.service.spec.repository.NotificationRepository;

@Service
public class NotificationBoxCRUDController implements NotificationBoxCRUDInterface {

	@Autowired
	private NotificationBoxRepository notificationboxRepository;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	/*

	@Override
	public NotificationCrudDTO getNotificationById(String username, String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		Notification notification = notificationRepository.findNotificationById(id);
		
		if (notification == null) {
			throw new NotFoundException("Cannot find Notification with id=" + id);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		
		crud.setRequest("GET Notification WITH id=" + id);
		crud.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notification, NotificationDTO.class));
		
		return crud;
	}
	
	@Override
	public NotificationCrudDTO getNotificationByAuthorId(String username, String authorId) throws BadInputException, NotFoundException {
		if (authorId == null) {
			throw new BadInputException("Notification authorId cannot be null");
		}
		
		List<Notification> notifications = notificationRepository.findNotificationByAuthorId(authorId);
		
		if (notifications.size() == 0) {
			throw new NotFoundException("Cannot find Notification with authorId=" + authorId);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("GET Notification WITH authorId=" + authorId);
		
		for (Notification notification : notifications) {
			crud.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notification, NotificationDTO.class));
		}		
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationByScope(String username, String scope) throws BadInputException, NotFoundException {
		if (scope == null) {
			throw new BadInputException("Notification scope cannot be null");
		}
		
		List<Notification> notifications = notificationRepository.findNotificationByScope(EventScope.valueOf(scope));
		
		if (notifications.size() == 0) {
			throw new NotFoundException("Cannot find Notification with scope=" + scope);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("GET Notification WITH scope=" + scope);
		
		for (Notification notification : notifications) {
			crud.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notification, NotificationDTO.class));
		}		
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationByArtifactId(String username, String artifactId) throws BadInputException, NotFoundException {
		if (artifactId == null) {
			throw new BadInputException("Notification artifactId cannot be null");
		}
		
		List<Notification> notifications = notificationRepository.findNotificationByArtifactId(artifactId);
		
		if (notifications.size() == 0) {
			throw new NotFoundException("Cannot find Notification with artifactId=" + artifactId);
		}
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		crud.setRequest("GET Notification WITH artifactId=" + artifactId);
		
		for (Notification notification : notifications) {
			crud.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notification, NotificationDTO.class));
		}		
		
		return crud;
	}

	@Override
	public NotificationCrudDTO createNotification(String username, @Nonnull NotificationDTO dto) throws BadInputException {
		
		if (dto.getId() != null) {
			throw new BadInputException("Notification id cannot be nulla");
		}
		
		if (dto.getCreationDate() != null) {
			throw new BadInputException("New Notification cannot have a creation date");
		}
		
		if (dto.getAuthorId() == null || dto.getScope() == null || 
				dto.getArtifactId() == null || dto.getDescription() == null) {
			throw new BadInputException("New Notification must have an author id, a scope, an artifact id and a description");
		}
		
		if (dto.isRead() == true) {
			throw new BadInputException("New Notification cannot be in read state");
		}
		
		dto.setCreationDate(LocalDate.now());
		dto.setRead(false);
		Notification newNotification = modelMapperFactory.getStandardModelMapper().map(dto, Notification.class);
		
		NotificationCrudDTO dtoCRUD = new NotificationCrudDTO();
		dtoCRUD.setRequest("CREATE Notification");
		dtoCRUD.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notificationRepository.insert(newNotification), NotificationDTO.class));
		
		return dtoCRUD;
	}
	
	@Override
	public NotificationCrudDTO updateNotification(String username, @Nonnull NotificationDTO dto) throws BadInputException, NotFoundException {
		if (dto == null) {
			throw new BadInputException("DTO cannot be null");
		}
		if (dto.getId() == null) {
			throw new BadInputException("Notification id cannot be null");
		}

		String id = dto.getId();
		Notification notification = notificationRepository.findNotificationById(id);		
		if (notification == null) {
			throw new NotFoundException("Cannot find Notification with id=" + id);
		}
		
		Notification updateNotification = modelMapperFactory.getStandardModelMapper().map(dto, Notification.class);
		notification.setRead(updateNotification.isRead());

		NotificationCrudDTO dtoCrud = new NotificationCrudDTO();
		dtoCrud.setRequest("UPDATE Notification WITH id=" + id);		
		dtoCrud.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notificationRepository.save(notification), NotificationDTO.class));

		return dtoCrud;
	}
	
	@Override
	public NotificationCrudDTO patchNotificationReadById(String username, String id, boolean read) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		Notification notification = notificationRepository.findNotificationById(id);
		if (notification == null) {
			throw new NotFoundException("Cannot find Notification with id=" + id);
		}
		
		notification.setRead(read);
		
		NotificationCrudDTO dtoCRUD = new NotificationCrudDTO();
		dtoCRUD.setRequest("PATCH (read=" + read + ") Notification WITH id=" + id);		
		dtoCRUD.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notificationRepository.save(notification), NotificationDTO.class));
		
		return dtoCRUD;
	}

	@Override
	public void deleteNotificationById(String username, String id) throws BadInputException {
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		notificationRepository.delete(id);
	}
	*/

	

}
