package metricapp.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import metricapp.dto.notification.box.NotificationBoxCrudDTO;
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
	public NotificationBoxCrudDTO createNotificationBoxForUser(String username) throws BadInputException {
		if (username == null) {
			throw new BadInputException("NotificationBox username cannot be null");
		}
		
		NotificationBox nbox = new NotificationBox();
		nbox.setOwnerId(username);
		nbox.setNotifications(new ArrayList<Notification>());
		
		nbox = nboxRepo.insert(nbox);
		
		NotificationBoxCrudDTO crud = new NotificationBoxCrudDTO();		
		crud.setRequest("CREATE NotificationBox WITH username=" + username);
		crud.addNotificationBox(nbox, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}
	
	@Override
	public NotificationBoxCrudDTO getAllNotificationBoxes() throws NotFoundException {
		List<NotificationBox> nboxes = nboxRepo.findAll();
		
		/* SILENT ERROR
		if (nboxes.isEmpty()) {
			throw new NotFoundException("Cannot find Notification");
		}
		*/
		
		String request = "GET ALL NBoxes";
		NotificationBoxCrudDTO crud = new NotificationBoxCrudDTO();
		crud.setRequest(request);
		crud.addAllNotificationBox(nboxes, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	@Override
	public NotificationBoxCrudDTO deleteAllNotificationBoxes() {
		nboxRepo.deleteAll();
		
		NotificationBoxCrudDTO crud = new NotificationBoxCrudDTO();
		crud.setRequest("DELETE ALL NBoxes");
		
		return crud;
	}

}
