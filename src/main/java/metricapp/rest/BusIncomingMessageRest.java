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


@RestController
@RequestMapping(("/incoming"))
public class BusIncomingMessageRest {

	@Autowired
	ExternalElementsRepositoryInterface externalElementsRepository;
	
	@Autowired
	MeasurementGoalCRUDInterface measurementGoalCrudController;
	
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
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
