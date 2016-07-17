package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.notification.NotificationCrudDTO;
import metricapp.dto.notification.NotificationDTO;
import metricapp.dto.notification.box.NotificationBoxCrudDTO;
import metricapp.dto.notification.box.NotificationBoxDTO;
import metricapp.entity.notification.Notification;
import metricapp.entity.notification.box.NotificationBox;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.NotificationBoxCRUDInterface;
import metricapp.service.spec.repository.NotificationBoxRepository;

@Service
public class NotificationBoxCRUDController implements NotificationBoxCRUDInterface {

	@Autowired
	private NotificationBoxRepository nboxRepo;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public NotificationCrudDTO getAllNotificationsForUser(String username) throws BadInputException {
		if (username == null) {
			throw new BadInputException("NotificationBox username cannot be null");
		}
		
		return null;
	}	

	@Override
	public NotificationBoxCrudDTO createNotificationBoxForUser(String username) throws BadInputException {
		if (username == null) {
			throw new BadInputException("NotificationBox username cannot be null");
		}
		
		NotificationBox notificationbox = new NotificationBox();
		notificationbox.setCreationDate(LocalDate.now());
		notificationbox.setLastPushDate(LocalDate.now());
		notificationbox.setOwnerId(username);
		notificationbox.setNotifications(new ArrayList<Notification>());
		
		NotificationBoxCrudDTO crud = new NotificationBoxCrudDTO();
		
		crud.setRequest("CREATE NotificationBox WITH username=" + username);
		crud.addNotificationBoxToList(modelMapperFactory.getStandardModelMapper().map(nboxRepo.insert(notificationbox), NotificationBoxDTO.class));
		
		return crud;
	}

	@Override
	public NotificationCrudDTO getNotificationForUserById(String username, String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Notification id cannot be null");
		}
		
		NotificationBox notificationBox = nboxRepo.findByOwnerId(username);
		
		if (notificationBox == null) {
			throw new NotFoundException("Cannot find NotificationBox with username=" + username);
		}
		
		List<Notification> notifications = notificationBox.getNotifications();
		int i = 0;
		while(i < notifications.size()) {
			if (notifications.get(i).getId().equals(id)) break;
			i++;
		}
		
		if (i == notifications.size()) {
			throw new NotFoundException();
		}
		
		Notification notification = notifications.get(i);
		
		NotificationCrudDTO crud = new NotificationCrudDTO();
		
		crud.setRequest("GET Notification WITH id=" + id);
		crud.addNotificationToList(modelMapperFactory.getStandardModelMapper().map(notification, NotificationDTO.class));
		
		return crud;	
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByAuthorId(String username, String authorId)
			throws BadInputException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByScope(String username, String scope)
			throws BadInputException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByArtifactId(String username, String scope)
			throws BadInputException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserByRead(String username, String read)
			throws BadInputException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationCrudDTO getNotificationsForUserFromTo(String username, String from, String to)
			throws BadInputException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationCrudDTO patchNotificationBoxForUser(String username, NotificationDTO dto)
			throws BadInputException, NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationCrudDTO deleteNotificationForUserById(String username, String id) throws BadInputException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
