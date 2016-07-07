package metricapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.bus.BusIncomingMessage;


@RestController
@RequestMapping(("/incoming"))
public class BusIncomingMessageRest {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BusIncomingMessage> post(@RequestBody BusIncomingMessage message) {
		//TODO wait for bus documentation
		System.out.println(message.getData());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
