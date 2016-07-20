package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventScope;
import metricapp.service.spec.NotificationServiceInterface;

@CrossOrigin
@RestController
@RequestMapping(("/test"))
public class TestRestController {
	
	@Autowired
	NotificationServiceInterface notificationServiceController;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> testPOST(@RequestBody String message) {
		
		System.out.println(message);
		Event event = new Event(EventScope.GRID, message);
		boolean result = notificationServiceController.publish("EXPERT", event);
		
		String response = "Received: " + message + " result=" + result;
		
		try {			
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (Exception e) {
			response = e.getMessage();
			e.printStackTrace();
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
