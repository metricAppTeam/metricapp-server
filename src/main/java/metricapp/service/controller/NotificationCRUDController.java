package metricapp.service.controller;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.entity.notification.Notification;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.NotificationCRUDInterface;
import metricapp.service.spec.repository.NotificationRepository;

@Service
public class NotificationCRUDController implements NotificationCRUDInterface {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;

	@Override
	public NotificationCrudDTO getNotificationById(String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		Notification notification = notificationRepository.findNotificationById(id);
		
		if (notification == null) {
			throw new NotFoundException("Notification with id " + id + " not found");
		}
		
		NotificationCrudDTO dto = new NotificationCrudDTO();
		
		dto.setRequest("GET Notification WITH id=" + id);
		dto.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notification, NotificationDTO.class));
		
		return dto;
	}

	@Override
	public NotificationCrudDTO createNotification(@Nonnull NotificationDTO dto) throws BadInputException {
		
		if (dto.getId() != null) {
			throw new BadInputException("New Notification cannot have an id");
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
		dtoCRUD.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notificationRepository.save(newNotification), NotificationDTO.class));
		return dtoCRUD;
	}

	@Override
	public void deleteNotificationById(String id) throws BadInputException {
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		notificationRepository.delete(id);
	}

	@Override
	public NotificationCrudDTO setNotificationReadById(String id, boolean read)
			throws BadInputException, NotFoundException, DBException {
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		Notification notification = notificationRepository.findNotificationById(id);
		notification.setRead(read);
		
		NotificationCrudDTO dtoCRUD = new NotificationCrudDTO();
		dtoCRUD.setRequest("UPDATE (read=" + read + ") Notification WITH id=" + id);
		
		try {
			dtoCRUD.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notificationRepository.save(notification), NotificationDTO.class));
		} catch (Exception e) {
			throw new DBException("DB error while saving Notification");
		}

		return dtoCRUD;
	}
	
	

}
