package metricapp.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.bus.BusIncomingMessage;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.entity.external.NotificationPointerBus;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.spec.controller.MeasurementGoalCRUDInterface;
import metricapp.service.spec.repository.ExternalElementsRepositoryInterface;

/**
 * This rest interface manages bus notification. In this case we manually activated notification for OrganizationalGoal create and update operations.
 * When an OrganizationalGoal is created, the Bus sends us a post to this rest interface to notify the new "instance" field.
 * 
 *
 */
@RestController
@RequestMapping(("/incoming"))
public class BusIncomingMessageRest {

	@Autowired
	ExternalElementsRepositoryInterface externalElementsRepository;
	
	@Autowired
	MeasurementGoalCRUDInterface measurementGoalCrudController;
	
	/**
	 * this method manages the incoming notifications. When an OrganizationalGoal is created, message contains the instance of it.
	 * According to Confluence Sequence Diagram about MeasurementGoal Creation, the new notification triggers the creation of a empty MeasurementGoal in state of "Created"
	 * and sets organizationalGoalId field to correct pointerBus. 
	 * Due to bus implementation, the response must be not empty, so we returns the incoming message. 
	 * @param message incoming notification
	 * @return the same message received
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BusIncomingMessage> post(@RequestBody BusIncomingMessage message) {
		System.out.println(message.getData());
		try {
			NotificationPointerBus organizationalGoalPointer = externalElementsRepository.pointerOfIncomingNotificationObject(message.getData());
			
			if(organizationalGoalPointer.getOperation().equals("create")){
				MeasurementGoal goal = new MeasurementGoal();
				goal.setState(State.Created);
				goal.setEntityType(Entity.MeasurementGoal);
				goal.setReleaseNote("Measurement Goal generated from Organizational goal " + organizationalGoalPointer.getInstance() + " creation");
				goal.setOrganizationalGoalId(externalElementsRepository.fromNotificationToPointerBus(organizationalGoalPointer));
				measurementGoalCrudController.createMeasurementGoal(goal);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<BusIncomingMessage>(message,HttpStatus.OK);
	}

}
