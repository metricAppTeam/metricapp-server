package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.notification.box.NotificationBoxCrudDTO;
import metricapp.service.spec.controller.AuthCRUDInterface;
import metricapp.service.spec.controller.NotificationBoxCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/inboxes"))
public class NotificationBoxRestController {
	
	@Autowired
	private AuthCRUDInterface authController;
	
	@Autowired
	private NotificationBoxCRUDInterface nboxController;	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<NotificationBoxCrudDTO> getAllNotificationBoxes() {
		
		NotificationBoxCrudDTO responseDTO = new NotificationBoxCrudDTO();
		
		try {
				
		responseDTO = nboxController.getAllNotificationBoxes();
		
		return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.OK);			
		
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.DELETE)
	public ResponseEntity<NotificationBoxCrudDTO> deleteAllNotificationBoxes() {
		
		NotificationBoxCrudDTO responseDTO = new NotificationBoxCrudDTO();
		
		try {
				
		responseDTO = nboxController.deleteAllNotificationBoxes();
		
		return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.OK);			
		
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NotificationBoxCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
