package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.entity.event.ArtifactScope;
import metricapp.entity.event.Event;
import metricapp.entity.event.EventPhase;
import metricapp.service.spec.NotificationServiceInterface;

@CrossOrigin
@RestController
@RequestMapping(("/test"))
public class TestRestController {
	
	@Autowired
	NotificationServiceInterface notificationServiceController;
	
	@RequestMapping(value = "bus-events", method = RequestMethod.POST)
	public ResponseEntity<String> testBusEvents(@RequestBody String message) {
		
		Event event = new Event(EventPhase.PHASE2_1, null, ArtifactScope.MGOAL, "1", message);
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
	
	@RequestMapping(value = "inner-events", method = RequestMethod.POST)
	public ResponseEntity<String> testInnerEvents(@RequestBody String message) {
		
		Event event = new Event(EventPhase.PHASE2_2, "expert-gm", ArtifactScope.MGOAL, "1", message);
		boolean result = notificationServiceController.publish("GRID", event);
		
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
